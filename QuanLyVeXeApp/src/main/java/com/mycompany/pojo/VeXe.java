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
public class VeXe {

    /**
     * @return the maNhanVien
     */
    public String getMaNhanVien() {
        return maNhanVien;
    }

    /**
     * @param maNhanVien the maNhanVien to set
     */
    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    /**
     * @return the trangThai
     */
    public String getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    private String maVe;
    private String tenKhachHang;
    private Date ngayDat; 
    private String sdt;
    private String maChuyenDi;
    private String viTriGhe;
    private String trangThai;
    private String maNhanVien;

    public VeXe() {
    }

    public VeXe(String maVe, String tenKhachHang, Date ngayDat, String sdt, String maChuyenDi, String viTriGhe, String trangThai, String maNhanVien) {
        this.maVe = maVe;
        this.tenKhachHang = tenKhachHang;
        this.ngayDat = ngayDat;
        this.sdt = sdt;
        this.maChuyenDi = maChuyenDi;
        this.viTriGhe = viTriGhe;
        this.trangThai = trangThai;
        this.maNhanVien = maNhanVien;
    }

    

    
    
    /**
     * @return the maVe
     */
    public String getMaVe() {
        return maVe;
    }

    /**
     * @param maVe the maVe to set
     */
    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    /**
     * @return the tenKhachHang
     */
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    /**
     * @param tenKhachHang the tenKhachHang to set
     */
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     */
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    /**
     * @return the sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     * @param sdt the sdt to set
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
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
     * @return the viTriGhe
     */
    public String getViTriGhe() {
        return viTriGhe;
    }

    /**
     * @param viTriGhe the viTriGhe to set
     */
    public void setViTriGhe(String viTriGhe) {
        this.viTriGhe = viTriGhe;
    }
}
