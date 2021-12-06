/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.ChuyenDi;
import java.sql.Connection;
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
                ChuyenDi c = new ChuyenDi(rs.getInt("MaChuyenDi"), rs.getInt("MaXe"), rs.getInt("GiaVe"), rs.getDate("ThoiGianKhoiHanh"),
                                        rs.getString("DiemKhoiHanh"), rs.getString("DiemKetThuc"), rs.getInt("SoGheTrong"), rs.getInt("SoGheDat"));
                results.add(c);
            }
        }
        return results;
    }
}
