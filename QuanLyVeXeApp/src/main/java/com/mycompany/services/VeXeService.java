/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.ChuyenDi;
import com.mycompany.pojo.VeXe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class VeXeService {

    public List<VeXe> getVeXe() throws SQLException {
        List<VeXe> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM vexe, chuyendi \n" +
                            "WHERE vexe.MaChuyenDi = chuyendi.MaChuyenDi and NgayKhoiHanh >= DATE(NOW())\n" +
                            " ORDER BY vexe.MaChuyenDi");

            while (rs.next()) {
                VeXe v = new VeXe(rs.getString("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getString("SoDienThoai"),
                        rs.getString("MaChuyenDi"), rs.getString("ViTriGhe"), rs.getString("TrangThai"));
                results.add(v);
            }
        }
        return results;
    }

    public void deleteVeXe(VeXe v) throws SQLException {
        try ( Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
            PreparedStatement stm = conn.prepareCall("DELETE FROM vexe WHERE MaVe = ?");
            stm.setString(1, v.getMaVe());
            stm.executeUpdate();
            
            String sql = "UPDATE chuyendi SET SoGheTrong = SoGheTrong+1, SoGheDat = SoGheDat-1 WHERE MaChuyenDi = ?";     
            PreparedStatement stm2 = conn.prepareCall(sql);
            stm2.setString(1, v.getMaChuyenDi());
            stm2.executeUpdate();
        
            conn.commit();
        }
    }

    public String getMaChuyenDiByMaVe(String MaVe) throws SQLException {
        String result = null;
        try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("SELECT MaChuyenDi FROM vexe WHERE MaVe = ?");
            stm.setString(1, MaVe);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                result = rs.getString("MaChuyenDi");
            }
        }
        return result;
    }

    public List<VeXe> getVeXeByMaCD(String kw) throws SQLException {
        List<VeXe> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM vexe WHERE MaChuyenDi like concat('%', '" + kw + "', '%')";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                VeXe v = new VeXe(rs.getString("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getString("SoDienThoai"),
                        rs.getString("MaChuyenDi"), rs.getString("ViTriGhe"), rs.getString("TrangThai"));
                results.add(v);
            }
        }
        return results;
    }

    public List<VeXe> getVeXeByMaTen(String kw) throws SQLException {
        List<VeXe> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM vexe WHERE TenKhachHang like concat('%', '" + kw + "', '%')";
            PreparedStatement stm = conn.prepareStatement(sql);

            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                VeXe v = new VeXe(rs.getString("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getString("SoDienThoai"),
                        rs.getString("MaChuyenDi"), rs.getString("ViTriGhe"), rs.getString("TrangThai"));
                results.add(v);
            }
        }
        return results;
    }

    public List<VeXe> getVeXeByMaSDT(String kw) throws SQLException {
        List<VeXe> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM vexe WHERE SoDienThoai like concat('%', '" + kw + "', '%')";
            PreparedStatement stm = conn.prepareStatement(sql);
          
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                VeXe v = new VeXe(rs.getString("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getString("SoDienThoai"),
                        rs.getString("MaChuyenDi"), rs.getString("ViTriGhe"), rs.getString("TrangThai"));
                results.add(v);
            }
        }
        return results;
    }

    public void addVeXe(VeXe v) throws SQLException {
        String sql = "INSERT INTO vexe(MaChuyenDi, TenKhachHang, SoDienThoai, NgayDat, ViTriGhe, TrangThai,MaVe)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";
          try ( Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
              
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, v.getMaChuyenDi());
            stm.setString(2, v.getTenKhachHang());
            stm.setString(3, v.getSdt());
            stm.setDate(4, v.getNgayDat());
            stm.setString(5, v.getViTriGhe());
            stm.setString(6, v.getTrangThai());
            stm.setString(7, v.getMaVe());

            stm.executeUpdate();
            
            //khi thêm thì số ghế trống của chuyến đi sẽ giảm 1 và số ghế đặt tăng 1
            String sql2 = "UPDATE chuyendi SET SoGheTrong = SoGheTrong-1, SoGheDat = SoGheDat +1 WHERE MaChuyenDi = ?";
            PreparedStatement stm2 = conn.prepareCall(sql2);
            stm2.setString(1, v.getMaChuyenDi());
            stm2.executeUpdate();
            
            conn.commit();
        }
    }
    
    public void updateVeXe(VeXe v) throws SQLException {
        String sql = "UPDATE vexe SET MaChuyenDi = ?, TenKhachHang= ?, SoDienThoai = ?, NgayDat=?, "
                    + "ViTriGhe = ?, TrangThai = ? WHERE MaVe = ?";
          try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, v.getMaChuyenDi());
            stm.setString(2, v.getTenKhachHang());
            stm.setString(3, v.getSdt());
            stm.setDate(4, v.getNgayDat());
            stm.setString(5, v.getViTriGhe());
            stm.setString(6, v.getTrangThai());
            stm.setString(7, v.getMaVe());

            stm.executeUpdate();
        }
    }
    
    public void updateSellVeXe(VeXe v) throws SQLException{
            String sql = "UPDATE vexe SET TrangThai = ? WHERE MaVe = ?";
            try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, v.getTrangThai());
            stm.setString(2, v.getMaVe());
            stm.executeUpdate();
        }
    }
}
