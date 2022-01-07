/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Account;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
public class AccountService {
    public List<Account> getAccounts() throws SQLException{
        List<Account> results = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM account");
            
            while(rs.next()){
                Account a = new Account(rs.getString("TaiKhoan"), rs.getString("MatKhau"), rs.getString("MaNhanVien"), rs.getInt("MaQuyen"));
                results.add(a);
            }
        }
        return results;
    }
    
    
    public Account getAccount(String tk) throws SQLException{
        Account results = null;
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall("SELECT * FROM account WHERE TaiKhoan=?");
            stm.setString(1, tk);
                       
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                results = new Account(rs.getString("TaiKhoan"), rs.getString("MatKhau") , rs.getString("MaNhanVien"), rs.getInt("MaQuyen"));
            }
        }
        return results;
    }
    
    public Account getAccountByMaNV(String maNV) throws SQLException{
        Account results = null;
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall("SELECT * FROM account WHERE MaNhanVien =? ");
            stm.setString(1, maNV);
                       
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                results = new Account(rs.getString("TaiKhoan"), rs.getString("MatKhau") , rs.getString("MaNhanVien"), rs.getInt("MaQuyen"));
            }
        }
        return results;
    }
    
    public void updateAccount(String s,String tk) throws SQLException {
        String sql = "UPDATE account SET MatKhau = ? WHERE TaiKhoan = ?";
        try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, s);
            stm.setString(2, tk);
           
            stm.executeUpdate();
        }
    }
    
    public void insertAccount(Account a) throws SQLException{
        String sql = "INSERT INTO account(TaiKhoan, MatKhau, MaNhanVien, MaQuyen)" 
                   + "VALUES(?, ?, ?, ?) ON DUPLICATE KEY UPDATE TaiKhoan = ?, MatKhau = ?, MaQuyen = ?";
        
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setString(1, a.getTaiKhoan());
            stm.setString(2, a.getMatKhau());
            stm.setString(3, a.getMaNhanVien());
            stm.setInt(4, a.getMaQuyen());
            stm.setString(5, a.getTaiKhoan());
            stm.setString(6, a.getMatKhau());
            stm.setInt(7, a.getMaQuyen());

            stm.executeUpdate();
        }
    }
    
    public void updateAccount(Account a) throws SQLException{
        String sql = "UPDATE account SET TaiKhoan = ?, MatKhau = ? WHERE MaNhanVien = ?";        
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall(sql);
            
            stm.setString(1, a.getTaiKhoan());
            stm.setString(2, a.getMatKhau());
            stm.setString(3, a.getMaNhanVien());          
            stm.executeUpdate();
        }
    }
    
    public void deleteAccount(Account a) throws SQLException {
        String sql = "DELETE FROM account WHERE TaiKhoan = ?";
        
        try ( Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, a.getTaiKhoan());

            stm.executeUpdate();           
        
            conn.commit();
        }   
        
    }
    
    // Phan loai nhan vien
    public String getLoaiNVByMaLoai(int maLoai) throws SQLException{
        String sql = "SELECT * FROM phanloainhanvien WHERE MaLoaiNhanVien = ?";
        String result = "";
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, maLoai);
           
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()){
                result = rs.getString("TenLoaiNhanVien");
            } else{
                result = "Không có";
            }
        }
        return result;
    }
    
    public String getTKByMaQuyenAndMaNV(int maLoai, String maNV) throws SQLException{
        String sql = "SELECT * FROM account WHERE MaQuyen = ? AND MaNhanVien = ?";
        String result = "";
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, maLoai);
            stm.setString(2, maNV);
           
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()){
                result = rs.getString("TaiKhoan");
            } else{
                result = "Không có";
            }
        }

        return result;
    }
    
    public String getMKByMaQuyenAndMaNV(int maLoai, String maNV) throws SQLException{
         String sql = "SELECT * FROM account WHERE MaQuyen = ? AND MaNhanVien = ?";
        String result = "";
        try (Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setInt(1, maLoai);
            stm.setString(2, maNV);
           
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()){
                result = rs.getString("MatKhau");
            } else{
                result = "Không có";
            }
        }
        return result;
    }
    
    
}
