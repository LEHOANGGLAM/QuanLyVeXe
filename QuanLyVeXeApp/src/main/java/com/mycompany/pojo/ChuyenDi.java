/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.sql.Date;

/**
 *
 * @author dell
 */
public class ChuyenDi {
    private int maChuyenDi;
    private int maXe;
    private Date thoiGianKhoiHanh;
    private String diemKhoiHanh;
    private String diemKetThuc;
    private int soGheTrong;
    private int soGheDat;

    public ChuyenDi() {
    }

    public ChuyenDi(int maChuyenDi, int maXe, Date thoiGianKhoiHanh, String diemKhoiHanh, String diemKetThuc, int soGheTrong, int soGheDat) {
        this.maChuyenDi = maChuyenDi;
        this.maXe = maXe;
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
        this.diemKhoiHanh = diemKhoiHanh;
        this.diemKetThuc = diemKetThuc;
        this.soGheTrong = soGheTrong;
        this.soGheDat = soGheDat;
    }
    
    /**
     * @return the maChuyenDi
     */
    public int getMaChuyenDi() {
        return maChuyenDi;
    }

    /**
     * @param maChuyenDi the maChuyenDi to set
     */
    public void setMaChuyenDi(int maChuyenDi) {
        this.maChuyenDi = maChuyenDi;
    }

    /**
     * @return the maXe
     */
    public int getMaXe() {
        return maXe;
    }

    /**
     * @param maXe the maXe to set
     */
    public void setMaXe(int maXe) {
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
}
