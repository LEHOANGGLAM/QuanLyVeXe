/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test;


import com.mycompany.pojo.VeXe;
import com.mycompany.services.VeXeService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    
    @Test
    public void testUniqueVitriGheTrongChuyenDi() throws SQLException{
        List<VeXe> listVeXe = new ArrayList<>();
        listVeXe.addAll(vxService.getVeXeByMaCD("1"));
        
        List<String> listVitriGhe = new ArrayList<>(); //A1,A2 //A1,A2
        for(VeXe v :listVeXe)
            listVitriGhe.add(v.getViTriGhe());
        
        Set<String> kq2 = new HashSet<>(listVitriGhe);
        Assertions.assertEquals(kq2.size(), listVitriGhe.size());
    }
}
