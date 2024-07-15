/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.response.HoaDonResponse;
//
/**
 *
 * @author ADMIN
 */

public class HoaDonRepository {    
    public ArrayList<HoaDonResponse> getAll(){
        String sql="SELECT  dbo.HoaDon.id, dbo.HoaDon.ma_hoa_don, dbo.HoaDon.ngay_tao, dbo.HoaDon.ngay_cap_nhat, dbo.HoaDon.tong_tien, dbo.NhanVien.ma_nhan_vien, dbo.KhachHang.ho_ten, dbo.KhachHang.dia_chi, dbo.KhachHang.so_dien_thoai, \n" +
"                 dbo.HoaDon.trang_thai\n" +
"FROM      dbo.HoaDon INNER JOIN\n" +
"                 dbo.NhanVien ON dbo.HoaDon.id_nhan_vien = dbo.NhanVien.id INNER JOIN\n" +
"                 dbo.KhachHang ON dbo.HoaDon.id_khach_hang = dbo.KhachHang.id";
        
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
                                .tongTien(rs.getString(5))
                                .maNhanVien(rs.getString(6))
                                .hoTen(rs.getString(7))
                                .diaChi(rs.getString(8))
                                .SDT(rs.getString(9))
                                .trangThai(rs.getString(10))
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
    
}
