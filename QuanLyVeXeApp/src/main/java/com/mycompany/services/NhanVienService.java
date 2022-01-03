/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienService {
    public List<NhanVien> getNhanVien() throws SQLException{
        List<NhanVien> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien ORDER BY MaNhanVien");
            
            while (rs.next()){
                NhanVien nv = new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getInt("MaLoaiNhanVien"),
                                           rs.getDate("NgaySinh"), rs.getString("SoDienThoai"), rs.getString("CMND"), rs.getString("QueQuan"));
                results.add(nv);
            }
        }
        return results;
    }
    
    public List<NhanVien> getNhanVienByMaNV(String maNV) throws SQLException{
        List<NhanVien> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM nhanvien WHERE MaNhanVien LIKE concat('%', '" + maNV + "', '%')";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getInt("MaLoaiNhanVien"),
                                           rs.getDate("NgaySinh"), rs.getString("SoDienThoai"), rs.getString("CMND"), rs.getString("QueQuan"));
                results.add(nv);
            }
        }
        return results;
    }
    
    public List<NhanVien> getNhanVienByTen(String ten) throws SQLException{
        List<NhanVien> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM nhanvien WHERE TenNhanVien LIKE concat('%', '" + ten + "', '%')";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getInt("MaLoaiNhanVien"),
                                           rs.getDate("NgaySinh"), rs.getString("SoDienThoai"), rs.getString("CMND"), rs.getString("QueQuan"));
                results.add(nv);
            }
        }
        return results;
    }
    
    public List<NhanVien> getNhanVienByMaLoaiNV(String maLoaiNV) throws SQLException{
        List<NhanVien> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM nhanvien WHERE MaLoaiNhanVien LIKE concat('%', '" + Integer.parseInt(maLoaiNV) + "', '%')";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"), rs.getInt("MaLoaiNhanVien"),
                                           rs.getDate("NgaySinh"), rs.getString("SoDienThoai"), rs.getString("CMND"), rs.getString("QueQuan"));
                results.add(nv);
            }
        }
        return results;
    }
}
