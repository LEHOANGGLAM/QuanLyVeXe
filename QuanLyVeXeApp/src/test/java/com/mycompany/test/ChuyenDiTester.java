/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.ChuyenDi;
import com.mycompany.pojo.XeKhach;
import com.mycompany.services.ChuyenDiService;
import com.mycompany.services.XeKhachService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
/**
 *
 * @author dell
 */
public class ChuyenDiTester {
    private static final ChuyenDiService cdService = new ChuyenDiService();
     private static final XeKhachService xkService = new XeKhachService();
     
     LocalDate local = LocalDate.now();
    java.sql.Date date = java.sql.Date.valueOf(local);  
    Time time = new Time(5000);
    ChuyenDi c = new ChuyenDi(RandomStringUtils.randomNumeric(6), "GHE_01",
            100000, date, time, "demo", "demo2",
            24, 0);

     
    @Test
    public void testAddChuyenDi() {
       
        try {
            cdService.addChuyenDi(c);
            ChuyenDi chuyendi = cdService.getChuyenDiByMaChuyenDi(c.getMaChuyenDi());
            Assertions.assertEquals(chuyendi.getMaChuyenDi(), c.getMaChuyenDi());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDeleteChuyenDi() {
       
        try {
            cdService.deleteChuyenDi(c.getMaChuyenDi());
           
            Assertions.assertNull(cdService.getChuyenDiByMaChuyenDi(c.getMaChuyenDi()));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUpdateChuyenDi() {
       
        try {
            cdService.updateChuyenDi(cdService.getChuyenDiByMaChuyenDi("1"));
           ChuyenDi chuyendi = cdService.getChuyenDiByMaChuyenDi("1");
            Assertions.assertEquals(chuyendi.getMaChuyenDi(), cdService.getChuyenDiByMaChuyenDi("1").getMaChuyenDi());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueId() throws SQLException{
        List<ChuyenDi> list = new ArrayList<>();
        try(Connection conn = jdbcUtils.getConn()){
            list.addAll(cdService.getChuyenDi());
            List<String> kq = new ArrayList<>();
            for(ChuyenDi c :list){
                String id = c.getMaChuyenDi();
                kq.add(id);
            }          
            Set<String> kq2 = new HashSet<>(kq);
            Assertions.assertEquals(kq.size(), kq2.size());
        }    
    }
    
    
}
