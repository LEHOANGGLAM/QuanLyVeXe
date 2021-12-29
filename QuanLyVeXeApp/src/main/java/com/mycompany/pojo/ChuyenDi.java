/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author dell
 */
public class ChuyenDi {
    private String maChuyenDi;
    private String maXe;
    private int giaVe;
    private Date thoiGianKhoiHanh;
    private String diemKhoiHanh;
    private String diemKetThuc;
    private int soGheTrong;
    private int soGheDat;

    public ChuyenDi() {
    }

    public ChuyenDi(String maChuyenDi, String maXe, int giaVe, Date thoiGianKhoiHanh, String diemKhoiHanh, String diemKetThuc, int soGheTrong, int soGheDat) {
        this.maChuyenDi = maChuyenDi;
        this.maXe = maXe;
        this.giaVe = giaVe;
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
        this.diemKhoiHanh = diemKhoiHanh;
        this.diemKetThuc = diemKetThuc;
        this.soGheTrong = soGheTrong;
        this.soGheDat = soGheDat;
    }

    public ChuyenDi(String randomNumeric, String maXe, int parseInt, LocalDate date, String text, String text0, int soGhe, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the giaVe
     */
    public int getGiaVe() {
        return giaVe;
    }

    /**
     * @param giaVe the giaVe to set
     */
    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }
    
    /**
     * @return the maChuyenDi
     */
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
     * @return the maXe
     */
    public String getMaXe() {
        return maXe;
    }

    /**
     * @param maXe the maXe to set
     */
    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    /**
     * @return the thoiGianKhoiHanh
     */
    public Date getThoiGianKhoiHanh() {
        return thoiGianKhoiHanh;
    }

    /**
     * @param thoiGianKhoiHanh the thoiGianKhoiHanh to set
     */
    public void setThoiGianKhoiHanh(Date thoiGianKhoiHanh) {
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
    }

    /**
     * @return the diemKhoiHanh
     */
    public String getDiemKhoiHanh() {
        return diemKhoiHanh;
    }

    /**
     * @param diemKhoiHanh the diemKhoiHanh to set
     */
    public void setDiemKhoiHanh(String diemKhoiHanh) {
        this.diemKhoiHanh = diemKhoiHanh;
    }

    /**
     * @return the diemKetThuc
     */
    public String getDiemKetThuc() {
        return diemKetThuc;
    }

    /**
     * @param diemKetThuc the diemKetThuc to set
     */
    public void setDiemKetThuc(String diemKetThuc) {
        this.diemKetThuc = diemKetThuc;
    }

    /**
     * @return the soGheTrong
     */
    public int getSoGheTrong() {
        return soGheTrong;
    }

    /**
     * @param soGheTrong the soGheTrong to set
     */
    public void setSoGheTrong(int soGheTrong) {
        this.soGheTrong = soGheTrong;
    }

    /**
     * @return the soGheDat
     */
    public int getSoGheDat() {
        return soGheDat;
    }

    /**
     * @param soGheDat the soGheDat to set
     */
    public void setSoGheDat(int soGheDat) {
        this.soGheDat = soGheDat;
    }
    
    @Override
    public String toString(){
        return this.maChuyenDi;
    }
}
