/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test;


import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.ChuyenDi;
import com.mycompany.pojo.VeXe;
import com.mycompany.services.VeXeService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class VeXeTester {
    private static final VeXeService vxService = new VeXeService();
    
     VeXe v = new VeXe(RandomStringUtils.randomNumeric(6), "test", Date.valueOf(LocalDate.now()),
                            "1234567894", "1", "A1", "Đặt","1");
    @Test
    public void testAddVeXe() {
       
        try {
            vxService.addVeXe(v);
            VeXe vexe = vxService.getVeXeByMaVe(v.getMaVe());
            Assertions.assertEquals(vexe.getMaVe(), v.getMaVe());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUpdateVeXe() {     
        try {
           
            VeXe vexe = vxService.getVeXeByMaVe("229022");
            VeXe v = vxService.getVeXeByMaVe("229022");
            v.setTenKhachHang("test2");
            vxService.updateVeXe(v);
           
            Assertions.assertNotEquals(vexe.getTenKhachHang(), v.getTenKhachHang());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDeleteVeXe() {
        try {
            vxService.deleteVeXe(v);  
            Assertions.assertNull(vxService.getVeXeByMaVe(v.getMaVe()));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueVitriGheDatByChuyenDi() throws SQLException{
        List<VeXe> listVeXe = new ArrayList<>();
        listVeXe.addAll(vxService.getVeXeByMaCD("332498"));
        
        List<String> listVitriGhe = new ArrayList<>();
        for(VeXe v :listVeXe)
            listVitriGhe.add(v.getViTriGhe());
        
        Set<String> kq2 = new HashSet<>(listVitriGhe);
        Assertions.assertEquals(kq2.size(), listVitriGhe.size());
    }
    
     @Test
    public void testUniqueId() throws SQLException{
         List<VeXe> list = new ArrayList<>();
         list.addAll(vxService.getVeXe());
         List<String> kq = new ArrayList<>();
         for (VeXe c : list) {
             String id = c.getMaVe();
             kq.add(id);
         }
         Set<String> kq2 = new HashSet<>(kq);
         Assertions.assertEquals(kq.size(), kq2.size());

    }
    
    @Test
    public void testGetVeXeByInvalidId(){
        VeXe v;
        try {
            v = vxService.getVeXeByMaVe("2");
            Assertions.assertNull(v);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    @Test
    public void testGetVeXeByValidId(){
        VeXe c;
        try {
            c = vxService.getVeXeByMaVe("229022");
            Assertions.assertEquals(c.getMaChuyenDi(),vxService.getVeXeByMaVe("229022").getMaChuyenDi());
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
}
