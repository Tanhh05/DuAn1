/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import main.config.DBConnect;
import main.entity.HoaDon;
import main.response.HoaDonResponse;
import util.Helper;
import java.util.Date;
//
/**
 *
 * @author ADMIN
 */

public class HoaDonRepository {

    public ArrayList<HoaDonResponse> getAll() {
        String sql = "SELECT  dbo.HoaDon.id, dbo.HoaDon.ma_hoa_don, dbo.HoaDon.ngay_tao, dbo.HoaDon.ngay_cap_nhat, dbo.HoaDon.tong_tien, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ho_ten, dbo.KhachHang.dia_chi, dbo.KhachHang.so_dien_thoai, dbo.HoaDon.trang_thai, \n"
                + "                 dbo.HoaDon.hinh_thuc_thanh_toan\n"
                + "FROM      dbo.HoaDon INNER JOIN\n"
                + "                 dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id INNER JOIN\n"
                + "                 dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id";

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, hoaDonID);
            ResultSet rs = ps.executeQuery();

            ArrayList<HoaDonResponse> lists = new ArrayList<>();
            while (rs.next()) {
                HoaDonResponse response
                        = HoaDonResponse.builder()
                                .id(rs.getInt(1))
                                .maHoaDon(rs.getString(2))
                                .ngayTao(rs.getString(3))
                                .ngayCapNhap(rs.getString(4))
                                .tongTien(rs.getDouble(5))
                                .maNhanVien(rs.getString(6))
                                .hoTen(rs.getString(7))
                                .diaChi(rs.getString(8))
                                .SDT(rs.getString(9))
                                .trangThai(rs.getInt(10))
                                .hinhThucTT(rs.getInt(11))
                                .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    
    public ArrayList<HoaDonResponse> searchh(String maHoaDon) {
    String sql = "SELECT  dbo.HoaDon.id, dbo.HoaDon.ma_hoa_don, dbo.HoaDon.ngay_tao, dbo.HoaDon.ngay_cap_nhat, dbo.HoaDon.tong_tien, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ho_ten, dbo.KhachHang.dia_chi, dbo.KhachHang.so_dien_thoai, dbo.HoaDon.trang_thai, \n"
            + "                 dbo.HoaDon.hinh_thuc_thanh_toan\n"
            + "FROM      dbo.HoaDon INNER JOIN\n"
            + "                 dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id INNER JOIN\n"
            + "                 dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id \n"
            + "WHERE dbo.HoaDon.ma_hoa_don LIKE ? \n"
            + "OR dbo.HoaDon.ngay_tao LIKE ? \n"
            + "OR dbo.HoaDon.ngay_cap_nhat LIKE ? \n"
            + "OR dbo.HoaDon.tong_tien LIKE ? \n"
            + "OR dbo.NhanVien.ma_nhan_vien LIKE ? \n"
            + "OR dbo.KhachHang.ho_ten LIKE ? \n"
            + "OR dbo.KhachHang.dia_chi LIKE ? \n"
            + "OR dbo.KhachHang.so_dien_thoai LIKE ? \n"
            + "OR dbo.HoaDon.trang_thai LIKE ? \n"
            + "OR dbo.HoaDon.hinh_thuc_thanh_toan LIKE ?";

    ArrayList<HoaDonResponse> lists = new ArrayList<>();
    try (Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
        String searchString = "%" + maHoaDon + "%";
        for (int i = 1; i <= 10; i++) {
            ps.setString(i, searchString);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lists.add(new HoaDonResponse(
                    rs.getInt(1), rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getInt(10),
                    rs.getInt(11)
            ));
        }
    } catch (Exception e) {
        e.printStackTrace(System.out); // Xử lý lỗi khi xảy ra
    }
    return lists;
}


    
    public ArrayList<HoaDonResponse> search(String keyword, Integer trangThai, Integer httt, Double giaMin, Double giaMax, String ngayBatDau, String ngayKetThuc) {
    String sql = "SELECT " +
            "dbo.HoaDon.id, " +
            "dbo.HoaDon.ma_hoa_don, " +
            "dbo.HoaDon.ngay_tao, " +
            "dbo.HoaDon.ngay_cap_nhat, " +
            "dbo.HoaDon.tong_tien, " +
            "dbo.NhanVien.ma_nhan_vien, " +
            "dbo.KhachHang.ho_ten, " +
            "dbo.KhachHang.dia_chi, " +
            "dbo.KhachHang.so_dien_thoai, " +
            "dbo.HoaDon.trang_thai, " +
            "dbo.HoaDon.hinh_thuc_thanh_toan " +
            "FROM dbo.HoaDon " +
            "INNER JOIN dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id " +
            "INNER JOIN dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id " +
            "WHERE 1=1"; // Start with a dummy condition that will always be true

    // Append conditions for mandatory parameters
    sql += " AND dbo.HoaDon.tong_tien BETWEEN ? AND ?";
    
    // Append conditions for optional parameters
    if (trangThai != null) {
        sql += " AND dbo.HoaDon.trang_thai = ?";
    }
    if (httt != null) {
        sql += " AND dbo.HoaDon.hinh_thuc_thanh_toan = ?";
    }
    if (ngayBatDau != null) {
        sql += " AND dbo.HoaDon.ngay_tao >= ?";
    }
    if (ngayKetThuc != null) {
        sql += " AND dbo.HoaDon.ngay_tao <= ?";
    }
    if (keyword != null && !keyword.isEmpty()) {
        sql += " AND (dbo.HoaDon.ma_hoa_don LIKE ? OR dbo.NhanVien.ma_nhan_vien LIKE ? OR dbo.KhachHang.ho_ten LIKE ? OR dbo.KhachHang.dia_chi LIKE ? OR dbo.KhachHang.so_dien_thoai LIKE ?)";
    }

    ArrayList<HoaDonResponse> lists = new ArrayList<>();
    try (Connection con = DBConnect.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        int index = 1; // Start index for setting parameters

        // Set mandatory parameters
        ps.setDouble(index++, giaMin != null ? giaMin : 0.0); // Set a default value or handle differently if needed
        ps.setDouble(index++, giaMax != null ? giaMax : Double.MAX_VALUE); // Set a default value or handle differently if needed

        // Set optional parameters based on conditions
        if (trangThai != null) {
            ps.setInt(index++, trangThai);
        }
        if (httt != null) {
            ps.setInt(index++, httt);
        }
        if (ngayBatDau != null) {
            ps.setDate(index++, java.sql.Date.valueOf(ngayBatDau));
        }
        if (ngayKetThuc != null) {
            ps.setDate(index++, java.sql.Date.valueOf(ngayKetThuc));
        }
        if (keyword != null && !keyword.isEmpty()) {
            String value = "%" + keyword + "%";
            ps.setString(index++, value); // LIKE for ma_hoa_don
            ps.setString(index++, value); // LIKE for ma_nhan_vien
            ps.setString(index++, value); // LIKE for ho_ten
            ps.setString(index++, value); // LIKE for dia_chi
            ps.setString(index++, value); // LIKE for so_dien_thoai
        }

        // Execute query and process results
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonResponse response = new HoaDonResponse();
                response.setId(rs.getInt("id"));
                response.setMaHoaDon(rs.getString("ma_hoa_don"));
                response.setNgayTao(rs.getString("ngay_tao"));
                response.setNgayCapNhap(rs.getString("ngay_cap_nhat"));
                response.setTongTien(rs.getDouble("tong_tien"));
                response.setMaNhanVien(rs.getString("ma_nhan_vien"));
                response.setHoTen(rs.getString("ho_ten"));
                response.setDiaChi(rs.getString("dia_chi"));
                response.setSDT(rs.getString("so_dien_thoai"));
                response.setTrangThai(rs.getInt("trang_thai"));
                response.setHinhThucTT(rs.getInt("hinh_thuc_thanh_toan"));
                lists.add(response);
            }
        }
    } catch (Exception e) {
        e.printStackTrace(); // Handle SQL exception
    }
    return lists;
}


   
}
