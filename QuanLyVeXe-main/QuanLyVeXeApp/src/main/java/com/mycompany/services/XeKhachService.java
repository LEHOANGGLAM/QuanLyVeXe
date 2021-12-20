/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.XeKhach;
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
public class XeKhachService {
    public List<XeKhach> getXeKhach() throws SQLException{
        List<XeKhach> results = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM xekhach");
            while(rs.next()){
                XeKhach x = new XeKhach(rs.getString("MaXe"), rs.getString("MaNhanVien"), rs.getString("BienSoXe"), rs.getInt("SoGhe"));
                results.add(x);
            }
        }
        return results; 
    }
    
     public XeKhach getXeKhachByMaXe(String MaXe) throws SQLException{
        XeKhach results = null;
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall("SELECT * FROM xekhach WHERE MaXe = ?");
            stm.setString(1, MaXe);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                results = new XeKhach(rs.getString("MaXe"), rs.getString("MaNhanVien"), rs.getString("BienSoXe"), rs.getInt("SoGhe"));
            }
        }
        return results; 
    }
}
