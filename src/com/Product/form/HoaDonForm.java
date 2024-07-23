package com.Product.form;

import com.itextpdf.text.BaseColor;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import main.entity.HoaDon;

import main.repository.HoaDonChiTietRepository;
import main.repository.HoaDonRepository;
import main.repository.LichSuHoaDonRepository;
import main.response.HoaDonChiTietReponse;
import main.response.HoaDonResponse;
import main.response.LichSuHoaDonResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class HoaDonForm extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();

    private HoaDonRepository hdRepo;

    private HoaDonChiTietRepository hdctRepo;

    private DefaultTableModel dtmHoaDonChiTiet;

    private DefaultTableModel dtmLichSuHoaDon;

    private LichSuHoaDonRepository lshdRepo;

    private Menu menu;
    

    public HoaDonForm() {
        initComponents();
        setOpaque(false);
        hdRepo = new HoaDonRepository();

        hdctRepo = new HoaDonChiTietRepository();

        dtm = (DefaultTableModel) tb_hd.getModel();

        showDataTable(hdRepo.getAll());
//        String reString = "HD002";
//        showDataTableV2(timKiemHoaDon(reString));

        dtmHoaDonChiTiet = (DefaultTableModel) tb_hdct.getModel();

        lshdRepo = new LichSuHoaDonRepository();

        dtmLichSuHoaDon = (DefaultTableModel) tb_lshd.getModel();
    }
// tao ra mot bien toan cuc de luu tru du lieu qr
    String resultQR;
//     chuyen vao ham tim tim kiem(ham quet qr)

    HoaDonForm(String ketqua) {
        resultQR = ketqua;
        System.out.println("ma hoa don tai jframe hoa don " + resultQR);
    }

    private void showDataTable(ArrayList<HoaDonResponse> lists) {
        dtm.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1); // Khoi tao 1 gia tri bat dau bang 1 de tu dong tang
        // for..each + lamda 
        lists.forEach(s -> dtm.addRow(new Object[]{
            index.getAndIncrement(), s.getMaHoaDon(), s.getNgayTao(), s.getNgayCapNhap(), s.getTongTien(), s.getMaNhanVien(),
            s.getHoTen(), s.getDiaChi(), s.getSDT(), s.getTrangThai() == 0 ? "Đã Thanh Toán" : "Chưa thanh toán", s.getHinhThucTT() == 0 ? "Tiền Mặt" : "Chuyển Khoản"
        }));
    }

    private void showTableHoaDonChiTiet(ArrayList<HoaDonChiTietReponse> lists) {
        DefaultTableModel model = (DefaultTableModel) tb_hdct.getModel();
        model.setRowCount(0);// quet lai phat
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> model.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSPCT(),
            s.getThuongHieu(), s.getXuatXu(),
            s.getMauSac(),
            s.getKichThuoc(), s.getChatLieu(), s.getCoAo(), s.getDoDay(), s.getPhongCach(),
            s.getGiaBan(), s.getSoLuong(),
            s.isTrangThai() ? "Còn hàng" : "Hết hàng"
        }));
    }

    private void showTableLichSuHoaDon(ArrayList<LichSuHoaDonResponse> lists) {
        DefaultTableModel model = (DefaultTableModel) tb_lshd.getModel();
        model.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> model.addRow(new Object[]{
            index.getAndIncrement(), s.getMaNV(), s.getNgayCapNhap(), s.getTrangThai() == 0 ? "Đã Thanh Toán" : "Chưa thanh toán"
        }));
    }

//    private static void addTableHeader(PdfPTable table) {
//        table.addCell("Invoice code");
//        table.addCell("Customer name");
//        table.addCell("Date of payment");
//        table.addCell("Product Name"); // New column
//        table.addCell("Total amount");
//    }
//
//    private static PdfPCell getCell(String text, int alignment) {
//        PdfPCell cell = new PdfPCell(new Phrase(text));
//        cell.setPadding(0);
//        cell.setHorizontalAlignment(alignment);
//        cell.setBorder(PdfPCell.NO_BORDER);
//        return cell;
//    }
    
//    private static PdfPCell getCell(String text, int alignment) {
//        PdfPCell cell = new PdfPCell(new Phrase(text));
//        cell.setPadding(0);
//        cell.setHorizontalAlignment(alignment);
//        cell.setBorder(PdfPCell.NO_BORDER);
//        return cell;
//    }
//
//    private static void addTableHeader(PdfPTable table) {
//        Stream.of("STT", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền").forEach(columnTitle -> {
//            PdfPCell header = new PdfPCell();
//            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            header.setBorderWidth(2);
//            header.setPhrase(new Phrase(columnTitle));
//            table.addCell(header);
//        });
    private static PdfPCell getCell(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void addTableHeader(PdfPTable table, Font font) {
        Stream.of("STT", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell(new Phrase(columnTitle, font));
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);
        });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btn_xuat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_hdct = new com.Product.GUI.Table();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_lshd = new com.Product.GUI.Table();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txt_timTheoGiaMax = new com.Product.GUI.textfield.TextField();
        txt_timTheoGia = new com.Product.GUI.textfield.TextField();
        txt_tuNgay = new com.Product.GUI.textfield.TextField();
        txt_denNgay = new com.Product.GUI.textfield.TextField();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbox_hoaDon = new com.Product.GUI.Combobox();
        cb_httt = new com.Product.GUI.Combobox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch1 = new com.Product.GUI.textfield.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_hd = new com.Product.GUI.Table();
        jButton7 = new javax.swing.JButton();
        QR = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Hóa đơn");

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/hoadonmoi.png"))); // NOI18N
        jButton3.setText("In Hóa Đơn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_xuat.setBackground(new java.awt.Color(255, 255, 153));
        btn_xuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xuatfile.png"))); // NOI18N
        btn_xuat.setText("Xuất Excel");
        btn_xuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204), 3));

        tb_hdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Ma SPCT", "Thương Hiệu", "Xuất xứ", "Màu Sắc", "Kích Thước", "Chất Liệu", "Cổ áo", "Độ dày", "Phong cách", "Giá bán", "Số Lượng", "Trạng Thái"
            }
        ));
        tb_hdct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcl(evt);
            }
        });
        jScrollPane2.setViewportView(tb_hdct);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 204), 3));

        tb_lshd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Nguoi Tac Dong", "Ngay Cap Nhap", "Trang Thai"
            }
        ));
        jScrollPane3.setViewportView(tb_lshd);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("Hóa đơn chi tiết");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Lịch sử hóa đơn");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm nâng cao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        txt_timTheoGiaMax.setLabelText("Nhập giá max");

        txt_timTheoGia.setLabelText("Nhập giá min");

        txt_tuNgay.setLabelText("Từ ngày");

        txt_denNgay.setLabelText("Đến ngày");
        txt_denNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_denNgayActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 153));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/loc.png"))); // NOI18N
        jButton6.setText("Lọc");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Hình thức thanh toán");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Trạng thái hóa đơn");

        cbox_hoaDon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đã Thanh Toán", "Chưa Thanh Toán", " ", " ", " " }));
        cbox_hoaDon.setLineColor(new java.awt.Color(51, 51, 255));
        cbox_hoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_hoaDonActionPerformed(evt);
            }
        });

        cb_httt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tiền Mặt", "Chuyển Khoản", " " }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Tìm theo giá");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Tìm theo ngày");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tìm kiếm ");

        txtSearch1.setLabelText("Tìm kiếm Tại Ðây");
        txtSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearch1ActionPerformed(evt);
            }
        });

        tb_hd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Ngày Tạo", "Ngày Thanh Toán", "Tổng Tiền", "Mã NV", "Tên KH", "Địa Chỉ ", "Số Điện Thoại", "Trạng Thái", "Hình Thức Thanh Toán"
            }
        ));
        tb_hd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_hd);

        jButton7.setBackground(new java.awt.Color(255, 255, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/timkiem.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        QR.setBackground(new java.awt.Color(255, 255, 153));
        QR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        QR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/qr.png"))); // NOI18N
        QR.setText("Quét QR");
        QR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbox_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cb_httt, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(QR)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_tuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_denNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_timTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_timTheoGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButton6)))
                        .addGap(0, 44, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_tuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_denNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(QR)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cbox_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(cb_httt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txt_timTheoGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_timTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.getAccessibleContext().setAccessibleName("Tìm kiếm");

        jButton5.setBackground(new java.awt.Color(255, 255, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        jButton5.setText("Làm Mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton5)
                                .addGap(73, 73, 73)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_xuat)
                                .addGap(19, 19, 19)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(486, 486, 486))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(btn_xuat)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        txtSearch1.setText("");
        txt_timTheoGia.setText("");
        txt_timTheoGiaMax.setText("");
        txt_tuNgay.setText("");
        txt_denNgay.setText("");
        showDataTable(hdRepo.getAll());
    }//GEN-LAST:event_jButton5ActionPerformed


    private void cbox_hoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_hoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_hoaDonActionPerformed

    private void mcl(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcl
        // TODO add your handling code here:
    }//GEN-LAST:event_mcl

    private void btn_xuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatActionPerformed
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Học viên");

            XSSFRow row = null;
            XSSFCell cell = null;

            // Tạo dòng chứa tiêu đề
            row = spreadsheet.createRow(2);
            row.setHeight((short) 500);
            cell = row.createCell(0, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("DANH SÁCH HÓA ĐƠN");

            // Tạo dòng chứa tiêu đề cột
            row = spreadsheet.createRow(3);
            row.setHeight((short) 500);
            cell = row.createCell(0, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("MÃ HĐ");
            cell = row.createCell(2, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("Ngày Tạo");
            cell = row.createCell(3, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("Ngày Thanh Toán");
            cell = row.createCell(4, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("Tổng Tiền");
            cell = row.createCell(5, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("Mã Nhân Viên");
            cell = row.createCell(6, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("Tên Khách Hàng");
            cell = row.createCell(7, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("Địa Chỉ");
            cell = row.createCell(8, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("Số Điện Thoại");
            cell = row.createCell(9, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("Trạng Thái");
            cell = row.createCell(10, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("Hình Thức Thanh Toán");

            // Lấy danh sách hóa đơn từ HoaDonRepository (giả sử là một đối tượng chứa phương thức getAll())
            HoaDonRepository hoaDonRepository = new HoaDonRepository();
            ArrayList<HoaDonResponse> listItem = hoaDonRepository.getAll();

            // Đổ dữ liệu từ danh sách hóa đơn vào bảng Excel
            for (int i = 0; i < listItem.size(); i++) {
                HoaDonResponse hoaDon = listItem.get(i);

                // Tạo dòng mới cho mỗi hóa đơn
                row = spreadsheet.createRow(4 + i);
                row.setHeight((short) 400);

                // Ghi dữ liệu vào từng ô trong dòng
                row.createCell(0, XSSFCell.CELL_TYPE_NUMERIC).setCellValue(i + 1); // STT
                row.createCell(1, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getMaHoaDon()); // Mã HĐ
                row.createCell(2, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getNgayTao().toString()); // Ngày Tạo
                row.createCell(3, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getNgayCapNhap().toString()); // Ngày Thanh Toán
                row.createCell(4, XSSFCell.CELL_TYPE_NUMERIC).setCellValue(hoaDon.getTongTien()); // Tổng Tiền
                row.createCell(5, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getMaNhanVien()); // Mã Nhân Viên
                row.createCell(6, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getHoTen()); // Tên Khách Hàng
                row.createCell(7, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getDiaChi()); // Địa Chỉ
                row.createCell(8, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getSDT()); // Số Điện Thoại
                row.createCell(9, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getTrangThai() == 0 ? "Đã Thanh Toán" : "Chưa thanh toán"); // Trạng Thái
                row.createCell(10, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getHinhThucTT() == 0 ? "Tiền Mặt" : "Chuyển Khoản"); // Hình Thức Thanh Toán

                // Nếu có các cột dữ liệu khác, bạn có thể tiếp tục thêm vào đây
            }

            // Hiển thị hộp thoại để chọn vị trí và tên file để lưu
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                // Đảm bảo rằng file có đuôi .xlsx
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }

                // Ghi workbook vào file Excel
                try (FileOutputStream out = new FileOutputStream(filePath)) {
                    workbook.write(out);
                    System.out.println("Xuất file Excel thành công vào: " + filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Có lỗi khi ghi file.");
                }
            }

            // Đóng workbook để giải phóng tài nguyên
            workbook.close();

            System.out.println("Xuất file Excel thành công.");
            JOptionPane.showMessageDialog(this, "Xuất file Excel thành công.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_xuatActionPerformed

    private void QRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QRActionPerformed
        // TODO add your handling code here:
        Menu menu = new Menu();
        menu.setVisible(true);
        menu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                HoaDonResponse hdrp = getHoaDonResponse(menu.maHD);// quet lai di
                ArrayList<HoaDonChiTietReponse> listHdct = hdctRepo.getByIdHoaDon(hdrp.getId());

                System.out.println("hdct: " + listHdct);
                showTableHoaDonChiTiet(listHdct);// quét l?i xem nó nhan ko

                HoaDonResponse hdr = getHoaDonResponse(menu.id);

                // Retrieve and display LichSuHoaDon
                ArrayList<LichSuHoaDonResponse> listLshd = lshdRepo.getByIdLSHoaDon(hdr.getId());
                System.out.println("lshd: " + listLshd);
                showTableLichSuHoaDon(listLshd);
            }
        });
    }//GEN-LAST:event_QRActionPerformed

    private ArrayList<HoaDonResponse> timKiemHoaDon(String resultQR) {
        HoaDonRepository hdRepo = new HoaDonRepository();
        ArrayList<HoaDonResponse> list = new ArrayList<>();

        System.out.println("Chức năng tìm kiếm hóa đơn");

        if (resultQR == null || resultQR.trim().isEmpty()) {
            System.out.println("Không có mã QR được cung cấp");
        } else {
            HoaDonResponse hoaDon = hdRepo.timKiemHoaDonResponsebyQR(resultQR.trim());
            if (hoaDon != null) {
                list.add(hoaDon);
                System.out.println("Hóa đơn được tìm thấy: " + hoaDon.getMaHoaDon());
            } else {
                System.out.println("Không tìm thấy hóa đơn với mã QR: " + resultQR);
            }

            // Hiển thị dữ liệu trên giao diện
            System.out.println(list);
            showDataTableV2(list);
        }

        return list;
    }
    
    private HoaDonResponse getHoaDonResponse(String resultQR) {
        HoaDonRepository hdRepo = new HoaDonRepository();

        System.out.println("Chức năng tìm kiếm hóa đơn");

        // Kiểm tra mã QR không null hoặc rỗng
        if (resultQR == null || resultQR.trim().isEmpty()) {
            System.out.println("Không có mã QR được cung cấp");
            return null; // Trả về null nếu không có mã QR
        } else {
            HoaDonResponse hoaDon = hdRepo.timKiemHoaDonResponsebyQR(resultQR.trim());
            if (hoaDon != null) {
                System.out.println("Hóa đơn được tìm thấy: " + hoaDon.getMaHoaDon());
                return hoaDon;
            } else {
                System.out.println("Không tìm thấy hóa đơn với mã QR: " + resultQR);
                return null; // Trả về null nếu không tìm thấy hóa đơn
            }
        }
    }

    private void showDataTableV2(ArrayList<HoaDonResponse> lists) {
        dtm.setRowCount(0); // Clear the existing rows in the table model
        AtomicInteger index = new AtomicInteger(1); // Initialize index starting from 1

        lists.forEach(s -> dtm.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMaHoaDon(),
            s.getNgayTao(),
            s.getNgayCapNhap(),
            s.getTongTien(),
            s.getMaNhanVien(),
            s.getHoTen(),
            s.getDiaChi(),
            s.getSDT(),
            s.getTrangThai() == 0 ? "Đã Thanh Toán" : "Chưa thanh toán",
            s.getHinhThucTT() == 0 ? "Tiền Mặt" : "Chuyển Khoản"
        }));

        dtm.fireTableDataChanged(); // Notify the table that the model has been updated
    }

    private void txtSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearch1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
    String keyword = txtSearch1.getText().trim();
    Integer trangThai = cbox_hoaDon.getSelectedIndex();
    Integer httt = cb_httt.getSelectedIndex();

    // Get giaMin
    Double giaMin = null;
    String giaMinText = txt_timTheoGia.getText().trim();
    if (!giaMinText.isEmpty()) {
        try {
            giaMin = Double.parseDouble(giaMinText);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid number format for giaMin");
        }
    }

    // Get giaMax
    Double giaMax = null;
    String giaMaxText = txt_timTheoGiaMax.getText().trim();
    if (!giaMaxText.isEmpty()) {
        try {
            giaMax = Double.parseDouble(giaMaxText);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid number format for giaMax");
        }
    }

    // Get startDate
    LocalDate startDate = null;
    String ngayBatDau = txt_tuNgay.getText().trim();
    if (!ngayBatDau.isEmpty()) {
        try {
            startDate = LocalDate.parse(ngayBatDau, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date format for startDate", ngayBatDau, 0, e);
        }
    }

    // Get endDate
    LocalDate endDate = null;
    String ngayKetThuc = txt_denNgay.getText().trim();
    if (!ngayKetThuc.isEmpty()) {
        try {
            endDate = LocalDate.parse(ngayKetThuc, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date format for endDate", ngayKetThuc, 0, e);
        }
    }

    // Call the search method with inputs
    showDataTable(hdRepo.search(
        keyword,
        trangThai,
        httt,
        giaMin,
        giaMax,
        startDate != null ? startDate.toString() : null,
        endDate != null ? endDate.toString() : null
    ));

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ cho phạm vi giá.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
} catch (DateTimeParseException e) {
    JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Vui lòng nhập ngày ở định dạng yyyy-MM-dd.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_jButton6ActionPerformed

    private void tb_hdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hdMouseClicked
        // TODO add your handling code here:
        int id = hdRepo.getAll().get(tb_hd.getSelectedRow()).getId();
        showTableHoaDonChiTiet(hdctRepo.getAll());
        showTableLichSuHoaDon(lshdRepo.getAll());
    }//GEN-LAST:event_tb_hdMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        showDataTable(hdRepo.searchh(txtSearch1.getText()));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // Choose file path for PDF file
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu PDF");
//        int userSelection = fileChooser.showSaveDialog(null);
//
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToSave = fileChooser.getSelectedFile();
//            String outputPath = fileToSave.getAbsolutePath();
//
//            // Ensure the file has a .pdf extension
//            if (!outputPath.endsWith(".pdf")) {
//                outputPath += ".pdf";
//            }
//
//            // Generate PDF file
//            try {
//                // Create a new Document
//                Document document = new Document();
//                PdfWriter.getInstance(document, new FileOutputStream(outputPath));
//                document.open();
//
//                // Font for the title
//                Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
//
//                // Add the main title
//                Paragraph title = new Paragraph("INVOICE", boldFont);
//                title.setAlignment(Element.ALIGN_CENTER);
//                document.add(title);
//
//                // Add staff and customer information
//                PdfPTable infoTable = new PdfPTable(2);
//                infoTable.setWidthPercentage(100);
//                infoTable.setSpacingBefore(10f);
//                infoTable.setSpacingAfter(10f);
//                infoTable.addCell(getCell("Staff", PdfPCell.ALIGN_LEFT));
//                infoTable.addCell(getCell("Admin", PdfPCell.ALIGN_RIGHT));
//                infoTable.addCell(getCell("Customer", PdfPCell.ALIGN_LEFT));
//                infoTable.addCell(getCell("MR A", PdfPCell.ALIGN_RIGHT));
//                document.add(infoTable);
//
//                // Create and format the table
//                PdfPTable table = new PdfPTable(4); // Number of columns
//                table.setWidthPercentage(100); // Table width
//
//                // Format the table header
//                addTableHeader(table);
//
//                // Create a list of invoices (this should come from your data source)
//                HoaDonRepository hoaDonRepository = new HoaDonRepository();
//                ArrayList<HoaDonResponse> listItem = hoaDonRepository.getAll();
//
//                // Add data to the table
//                for (HoaDonResponse hoaDon : listItem) {
//                    table.addCell(hoaDon.getMaHoaDon());
//                    table.addCell(String.valueOf(hoaDon.getHoTen()));
//                    table.addCell(String.valueOf(hoaDon.getHinhThucTT()));
//                    table.addCell(String.valueOf(hoaDon.getNgayTao()));
//                }
//
//                // Add the table to the document
//                document.add(table);
//
//                // Add total amount (example value, adjust as needed)
//                Paragraph total = new Paragraph("Total: $8.00", boldFont);
//                total.setAlignment(Element.ALIGN_RIGHT);
//                total.setSpacingBefore(10f);
//                document.add(total);
//
//                // Generate QR code for each invoice
//                for (HoaDonResponse hoaDon : listItem) {
//                    String qrContent = "Invoice ID: " + hoaDon.getMaHoaDon();
//                    BarcodeQRCode qrCode = new BarcodeQRCode(qrContent, 100, 100, null);
//                    com.itextpdf.text.Image qrImage = qrCode.getImage();
//                    qrImage.setAlignment(Element.ALIGN_CENTER);
//                    qrImage.setSpacingBefore(10f);
//                    document.add(qrImage);
//                }
//
//                // Close the document
//                document.close();
//                System.out.println("PDF created at: " + outputPath);
//            } catch (DocumentException | IOException e) {
//                e.printStackTrace();
//            }
//        }

        // Choose file path for PDF file
//        int rowIndex = tb_hd.getSelectedRow();
//        if (rowIndex == -1) {
//            JOptionPane.showMessageDialog(null, "Chọn dòng để xuất");
//            return;
//        }
//
//        // Get data from the selected row
//        String maHoaDon = (String) tb_hd.getValueAt(rowIndex, 1); // Assuming column 0 is MaHoaDon
//        String hoTen = (String) tb_hd.getValueAt(rowIndex, 6); // Adjust index as needed
//        String ngayTao = (String) tb_hd.getValueAt(rowIndex, 2); // Adjust index as needed
//        Double tongTien = (Double) tb_hd.getValueAt(rowIndex, 4); // Adjust index as needed
//
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu PDF");
//        int userSelection = fileChooser.showSaveDialog(null);
//
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToSave = fileChooser.getSelectedFile();
//            String outputPath = fileToSave.getAbsolutePath();
//
//            // Ensure the file has a .pdf extension
//            if (!outputPath.endsWith(".pdf")) {
//                outputPath += ".pdf";
//            }
//
//            // Generate PDF file
//            try {
//                // Create a new Document
//                Document document = new Document();
//                PdfWriter.getInstance(document, new FileOutputStream(outputPath));
//                document.open();
//
//                // Font for the title
//                Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
//
//                // Add the main title
//                Paragraph title = new Paragraph("Bill", boldFont);
//                title.setAlignment(Element.ALIGN_CENTER);
//                document.add(title);
//
//                // Add staff and customer information (Example data here)
//                PdfPTable infoTable = new PdfPTable(2);
//                infoTable.setWidthPercentage(100);
//                infoTable.setSpacingBefore(10f);
//                infoTable.setSpacingAfter(10f);
//                infoTable.addCell(getCell("Staff", PdfPCell.ALIGN_LEFT));
//                infoTable.addCell(getCell("Admin", PdfPCell.ALIGN_RIGHT));
//                infoTable.addCell(getCell("Customer", PdfPCell.ALIGN_LEFT));
//                infoTable.addCell(getCell("MR A", PdfPCell.ALIGN_RIGHT));
//                document.add(infoTable);
//
//                // Create and format the table
//                PdfPTable table = new PdfPTable(4); // Number of columns
//                table.setWidthPercentage(100); // Table width
//
//                // Format the table header
//                addTableHeader(table);
//
//                // Add data from the selected row
//                table.addCell(maHoaDon);
//                table.addCell(hoTen);
//                table.addCell(ngayTao);
//                table.addCell(String.format("$%.2f", tongTien)); // Format the amount as currency
//
//                // Add the table to the document
//                document.add(table);
//
//                // Add total amount (example value, adjust as needed)
//                Paragraph total = new Paragraph("Total: " + String.format("$%.2f", tongTien), boldFont);
//                total.setAlignment(Element.ALIGN_RIGHT);
//                total.setSpacingBefore(10f);
//                document.add(total);
//
//                // Generate QR code for the invoice
//                String qrContent = "Invoice ID: " + maHoaDon;
//                BarcodeQRCode qrCode = new BarcodeQRCode(qrContent, 100, 100, null);
//                com.itextpdf.text.Image qrImage = qrCode.getImage();
//                qrImage.setAlignment(Element.ALIGN_CENTER);
//                qrImage.setSpacingBefore(10f);
//                document.add(qrImage);
//
//                // Close the document
//                document.close();
//                System.out.println("PDF created at: " + outputPath);
//            } catch (DocumentException | IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        int rowIndex = tb_hd.getSelectedRow();
//        if (rowIndex == -1) {
//            JOptionPane.showMessageDialog(null, "Chọn dòng để xuất");
//            return;
//        }
//
//        // Get data from the selected row
//        String maHoaDon = (String) tb_hd.getValueAt(rowIndex, 1); // Assuming column 0 is MaHoaDon
//        String hoTen = (String) tb_hd.getValueAt(rowIndex, 6); // Assuming column 1 is HoTen
//        String ngayTao = (String) tb_hd.getValueAt(rowIndex, 2); // Assuming column 2 is NgayTao
//        String tenSanPham = (String) tb_hdct.getValueAt(rowIndex, 2); // Assuming column 3 is TenSanPham
//        Double tongTien = (Double) tb_hd.getValueAt(rowIndex, 4); // Assuming column 4 is TongTien
//
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu PDF");
//        int userSelection = fileChooser.showSaveDialog(null);
//
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToSave = fileChooser.getSelectedFile();
//            String outputPath = fileToSave.getAbsolutePath();
//
//            // Ensure the file has a .pdf extension
//            if (!outputPath.endsWith(".pdf")) {
//                outputPath += ".pdf";
//            }
//
//            // Generate PDF file
//            try {
//                // Create a new Document
//                Document document = new Document();
//                PdfWriter.getInstance(document, new FileOutputStream(outputPath));
//                document.open();
//
//                // Font for the title
//                Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
//
//                // Add the main title
//                Paragraph title = new Paragraph("Bill", boldFont);
//                title.setAlignment(Element.ALIGN_CENTER);
//                document.add(title);
//
//                // Add staff and customer information (Example data here)
//                PdfPTable infoTable = new PdfPTable(2);
//                infoTable.setWidthPercentage(100);
//                infoTable.setSpacingBefore(10f);
//                infoTable.setSpacingAfter(10f);
//                infoTable.addCell(getCell("Staff", PdfPCell.ALIGN_LEFT));
//                infoTable.addCell(getCell("Admin", PdfPCell.ALIGN_RIGHT));
//                infoTable.addCell(getCell("Customer", PdfPCell.ALIGN_LEFT));
//                infoTable.addCell(getCell("MR A", PdfPCell.ALIGN_RIGHT));
//                document.add(infoTable);
//
//                // Create and format the table
//                PdfPTable table = new PdfPTable(5); // Updated to 5 columns
//                table.setWidthPercentage(100); // Table width
//
//                // Format the table header
//                addTableHeader(table);
//
//                // Add data from the selected row
//                table.addCell(maHoaDon);
//                table.addCell(hoTen);
//                table.addCell(ngayTao);
//                table.addCell(tenSanPham); // Add product name
//                table.addCell(String.format("$%.2f", tongTien)); // Format the amount as currency
//
//                // Add the table to the document
//                document.add(table);
//
//                // Add total amount (example value, adjust as needed)
//                Paragraph total = new Paragraph("Total: " + String.format("$%.2f", tongTien), boldFont);
//                total.setAlignment(Element.ALIGN_RIGHT);
//                total.setSpacingBefore(10f);
//                document.add(total);
//
//                // Generate QR code for the invoice
//                String qrContent = maHoaDon;
//                BarcodeQRCode qrCode = new BarcodeQRCode(qrContent, 100, 100, null);
//                com.itextpdf.text.Image qrImage = qrCode.getImage();
//                qrImage.setAlignment(Element.ALIGN_CENTER);
//                qrImage.setSpacingBefore(10f);
//                document.add(qrImage);
//
//                // Close the document
//                document.close();
//                System.out.println("PDF created at: " + outputPath);
//            } catch (DocumentException | IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    
    int rowIndex = tb_hd.getSelectedRow();
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Chọn dòng để xuất");
            return;
        }

        // Get data from the selected row in tb_hd
        String maHoaDon = (String) tb_hd.getValueAt(rowIndex, 1); // Assuming column 1 is MaHoaDon
        String hoTen = (String) tb_hd.getValueAt(rowIndex, 6); // Assuming column 6 is HoTen
        String ngayTao = (String) tb_hd.getValueAt(rowIndex, 2); // Assuming column 2 is NgayTao
        Double tongTien = (Double) tb_hd.getValueAt(rowIndex, 4); // Assuming column 4 is TongTien

        // Get data from the selected row in tb_hdct
        String tenSanPham = (String) tb_hdct.getValueAt(rowIndex, 2); // Assuming column 2 is TenSanPham in tb_hdct
        Integer soLuong = (Integer) tb_hdct.getValueAt(rowIndex, 11); // Assuming column 3 is SoLuong in tb_hdct
        Double donGia = (Double) tb_hdct.getValueAt(rowIndex, 10); // Assuming column 4 is DonGia in tb_hdct

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn vị trí và tên file để lưu PDF");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String outputPath = fileToSave.getAbsolutePath();

            // Ensure the file has a .pdf extension
            if (!outputPath.endsWith(".pdf")) {
                outputPath += ".pdf";
            }

            // Generate PDF file
            try {
                // Create a new Document
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(outputPath));
                document.open();

                // Load Vietnamese font
                BaseFont baseFont = BaseFont.createFont("C:\\Users\\ADMIN\\Desktop\\Du_An_1_SD19310-master\\Du_An_1_SD19310-master\\lib\\TimesNewRoman\\SVN-Times New Roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font titleFont = new Font(baseFont, 18, Font.BOLD);
                Font boldFont = new Font(baseFont, 12, Font.BOLD);
                Font regularFont = new Font(baseFont, 12, Font.NORMAL);

                // Add the logo
                String logoPath = "D:\\anhbophimcuocdoi\\logo.png"; // Update this path
                File logoFile = new File(logoPath);
                if (logoFile.exists()) {
                    Image logo = Image.getInstance(logoPath);
                    logo.setAlignment(Element.ALIGN_CENTER);
                    logo.scalePercent(50);
                    document.add(logo);
                } else {
                    System.err.println("Logo file not found at: " + logoPath);
                }

                // Add the main title
                Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG\nBILL OF SALE", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);

                // Add store information
                Paragraph storeInfo = new Paragraph("SNEAKER BEESHOES\nĐịa chỉ: 66 Mễ Trì Hạ, Nam Từ Liêm, Hà Nội", boldFont);
                storeInfo.setAlignment(Element.ALIGN_CENTER);
                storeInfo.setSpacingAfter(10);
                document.add(storeInfo);

                // Add invoice details
                PdfPTable infoTable = new PdfPTable(2);
                infoTable.setWidthPercentage(100);
                infoTable.setSpacingBefore(10f);
                infoTable.setSpacingAfter(10f);
                infoTable.addCell(getCell("Mã hóa đơn:", PdfPCell.ALIGN_LEFT, boldFont));
                infoTable.addCell(getCell(maHoaDon, PdfPCell.ALIGN_RIGHT, regularFont));
                infoTable.addCell(getCell("Khách hàng:", PdfPCell.ALIGN_LEFT, boldFont));
                infoTable.addCell(getCell(hoTen, PdfPCell.ALIGN_RIGHT, regularFont));
                infoTable.addCell(getCell("Ngày mua:", PdfPCell.ALIGN_LEFT, boldFont));
                infoTable.addCell(getCell(ngayTao, PdfPCell.ALIGN_RIGHT, regularFont));
                infoTable.addCell(getCell("Nhân viên bán hàng:", PdfPCell.ALIGN_LEFT, boldFont));
                infoTable.addCell(getCell("Admin", PdfPCell.ALIGN_RIGHT, regularFont)); // Placeholder for staff name
                document.add(infoTable);

                // Create and format the table
                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100); // Table width
                table.setSpacingBefore(20f);

                // Add table headers
                addTableHeader(table, boldFont);

                // Add data from the selected row
                table.addCell(new PdfPCell(new Phrase("1", regularFont))); // Assuming single item for simplicity
                table.addCell(new PdfPCell(new Phrase(tenSanPham, regularFont))); // Add product name
                table.addCell(new PdfPCell(new Phrase(String.valueOf(soLuong), regularFont))); // Add quantity
                table.addCell(new PdfPCell(new Phrase(String.format("%.0f đ", donGia), regularFont))); // Add unit price
                table.addCell(new PdfPCell(new Phrase(String.format("%.0f đ", soLuong * donGia), regularFont))); // Calculate total price

                // Add the table to the document
                document.add(table);

                // Add total amount
                Paragraph total = new Paragraph("Tổng tiền phải thanh toán: " + String.format("%.0f đ", tongTien), boldFont);
                total.setAlignment(Element.ALIGN_RIGHT);
                total.setSpacingBefore(10f);
                document.add(total);

                // Generate QR code for the invoice
                String qrContent = maHoaDon;
                BarcodeQRCode qrCode = new BarcodeQRCode(qrContent, 100, 100, null);
                com.itextpdf.text.Image qrImage = qrCode.getImage();
                qrImage.setAlignment(Element.ALIGN_CENTER);
                qrImage.setSpacingBefore(10f);
                document.add(qrImage);

                // Add footer
                Paragraph footer = new Paragraph("Copyright © 2023 Bee Shoes\nAll rights reserved", boldFont);
                footer.setAlignment(Element.ALIGN_CENTER);
                footer.setSpacingBefore(20f);
                document.add(footer);

                // Close the document
                document.close();
                System.out.println("PDF created at: " + outputPath);
            } catch (DocumentException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_denNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_denNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_denNgayActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton QR;
    private javax.swing.JButton btn_xuat;
    private com.Product.GUI.Combobox cb_httt;
    private com.Product.GUI.Combobox cbox_hoaDon;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.Product.GUI.Table tb_hd;
    private com.Product.GUI.Table tb_hdct;
    private com.Product.GUI.Table tb_lshd;
    private com.Product.GUI.textfield.TextField txtSearch1;
    private com.Product.GUI.textfield.TextField txt_denNgay;
    private com.Product.GUI.textfield.TextField txt_timTheoGia;
    private com.Product.GUI.textfield.TextField txt_timTheoGiaMax;
    private com.Product.GUI.textfield.TextField txt_tuNgay;
    // End of variables declaration//GEN-END:variables
}
