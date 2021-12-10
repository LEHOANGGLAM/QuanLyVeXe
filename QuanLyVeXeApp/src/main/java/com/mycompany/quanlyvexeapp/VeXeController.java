/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.pojo.ChuyenDi;
import com.mycompany.pojo.XeKhach;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class VeXeController implements Initializable {
    @FXML private Tab tabghe;
    @FXML private Tab tabgiuong;
    @FXML private ComboBox<XeKhach> cbXeKhach;
    @FXML private ComboBox<ChuyenDi> cbDiemKhoiHanh;
    @FXML private ComboBox<ChuyenDi> cbDiemKetThuc;
    @FXML private TextField txtBienSoXe;
    @FXML private TextField txtGiaVe;
    @FXML private TextField txtThoiGianKhoiHanh;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
    public void lapVeHandler(ActionEvent event){
        
    }
}
