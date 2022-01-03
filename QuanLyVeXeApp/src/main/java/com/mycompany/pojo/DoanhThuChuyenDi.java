/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class DoanhThuChuyenDi {

    /**
     * @return the maChuyenDi
     */
   
    private String maChuyenDi;
    private int doanhThu;
    private int soVeDat;
    private Date ngay;
    
    public DoanhThuChuyenDi(){
       
    }
    
    public DoanhThuChuyenDi(String maCD, int dT, int sVD, Date n){
        this.maChuyenDi = maCD;
        this.doanhThu = dT;
        this.soVeDat = sVD;
        this.ngay = n;
    }
    
     public String getMaChuyenDi() {
        return maChuyenDi;
    }

    /**
     * @param maChuyenDi the maChuyenDi to set
     */
    public void setMaChuyenDi(String maChuyenDi) {
        this.maChuyenDi = maChuyenDi;
    }

    /**
     * @return the doanhThu
     */
    public int getDoanhThu() {
        return doanhThu;
    }

    /**
     * @param doanhThu the doanhThu to set
     */
    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }

    /**
     * @return the soVeDat
     */
    public int getSoVeDat() {
        return soVeDat;
    }

    /**
     * @param soVeDat the soVeDat to set
     */
    public void setSoVeDat(int soVeDat) {
        this.soVeDat = soVeDat;
    }

    /**
     * @return the ngay
     */
    public Date getNgay() {
        return ngay;
    }

    /**
     * @param ngay the ngay to set
     */
}
