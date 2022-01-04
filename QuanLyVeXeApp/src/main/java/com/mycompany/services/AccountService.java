/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.Account;
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
public class AccountService {
    public List<Account> getAccounts() throws SQLException{
        List<Account> results = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM account");
            
            while(rs.next()){
                Account a = new Account(rs.getString("TaiKhoan"), rs.getString("MatKhau"), rs.getInt("MaNhanVien"), rs.getInt("MaQuyen"));
                results.add(a);
            }
        }
        return results;
    }
    
}
