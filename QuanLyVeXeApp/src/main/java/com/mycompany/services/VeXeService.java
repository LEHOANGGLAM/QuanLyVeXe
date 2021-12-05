/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.VeXe;
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
public class VeXeService {
    public List<VeXe> getVeXe() throws SQLException{
       List<VeXe> results = new ArrayList<>();
       try(Connection conn = jdbcUtils.getConn()){
           Statement stm = conn.createStatement();
           ResultSet rs = stm.executeQuery("SELECT * FROM vexe ORDER BY MaChuyenDi");
           
           while(rs.next()){
               VeXe v = new VeXe(rs.getInt("MaVe"), rs.getString("TenKhachHang"), rs.getDate("NgayDat"), rs.getInt("SoDienThoai"),
                                rs.getInt("MaChuyenDi"), rs.getString("ViTriGhe"));
               results.add(v);
           }
       }
       return results;
    }
}
