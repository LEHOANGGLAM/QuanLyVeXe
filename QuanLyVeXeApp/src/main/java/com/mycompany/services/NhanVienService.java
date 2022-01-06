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
    
    public void insertNhanVien(NhanVien nv) throws SQLException{
        String sql = "INSERT INTO nhanvien(MaNhanVien, TenNhanVien, MaLoaiNhanVien, NgaySinh, SoDienThoai, CMND, QueQuan)" 
                   + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setString(1, nv.getMaNhanVien());
            stm.setString(2, nv.getTenNhanVien());
            stm.setInt(3, nv.getMaLoaiNhanVien());
            stm.setDate(4, nv.getNgaySinh());
            stm.setString(5, nv.getSoDienThoai());
            stm.setString(6, nv.getCMND());
            stm.setString(7, nv.getQueQuan());
            stm.executeUpdate();
        }    
    }
    
    public void updateNhanVien(NhanVien nv) throws SQLException{
        String sql = "UPDATE nhanvien SET TenNhanVien = ?, MaLoaiNhanVien = ?, NgaySinh = ?, SoDienThoai = ?, CMND = ?, QueQuan = ? WHERE MaNhanVien = ?";
        
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall(sql);
            
            stm.setString(1, nv.getMaNhanVien());
            stm.setString(2, nv.getTenNhanVien());
            stm.setInt(3, nv.getMaLoaiNhanVien());
            stm.setDate(4, nv.getNgaySinh());
            stm.setString(5, nv.getSoDienThoai());
            stm.setString(6, nv.getCMND());
            stm.setString(7, nv.getQueQuan());
            stm.executeUpdate();
        } 
    }
       
    public void deleteNhanVien(NhanVien nv) throws SQLException{
        String sql = "DELETE FROM nhanvien WHERE MaNhanVien = ?";
        
        try ( Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, nv.getMaNhanVien());


            stm.executeUpdate();           
        
            conn.commit();
        }   
     
    }
    
        
    public boolean isMaNhanVienExist(String MaNhanVien) throws SQLException{
        String sql = "SELECT MaNhanVien FROM nhanvien WHERE MaNhanVien = ?";
        
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall(sql);
            
            stm.setString(1, MaNhanVien);
            
            ResultSet rs = stm.executeQuery();
            return rs.next();
               
        }
    }
}
