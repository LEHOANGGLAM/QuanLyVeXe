package com.mycompany.test;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.XeKhach;
import com.mycompany.services.XeKhachService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dell
 */
public class XeKhachTester {
    private static final XeKhachService xkService = new XeKhachService();

    @Test
    public void testGetXeKhachByInValidId(){
        XeKhach xk;
        try {
            xk = xkService.getXeKhachByMaXe("1");
             Assertions.assertNull(xk);
        } catch (SQLException ex) {
            Logger.getLogger(XeKhachTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetXeKhachById(){
        XeKhach xk;
        try {
            xk = xkService.getXeKhachByMaXe("GHE_01");
             Assertions.assertEquals("XE1", xk.getBienSoXe());
        } catch (SQLException ex) {
            Logger.getLogger(XeKhachTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueId() throws SQLException{
        try(Connection conn = jdbcUtils.getConn()){
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM xekhach");
            
            List<String> kq = new ArrayList<>();
            while(rs.next()){
                String id = rs.getString("MaXe");
                kq.add(id);
            }
            
            Set<String> kq2 = new HashSet<>(kq);
            Assertions.assertEquals(kq.size(), kq2.size());
        }    
    }
}
