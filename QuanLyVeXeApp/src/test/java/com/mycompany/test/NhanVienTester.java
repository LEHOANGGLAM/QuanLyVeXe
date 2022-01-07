/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test;

import com.mycompany.conf.jdbcUtils;
import com.mycompany.pojo.NhanVien;
import com.mycompany.services.NhanVienService;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
/**
 *
 * @author Admin
 */
public class NhanVienTester {
    private static final NhanVienService nvService = new NhanVienService();
    
    //Gia tri test
    Date sqlDate = Date.valueOf(LocalDate.now());
    NhanVien testNV = new NhanVien("5", "Thuan Tam", 3, sqlDate, "1234567890", "0339670438", "Kien Giang");
    
    @Test
    public void testInsertNhanVien(){
        try{
            nvService.insertNhanVien(testNV);
            List<NhanVien> tempListNV = nvService.getNhanVienByMaNV(testNV.getMaNhanVien());
            NhanVien tempNV = tempListNV.get(0);
            Assertions.assertEquals(testNV.getMaNhanVien(), tempNV.getMaNhanVien());
        } catch(SQLException ex){
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
 
        }
    }
    
    @Test
    public void testUpdateNhanVien(){
        try{
            //Lay data co MaNV = 6 ra update
            List<NhanVien> tempListNV = nvService.getNhanVienByMaNV("5");
            NhanVien tempNV = tempListNV.get(0);  
            tempNV.setTenNhanVien("Tam Cui bap");
            
            nvService.updateNhanVien(tempNV);
            Assertions.assertNotEquals(testNV.getTenNhanVien(), tempNV.getTenNhanVien());            

        } catch (SQLException ex){
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test 
    public void testDeleteNhanVien(){
        try{
            List<NhanVien> tempListNV = nvService.getNhanVienByMaNV("5");
            NhanVien tempNV = tempListNV.get(0);  
            
            nvService.deleteNhanVien(tempNV);         
            
            NhanVien checkNV;
            List<NhanVien> checkList = nvService.getNhanVienByMaNV("5");
            if (checkList.isEmpty()){
                checkNV = null;
            } else{
                checkNV = checkList.get(0);
            }
            
            Assertions.assertNull(checkNV);
        } catch (SQLException ex){
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);
       
        }
    }
    
    @Test 
    public void testGetNhanVien(){
        try{
            List<NhanVien> testList = nvService.getNhanVien();
            
            Assertions.assertNotNull(testList);
        } catch (SQLException ex){
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }
    
    @Test
    public void testGetNhanVienByInvalidTen(){
        NhanVien tempNV;
        try{
            List<NhanVien> testList = nvService.getNhanVienByTen("Khong co ten");
            if (testList.isEmpty()){
                tempNV = null;
            } else{
                tempNV = testList.get(0);
            }
            
            Assertions.assertNull(tempNV);
            
        } catch (SQLException ex){
             Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);                       
        }
    }
    
    @Test
    public void testGetNhanVienByValidTen(){
        NhanVien tempNV;
        try{
            List<NhanVien> testList = nvService.getNhanVienByTen("Thuận Tâm");
            if (testList.isEmpty()){
                tempNV = null;
            } else{
                tempNV = testList.get(0);
            }
            
            Assertions.assertNotNull(tempNV);
            
        } catch (SQLException ex){
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);                       
        }
    }
    
    @Test
    public void testCheckNotExistMaNhanVien(){
        try{
            boolean isExist = nvService.isMaNhanVienExist("7");
            Assertions.assertFalse(isExist);
        } catch (SQLException ex){
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);                       
            
        }
    }
    
    @Test
    public void testCheckExistMaNhanVien(){
        try{
            boolean isExist = nvService.isMaNhanVienExist("1");
            Assertions.assertTrue(isExist);
        } catch (SQLException ex){
            Logger.getLogger(NhanVienTester.class.getName()).log(Level.SEVERE, null, ex);                       
            
        }
    }
}
