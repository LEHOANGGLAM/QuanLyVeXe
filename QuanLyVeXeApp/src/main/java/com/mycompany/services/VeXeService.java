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
                            "WHERE vexe.MaChuyenDi = chuyendi.MaChuyenDi and ThoiGianKhoiHanh> DATE(NOW())\n" +
                            " ORDER BY vexe.MaChuyenDi");

            while (rs.next()) {
                VeXe v = new VeXe(rs.getString("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getInt("SoDienThoai"),
                        rs.getString("MaChuyenDi"), rs.getString("ViTriGhe"), rs.getInt("TrangThai"));
                results.add(v);
            }
        }
        return results;
    }

//    public List<VeXe> getVeXeByMaChuyenDi(String MaChuyenDi) throws SQLException {
//        List<VeXe> results = new ArrayList<>();
//        try ( Connection conn = jdbcUtils.getConn()) {
//            PreparedStatement stm = conn.prepareCall("SELECT * FROM vexe WHERE MaChuyenDi = ?");
//            stm.setString(1, MaChuyenDi);
//
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                VeXe v = new VeXe(rs.getString("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getInt("SoDienThoai"),
//                        rs.getString("MaChuyenDi"), rs.getString("ViTriGhe"), rs.getInt("TrangThai"));
//                results.add(v);
//            }
//        }
//        return results;
//    }

    public void deleteVeXe(String MaVe) throws SQLException {
        try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("DELETE FROM vexe WHERE MaVe = ?");
            stm.setString(1, MaVe);
            stm.executeUpdate();
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
                VeXe v = new VeXe(rs.getString("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getInt("SoDienThoai"),
                        rs.getString("MaChuyenDi"), rs.getString("ViTriGhe"), rs.getInt("TrangThai"));
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
                VeXe v = new VeXe(rs.getString("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getInt("SoDienThoai"),
                        rs.getString("MaChuyenDi"), rs.getString("ViTriGhe"), rs.getInt("TrangThai"));
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
                VeXe v = new VeXe(rs.getString("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getInt("SoDienThoai"),
                        rs.getString("MaChuyenDi"), rs.getString("ViTriGhe"), rs.getInt("TrangThai"));
                results.add(v);
            }
        }
        return results;
    }

    public void addVeXe(VeXe v) throws SQLException {
        String sql = "INSERT INTO vexe(MaChuyenDi, TenKhachHang, SoDienThoai, NgayDat, ViTriGhe, TrangThai,MaVe)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        addOrUpdateVeXe(v, sql);
    }
    
    public void updateVeXe(VeXe v) throws SQLException {
        String sql = "UPDATE vexe SET MaChuyenDi = ?, TenKhachHang= ?, SoDienThoai = ?, NgayDat=?, "
                    + "ViTriGhe = ?, TrangThai = ? WHERE MaVe = ?";
        addOrUpdateVeXe(v, sql);
    }
    
    public void addOrUpdateVeXe(VeXe v, String sql) throws SQLException {
        try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, v.getMaChuyenDi());
            stm.setString(2, v.getTenKhachHang());
            stm.setInt(3, v.getSdt());
            stm.setDate(4, v.getNgayDat());
            stm.setString(5, v.getViTriGhe());
            stm.setInt(6, v.getTrangThai());
            stm.setString(7, v.getMaVe());

            stm.executeUpdate();
        }
    }
    
    public void updateSellVeXe(VeXe v) throws SQLException{
            String sql = "UPDATE vexe SET TrangThai = ? WHERE MaVe = ?";
            try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall(sql);

            stm.setInt(1, v.getTrangThai());
            stm.setString(2, v.getMaVe());
            stm.executeUpdate();
        }
    }
}
