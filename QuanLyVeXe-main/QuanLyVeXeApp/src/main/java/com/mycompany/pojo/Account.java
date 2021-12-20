/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author dell
 */
public class Account {
    private String taiKhoan;
    private String matKhau;
    private int maNhanVien;
    private int maQuyen;

    public Account(){
    }

    public Account(String taiKhoan, String matKhau, int maNhanVien, int maQuyen) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.maNhanVien = maNhanVien;
        this.maQuyen = maQuyen;
    }
    
    /**
     * @return the taiKhoan
     */
    public String getTaiKhoan() {
        return taiKhoan;
    }

    /**
     * @param taiKhoan the taiKhoan to set
     */
    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    /**
     * @return the matKhau
     */
    public String getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    /**
     * @return the maNhanVien
     */
    public int getMaNhanVien() {
        return maNhanVien;
    }

    /**
     * @param maNhanVien the maNhanVien to set
     */
    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    /**
     * @return the maQuyen
     */
    public int getMaQuyen() {
        return maQuyen;
    }

    /**
     * @param maQuyen the maQuyen to set
     */
    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }
}
