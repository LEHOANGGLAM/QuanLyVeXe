/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.ChuyenDi;
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
public class ChuyenDiService {

    public List<ChuyenDi> getChuyenDi() throws SQLException {
        List<ChuyenDi> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM chuyendi ORDER BY MaXe");

            while (rs.next()) {
                ChuyenDi c = new ChuyenDi(rs.getString("MaChuyenDi"), rs.getString("MaXe"), rs.getInt("GiaVe"), rs.getDate("NgayKhoiHanh"), rs.getTime("GioKhoiHanh"),
                        rs.getString("DiemKhoiHanh"), rs.getString("DiemKetThuc"), rs.getInt("SoGheTrong"), rs.getInt("SoGheDat"));
                results.add(c);
            }
        }
        return results;
    }

    public List<ChuyenDi> getChuyenDiSortByDate() throws SQLException {
        List<ChuyenDi> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM chuyendi WHERE NgayKhoiHanh >= DATE(NOW())");

            while (rs.next()) {
                ChuyenDi c = new ChuyenDi(rs.getString("MaChuyenDi"), rs.getString("MaXe"), rs.getInt("GiaVe"),  rs.getDate("NgayKhoiHanh"), rs.getTime("GioKhoiHanh"),
                        rs.getString("DiemKhoiHanh"), rs.getString("DiemKetThuc"), rs.getInt("SoGheTrong"), rs.getInt("SoGheDat"));
                results.add(c);
            }
        }
        return results;
    }
   

    //////////////loiii
    public List<ChuyenDi> getChuyenDiByKw(String kw) throws SQLException {
        List<ChuyenDi> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
             //String sql = "SELECT * FROM chuyendi WHERE DiemKhoiHanh like concat('%', '?', '%')"; 
            String sql = "SELECT * FROM chuyendi WHERE DiemKhoiHanh like concat('%', '" + kw + "', '%') or \n"
                    + "DiemKetThuc like concat('%', '" + kw + "', '%') or GiaVe like concat('%', '" + kw + "', '%') or"
                    + " NgayKhoiHanh like concat('%', '" + kw + "', '%') or MaXe like concat('%', '" + kw + "', '%')";

            PreparedStatement stm = conn.prepareStatement(sql);
            // Làm theo cách này bị lỗi???
//          if(kw != null && !kw.isEmpty()){
//             stm.setString(1, kw);
//          }
          
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ChuyenDi c = new ChuyenDi(rs.getString("MaChuyenDi"), rs.getString("MaXe"), rs.getInt("GiaVe"),  rs.getDate("NgayKhoiHanh"), rs.getTime("GioKhoiHanh"),
                        rs.getString("DiemKhoiHanh"), rs.getString("DiemKetThuc"), rs.getInt("SoGheTrong"), rs.getInt("SoGheDat"));
                results.add(c);
            }
        }
        return results;
    }
    
    public List<ChuyenDi> getChuyenDiByKwAndSortDate(String kw) throws SQLException {
        List<ChuyenDi> results = new ArrayList<>();
        try ( Connection conn = jdbcUtils.getConn()) {
                String sql = "SELECT * FROM chuyendi WHERE NgayKhoiHanh >=DATE(NOW()) and (DiemKhoiHanh like concat('%', '" + kw + "', '%') or \n"
                    + "DiemKetThuc like concat('%', '" + kw + "', '%'))";
            PreparedStatement stm = conn.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                ChuyenDi c = new ChuyenDi(rs.getString("MaChuyenDi"), rs.getString("MaXe"), rs.getInt("GiaVe"), rs.getDate("NgayKhoiHanh"), rs.getTime("GioKhoiHanh"),
                        rs.getString("DiemKhoiHanh"), rs.getString("DiemKetThuc"), rs.getInt("SoGheTrong"), rs.getInt("SoGheDat"));
                results.add(c);
            }
        }
        return results;
    }

    public void deleteChuyenDi(String MaChuyenDi) throws SQLException {
        try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("DELETE FROM chuyendi WHERE MaChuyenDi = ?");
            stm.setString(1, MaChuyenDi);
            stm.executeUpdate();
        }
    }

    public void addChuyenDi(ChuyenDi c) throws SQLException {
        String sql = "INSERT INTO chuyendi(MaXe, GiaVe, NgayKhoiHanh,GioKhoiHanh, DiemKhoiHanh, DiemKetThuc,SoGheTrong, SoGheDat, MaChuyenDi)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        addOrUpdateChuyenDi(c, sql);
    }

    public void updateChuyenDi(ChuyenDi c) throws SQLException {
        String sql = "UPDATE chuyendi SET MaXe=?, GiaVe=?,  NgayKhoiHanh=?,GioKhoiHanh=?, DiemKhoiHanh=?, DiemKetThuc=?, SoGheTrong=?, SoGheDat=? WHERE MaChuyenDi = ?";
        addOrUpdateChuyenDi(c, sql);
    }

    public void addOrUpdateChuyenDi(ChuyenDi c, String sql) throws SQLException {
        try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, c.getMaXe());
            stm.setInt(2, c.getGiaVe());
            stm.setDate(3, c.getNgayKhoiHanh());
            stm.setTime(4, c.getGioKhoiHanh());
            stm.setString(5, c.getDiemKhoiHanh());
            stm.setString(6, c.getDiemKetThuc());
            stm.setInt(7, c.getSoGheTrong());
            stm.setInt(8, c.getSoGheDat());
            stm.setString(9, c.getMaChuyenDi());

            stm.executeUpdate();
        }
    }
    
    public ChuyenDi getChuyenDiByMaChuyenDi(String MaChuyenDi) throws SQLException{
        ChuyenDi result = null;
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE MaChuyenDi = ?");
            stm.setString(1, MaChuyenDi);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                
                result  = new ChuyenDi(rs.getString("MaChuyenDi"), rs.getString("MaXe"), rs.getInt("GiaVe"),  rs.getDate("NgayKhoiHanh"), rs.getTime("GioKhoiHanh"),
                        rs.getString("DiemKhoiHanh"), rs.getString("DiemKetThuc"), rs.getInt("SoGheTrong"), rs.getInt("SoGheDat"));
            }
        }
        return result; 
    }
    
    // TT01
    // Lay Doanh Thu Tu Ma Chuyen Di
    public int getGiaVeByMCD(String mCD) throws SQLException {
        int res = 0;
        try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE MaChuyenDi = ?");
            stm.setString(1, mCD);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                res = rs.getInt("GiaVe");
            }
        }
        return res;
    }
}
