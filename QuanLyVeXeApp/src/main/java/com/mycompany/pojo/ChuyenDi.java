/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author dell
 */
public class ChuyenDi {

    /**
     * @return the isDelete
     */
    public int getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return the gioKhoiHanh
     */
    public Time getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    /**
     * @param gioKhoiHanh the gioKhoiHanh to set
     */
    public void setGioKhoiHanh(Time gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }
    private String maChuyenDi;
    private String maXe;
    private int giaVe;
    private Date ngayKhoiHanh;
    private Time gioKhoiHanh;
    private String diemKhoiHanh;
    private String diemKetThuc;
    private int soGheTrong;
    private int soGheDat;
    private int isDelete;

    public ChuyenDi() {
    }

    public ChuyenDi(String maChuyenDi, String maXe, int giaVe, Date ngayKhoiHanh, Time gioKhoiHanh, String diemKhoiHanh, String diemKetThuc, int soGheTrong, int soGheDat, int isDelete) {
        this.maChuyenDi = maChuyenDi;
        this.maXe = maXe;
        this.giaVe = giaVe;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.gioKhoiHanh = gioKhoiHanh;
        this.diemKhoiHanh = diemKhoiHanh;
        this.diemKetThuc = diemKetThuc;
        this.soGheTrong = soGheTrong;
        this.soGheDat = soGheDat;
        this.isDelete = isDelete;
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
    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    /**
     * @param ngayKhoiHanh the ngayKhoiHanh to set
     */
    public void setNgayKhoiHanh(Date ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
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
