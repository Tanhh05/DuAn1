/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;
import main.config.DBConnect;
import main.entity.SanPhamChiTiet;
import com.Product.form.NhanVienForm;
import main.response.SanPhamChiTietRespone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import main.response.DanhSachSanPham;
/**
 *
 * @author ADMIN
 */
public class DanhSachSPRepository {
    public ArrayList<DanhSachSanPham> getAll() {
        String sql = "SELECT  dbo.SanPhamChiTiet.id, dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, dbo.SanPham.ten_san_pham, dbo.TinhLinhHoat.ten_tinh_linh_hoat, dbo.XuatXu.ten_nuoc, dbo.KichThuoc.size, dbo.SanPhamChiTiet.so_luong_ton, dbo.SanPhamChiTiet.gia_ban\n" +
"FROM      dbo.SanPhamChiTiet INNER JOIN\n" +
"                 dbo.SanPham ON dbo.SanPhamChiTiet.id_san_pham = dbo.SanPham.id INNER JOIN\n" +
"                 dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id INNER JOIN\n" +
"                 dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id INNER JOIN\n" +
"                 dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id";

        ArrayList<DanhSachSanPham> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new DanhSachSanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7),rs.getDouble(8)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
}
