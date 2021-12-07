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
    public List<ChuyenDi> getChuyenDi() throws SQLException{
        List<ChuyenDi> results = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM chuyendi");
            
            while(rs.next()){
                ChuyenDi c = new ChuyenDi(rs.getString("MaChuyenDi"), rs.getString("MaXe"), rs.getInt("GiaVe"), rs.getDate("ThoiGianKhoiHanh"),
                                        rs.getString("DiemKhoiHanh"), rs.getString("DiemKetThuc"), rs.getInt("SoGheTrong"), rs.getInt("SoGheDat"));
                results.add(c);
            }
        }
        return results;
    }
    
    public void addChuyenDi(ChuyenDi c) throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO chuyendi(MaChuyenDi, MaXe, GiaVe, ThoiGianKhoiHanh, DiemKhoiHanh, DiemKetThuc)"
                    + " VALUES(?, ?, ?, ?, ?, ?)");
            stm.setString(1, c.getMaChuyenDi());
            stm.setString(2, c.getMaXe());
            stm.setInt(3, c.getGiaVe());
            stm.setDate(4, c.getThoiGianKhoiHanh());
            stm.setString(5, c.getDiemKhoiHanh());
            stm.setString(6, c.getDiemKetThuc());
            
            stm.executeUpdate();
            //conn.commit();
        }
    }
}
