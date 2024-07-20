package com.Product.form;

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


public class HoaDonForm extends javax.swing.JPanel {

    private DefaultTableModel dtm;
    
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
        
        dtmHoaDonChiTiet =(DefaultTableModel) tb_hdct.getModel();
        
        lshdRepo = new LichSuHoaDonRepository();
        
        dtmLichSuHoaDon = (DefaultTableModel) tb_lshd.getModel();
        
        
    }
    
//    public void close(){
//        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
//        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
//    }
    
    private void showDataTable(ArrayList<HoaDonResponse> lists) {
        dtm.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1); // Khoi tao 1 gia tri bat dau bang 1 de tu dong tang
        // for..each + lamda 
        lists.forEach(s -> dtm.addRow(new Object[]{
            index.getAndIncrement(),s.getMaHoaDon(),s.getNgayTao(),s.getNgayCapNhap(),s.getTongTien(),s.getMaNhanVien(),
            s.getHoTen(),s.getDiaChi(),s.getSDT(),s.getTrangThai()== 0 ? "Đã Thanh Toán" : "Chưa thanh toán",s.getHinhThucTT()==0 ? "Tiền Mặt":"Chuyển Khoản"
        }));
    }
    
    private void showTableHoaDonChiTiet(ArrayList<HoaDonChiTietReponse> lists) {
        dtmHoaDonChiTiet.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmHoaDonChiTiet.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSPCT(),s.getTenSPCT(),s.getSoLuong(),s.getTongTien()
        }));
    }
    
    private void showTableLichSuHoaDon(ArrayList<LichSuHoaDonResponse> lists) {
        dtmLichSuHoaDon.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmLichSuHoaDon.addRow(new Object[]{
            index.getAndIncrement(), s.getMaNV(),s.getNgayCapNhap(),s.getTrangThai()== 0 ? "Đã Thanh Toán" : "Chưa thanh toán"
        }));
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        QR = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_hd = new com.Product.GUI.Table();
        jButton3 = new javax.swing.JButton();
        btn_xuat = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
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
        txtSearch = new com.Product.GUI.textfield.TextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Hóa đơn");

        QR.setBackground(new java.awt.Color(255, 255, 153));
        QR.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        QR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/qr.png"))); // NOI18N
        QR.setText("Quét QR");
        QR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QRActionPerformed(evt);
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

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/hoadonmoi.png"))); // NOI18N
        jButton3.setText("In Hóa Đơn");

        btn_xuat.setBackground(new java.awt.Color(255, 255, 153));
        btn_xuat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/xuatfile.png"))); // NOI18N
        btn_xuat.setText("Xuất Excel");
        btn_xuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Product/icon/lammoi.png"))); // NOI18N
        jButton5.setText("Làm Mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 204), 3));

        tb_hdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Ma SPCT", "Ten San Pham", "So Luong", "Tong Tien"
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        cbox_hoaDon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đã Thanh Toán", "Chưa Thanh Toán" }));
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

        txtSearch.setLabelText("Tìm kiếm Tại Ðây");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_timTheoGia, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbox_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_timTheoGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_tuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txt_denNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_httt, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_httt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(cbox_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel4))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel2)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_timTheoGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_denNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_timTheoGiaMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.getAccessibleContext().setAccessibleName("Tìm kiếm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(jButton3)
                .addGap(144, 144, 144)
                .addComponent(btn_xuat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(244, 244, 244))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5)
                                .addGap(216, 216, 216)
                                .addComponent(QR)
                                .addGap(55, 55, 55))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(QR)
                            .addComponent(jButton5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_xuat)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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
        txtSearch.setText("");
        txt_timTheoGia.setText("");
        txt_timTheoGiaMax.setText("");
        txt_tuNgay.setText("");
        txt_denNgay.setText("");
        showDataTable(hdRepo.getAll());
    }//GEN-LAST:event_jButton5ActionPerformed

    
    private void cbox_hoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_hoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_hoaDonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
       try {
            String keyword = txtSearch.getText().trim();
            Integer trangThai = cbox_hoaDon.getSelectedIndex();
            Integer httt = cb_httt.getSelectedIndex();

            // Get giaMin
            Double giaMin = null;
            String giaMinText = txt_timTheoGia.getText().trim();
            if (!giaMinText.isEmpty()) {
                giaMin = Double.parseDouble(giaMinText);
            }

            // Get giaMax
            Double giaMax = null;
            String giaMaxText = txt_timTheoGiaMax.getText().trim();
            if (!giaMaxText.isEmpty()) {
                giaMax = Double.parseDouble(giaMaxText);
            }

            // Get startDate
            LocalDate startDate = null;
            String ngayBatDau = txt_tuNgay.getText().trim();
            if (!ngayBatDau.isEmpty()) {
                try {
                    startDate = LocalDate.parse(ngayBatDau, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (DateTimeParseException e) {
                    throw new DateTimeParseException("Invalid date format", ngayBatDau, 0, e);
                }
            }

            // Get endDate
            LocalDate endDate = null;
            String ngayKetThuc = txt_denNgay.getText().trim();
            if (!ngayKetThuc.isEmpty()) {
                try {
                    endDate = LocalDate.parse(ngayKetThuc, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (DateTimeParseException e) {
                    throw new DateTimeParseException("Invalid date format", ngayKetThuc, 0, e);
                }
            }

            // Call the search method with inputs
            showDataTable(hdRepo.search(keyword, trangThai, httt, giaMin, giaMax, startDate != null ? startDate.toString() : null, endDate != null ? endDate.toString() : null));
            showDataTable(hdRepo.searchh(txtSearch.getText()));
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ cho phạm vi giá.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ. Vui lòng nhập ngày ở định dạng yyyy-MM-dd.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tìm kiếm. Vui lòng thử lại.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        


    }//GEN-LAST:event_jButton6ActionPerformed

    private void txt_denNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_denNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_denNgayActionPerformed

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
                row.createCell(9, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getTrangThai()== 0 ? "Đã Thanh Toán" : "Chưa thanh toán"); // Trạng Thái
                row.createCell(10, XSSFCell.CELL_TYPE_STRING).setCellValue(hoaDon.getHinhThucTT()==0 ? "Tiền Mặt":"Chuyển Khoản"); // Hình Thức Thanh Toán

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

    private void tb_hdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hdMouseClicked
        // TODO add your handling code here:
        int id = hdRepo.getAll().get(tb_hd.getSelectedRow()).getId();
        showTableHoaDonChiTiet(hdctRepo.getAll());
        showTableLichSuHoaDon(lshdRepo.getAll());
    }//GEN-LAST:event_tb_hdMouseClicked

    private void QRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QRActionPerformed
        // TODO add your handling code here:
        Menu menu= new Menu();
        menu.setVisible(true);
        
    }//GEN-LAST:event_QRActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton QR;
    private javax.swing.JButton btn_xuat;
    private com.Product.GUI.Combobox cb_httt;
    private com.Product.GUI.Combobox cbox_hoaDon;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private com.Product.GUI.textfield.TextField txtSearch;
    private com.Product.GUI.textfield.TextField txt_denNgay;
    private com.Product.GUI.textfield.TextField txt_timTheoGia;
    private com.Product.GUI.textfield.TextField txt_timTheoGiaMax;
    private com.Product.GUI.textfield.TextField txt_tuNgay;
    // End of variables declaration//GEN-END:variables
}
