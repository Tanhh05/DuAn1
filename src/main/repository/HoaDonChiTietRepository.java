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
        String sql = "SELECT dbo.SanPhamChiTiet.id, dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, dbo.ThuongHieu.ten_thuong_hieu, "
                + "dbo.XuatXu.ten_nuoc, dbo.MauSac.ten_mau, dbo.KichThuoc.size, dbo.ChatLieu.ten_chat_lieu, dbo.CoAo.ten_co_ao, "
                + "dbo.DoDay.ten_do_day, dbo.TinhLinhHoat.ten_tinh_linh_hoat, dbo.SanPhamChiTiet.gia_ban, dbo.SanPhamChiTiet.so_luong_ton, "
                + "dbo.SanPhamChiTiet.trang_thai "
                + "FROM dbo.SanPhamChiTiet "
                + "INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id "
                + "INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id "
                + "INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id "
                + "INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id "
                + "INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id "
                + "INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id "
                + "INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id "
                + "INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id";

        ArrayList<HoaDonChiTietReponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new HoaDonChiTietReponse(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getDouble(11),
                        rs.getInt(12), rs.getBoolean(13)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }

    public ArrayList<HoaDonChiTietReponse> getByIdHoaDon(Integer idHD) {
        String sql = "SELECT dbo.SanPhamChiTiet.id, dbo.SanPhamChiTiet.ma_san_pham_chi_tiet, dbo.ThuongHieu.ten_thuong_hieu,  \n"
                + "    dbo.XuatXu.ten_nuoc, dbo.MauSac.ten_mau, dbo.KichThuoc.size, dbo.ChatLieu.ten_chat_lieu, dbo.CoAo.ten_co_ao,  \n"
                + "    dbo.DoDay.ten_do_day, dbo.TinhLinhHoat.ten_tinh_linh_hoat, dbo.SanPhamChiTiet.gia_ban, dbo.SanPhamChiTiet.so_luong_ton,  \n"
                + "    dbo.SanPhamChiTiet.trang_thai  \n"
                + "    FROM dbo.SanPhamChiTiet  \n"
                + "	INNER JOIN dbo.HoaDonChiTiet ON dbo.SanPhamChiTiet.id = dbo.HoaDonChiTiet.id_san_pham_chi_tiet\n"
                + "    INNER JOIN dbo.ThuongHieu ON dbo.SanPhamChiTiet.id_thuong_hieu = dbo.ThuongHieu.id  \n"
                + "    INNER JOIN dbo.XuatXu ON dbo.SanPhamChiTiet.id_xuat_xu = dbo.XuatXu.id  \n"
                + "    INNER JOIN dbo.MauSac ON dbo.SanPhamChiTiet.id_mau_sac = dbo.MauSac.id  \n"
                + "    INNER JOIN dbo.KichThuoc ON dbo.SanPhamChiTiet.id_kich_thuoc = dbo.KichThuoc.id  \n"
                + "    INNER JOIN dbo.ChatLieu ON dbo.SanPhamChiTiet.id_chat_lieu = dbo.ChatLieu.id  \n"
                + "    INNER JOIN dbo.CoAo ON dbo.SanPhamChiTiet.id_co_ao = dbo.CoAo.id  \n"
                + "    INNER JOIN dbo.DoDay ON dbo.SanPhamChiTiet.id_do_day = dbo.DoDay.id  \n"
                + "    INNER JOIN dbo.TinhLinhHoat ON dbo.SanPhamChiTiet.id_tinh_linh_hoat = dbo.TinhLinhHoat.id\n"
                + "        WHERE dbo.hoadonchitiet.id_hoa_don = ?";

        ArrayList<HoaDonChiTietReponse> lists = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHD);// l?
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lists.add(new HoaDonChiTietReponse(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getDouble(11),
                        rs.getInt(12), rs.getBoolean(13)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out); // nem loi khi xay ra 
        }
        return lists;
    }
}
