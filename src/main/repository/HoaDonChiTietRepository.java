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
import main.response.HoaDonChiTietReponse;
import main.response.HoaDonResponse;

/**
 *
 * @author ADMIN
 */
public class HoaDonChiTietRepository {
    public ArrayList<HoaDonChiTietReponse> getAll() {
        String sql = "SELECT  dbo.HoaDonChiTiet.id, dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, dbo.SanPhamChiTiet.ten_san_pham, dbo.SanPhamChiTiet.so_luong_ton, dbo.HoaDon.tong_tien\n" +
"FROM      dbo.SanPhamChiTiet INNER JOIN\n" +
"                 dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id INNER JOIN\n" +
"                 dbo.HoaDonChiTiet ON dbo.SanPhamChiTiet.id = dbo.HoaDonChiTiet.id_san_pham_chi_tiet INNER JOIN\n" +
"                 dbo.HoaDon ON dbo.SanPham.id = dbo.HoaDon.id_san_pham AND dbo.HoaDonChiTiet.id_hoa_don = dbo.HoaDon.id";
        ArrayList<HoaDonChiTietReponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setObject(1, hoaDonID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietReponse response = HoaDonChiTietReponse.builder()
                        .id(rs.getInt(1))
                        .maSPCT(rs.getString(2))
                        .tenSPCT(rs.getString(3))
                        .soLuong(rs.getInt(4))
                        .tongTien(rs.getDouble(5))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
}
