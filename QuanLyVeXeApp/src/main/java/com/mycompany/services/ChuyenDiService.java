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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ChuyenDiService {
    public List<ChuyenDi> getChuyenDi() throws SQLException{
        List<ChuyenDi> results = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM chuyendi ORDER BY MaXe");
            
            while(rs.next()){
                ChuyenDi c = new ChuyenDi(rs.getString("MaChuyenDi"), rs.getString("MaXe"), rs.getInt("GiaVe"), rs.getDate("ThoiGianKhoiHanh"),
                                        rs.getString("DiemKhoiHanh"), rs.getString("DiemKetThuc"), rs.getInt("SoGheTrong"), rs.getInt("SoGheDat"));
                results.add(c);
            }
        }
        return results;
    }
    
    public void addChuyenDi(ChuyenDi c) throws SQLException{
        String sql = "INSERT INTO chuyendi(MaXe, GiaVe, ThoiGianKhoiHanh, DiemKhoiHanh, DiemKetThuc,SoGheTrong, SoGheDat, MaChuyenDi)"
                        + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        addOrUpdateChuyenDi(c, sql);
    }
    
    public void updateChuyenDi(ChuyenDi c) throws SQLException{
        String sql = "UPDATE chuyendi SET MaXe=?, GiaVe=?, ThoiGianKhoiHanh=?, DiemKhoiHanh=?, DiemKetThuc=?, SoGheTrong=?, SoGheDat=? WHERE MaChuyenDi = ?";
        addOrUpdateChuyenDi(c, sql);
    }
    
    public void addOrUpdateChuyenDi(ChuyenDi c, String sql) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall(sql);
            
            stm.setString(1, c.getMaXe());
            stm.setInt(2, c.getGiaVe());
            stm.setDate(3, c.getThoiGianKhoiHanh());
            stm.setString(4, c.getDiemKhoiHanh());
            stm.setString(5, c.getDiemKetThuc());
            stm.setInt(6, c.getSoGheTrong());
            stm.setInt(7, c.getSoGheDat());
            stm.setString(8, c.getMaChuyenDi());

            stm.executeUpdate();
        }
    }
}
