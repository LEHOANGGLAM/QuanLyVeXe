/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.conf.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLThongTinInVeController implements Initializable {
    @FXML private Label maVe;
    @FXML private Label bienSo;
    @FXML private Label noiDi;
    @FXML private Label noiDen;
    @FXML private Label ghe;
    @FXML private Label gia;
    @FXML private Label ten;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void inHandler(ActionEvent event) throws IOException {
        
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Utils.getBox("Bán vé thành công", Alert.AlertType.INFORMATION).show();
    }
    
    public void loadForm(String maVe, String bienSo, String noiDi, String noiDen, String ghe, String gia, String ten) {
        this.maVe.setText(maVe);
        this.bienSo.setText(bienSo);
        this.noiDi.setText(noiDi);
        this.noiDen.setText(noiDen);
        this.ghe.setText(ghe);
        this.gia.setText(gia);
        this.ten.setText(ten);
    }
}
