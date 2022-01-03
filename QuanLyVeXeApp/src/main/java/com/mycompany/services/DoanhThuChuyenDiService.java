/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.DoanhThuChuyenDi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DoanhThuChuyenDiService {
    
    public void insertDTCD(DoanhThuChuyenDi dtcd) throws SQLException{
        String sql = "INSERT INTO doanhthuchuyendi(MaChuyenDi, DoanhThu, SoVeDat, Ngay)" 
                   + "VALUES(?, ?, ?, ?)";
        
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setString(1, dtcd.getMaChuyenDi());
            stm.setInt(2, dtcd.getDoanhThu());
            stm.setInt(3, dtcd.getSoVeDat());
            stm.setDate(4, dtcd.getNgay());
            stm.executeUpdate();
        }
    }
    
    public void updateSoVeAndDoanhThu(DoanhThuChuyenDi dtcd) throws SQLException{
        String sql = "UPDATE doanhthuchuyendi SET DoanhThu = ?, SoVeDat = ? WHERE MaChuyenDi = ? AND Ngay = ?";
        
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall(sql);
            
            stm.setInt(1, dtcd.getDoanhThu());
            stm.setInt(2, dtcd.getSoVeDat());
            stm.setString(3, dtcd.getMaChuyenDi());
            stm.setDate(4, dtcd.getNgay());
            // UPDATE?
            stm.executeUpdate();
        }                  
    }
    
    public boolean isDateExist() throws SQLException{
        String sql = "SELECT Ngay FROM doanhthuchuyendi WHERE Ngay = ?";
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall(sql);
                        
            stm.setDate(1, Date.valueOf(LocalDate.now()));
            
            ResultSet rs = stm.executeQuery();         
            return rs.next();
        }
    }
    
    public boolean isMCDExist(String maChuyenDi) throws SQLException{
        String sql = "SELECT Ngay FROM doanhthuchuyendi WHERE MaChuyenDi = ?";
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall(sql);
                        
            stm.setString(1, maChuyenDi);
            
            ResultSet rs = stm.executeQuery();         
            return rs.next();
        }
    }
    
    public int getDoanhThuByDateAndMCD(String mCD, Date ngay) throws SQLException {
        int res = 0;
        String sql = "SELECT * FROM doanhthuchuyendi WHERE MaChuyenDi = ? AND Ngay = ?";

        try ( Connection conn = jdbcUtils.getConn()) {
            
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, mCD);
            stm.setDate(2, ngay);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                res = rs.getInt("DoanhThu");
            }
        }
        return res;
    }
    
    public int getSoVeDatByDateAndMCD(String mCD, Date ngay) throws SQLException {
        int res = 0;
        String sql = "SELECT * FROM doanhthuchuyendi WHERE MaChuyenDi = ? AND Ngay = ?";
        try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, mCD);
            stm.setDate(2, ngay);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                res = rs.getInt("SoVeDat");
            }
        }
        return res;
    }
    
    //BangThongKe
    public List<DoanhThuChuyenDi> getDoanhThuFromDateToDate(Date d1, Date d2) throws SQLException{
        List<DoanhThuChuyenDi> results = new ArrayList<>();
        String sql = "SELECT * FROM doanhthuchuyendi ORDER BY MaChuyenDi";
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
                
            while (rs.next()) {
                
                DoanhThuChuyenDi dtcd = new DoanhThuChuyenDi(rs.getString("MaChuyenDi"), rs.getInt("DoanhThu"), rs.getInt("SoVeDat"), rs.getDate("Ngay"));               
                if (dtcd.getNgay() != null){
                    if (dtcd.getNgay().compareTo(d1) == 0 || dtcd.getNgay().compareTo(d2) == 0 ||
                        dtcd.getNgay().after(d1) && dtcd.getNgay().before(d2))
                    
                            results.add(dtcd);

                }
          
            }
        }
        return results;
    }
    
    public int getTongDoanhThuFromDateToDate(Date d1, Date d2) throws SQLException{
        int result = 0;
        String sql = "SELECT * FROM doanhthuchuyendi";
        
        try ( Connection conn = jdbcUtils.getConn()) {
            
            PreparedStatement stm = conn.prepareCall(sql);
  
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                if (rs.getDate("Ngay") != null){
                    if (rs.getDate("Ngay").compareTo(d1) == 0 || rs.getDate("Ngay").compareTo(d2) == 0 ||
                        rs.getDate("Ngay").after(d1) && rs.getDate("Ngay").before(d2))         
                            result += rs.getInt("DoanhThu");  
                }
                
            }
        }
        return result;
    }
}
