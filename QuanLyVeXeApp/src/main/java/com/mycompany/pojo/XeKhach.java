/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author dell
 */
public class XeKhach {
    private String maXe;
    private String maNhanVien;
    private String BienSoXe;
    private int soGhe;

    public XeKhach() {
    }

    public XeKhach(String maXe, String maNhanVien, String BienSoXe, int soGhe) {
        this.maXe = maXe;
        this.maNhanVien = maNhanVien;
        this.BienSoXe = BienSoXe;
        this.soGhe = soGhe;
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
     * @return the BienSoXe
     */
    public String getBienSoXe() {
        return BienSoXe;
    }

    /**
     * @param BienSoXe the BienSoXe to set
     */
    public void setBienSoXe(String BienSoXe) {
        this.BienSoXe = BienSoXe;
    }

    /**
     * @return the soGhe
     */
    public int getSoGhe() {
        return soGhe;
    }

    /**
     * @param soGhe the soGhe to set
     */
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }
    
    @Override
    public String toString(){
        return this.maXe;
    }
}
