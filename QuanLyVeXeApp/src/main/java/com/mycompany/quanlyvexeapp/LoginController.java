/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Account;
import com.mycompany.services.AccountService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author dell
 */
public class LoginController implements Initializable{
    @FXML private TextField txtTaiKhoan;
    @FXML private TextField txtMatKhau;
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
    public void dangNhapHandler(ActionEvent event){
        AccountService s = new AccountService();
        try {
            List<Account> a = s.getAccounts();
            a.forEach(a1 -> {
                
                if(a1.getTaiKhoan().equals(this.txtTaiKhoan.getText()) && a1.getMatKhau().equals(this.txtMatKhau.getText())){
                    Utils.getBox("Đăng nhập thành công", Alert.AlertType.INFORMATION).show();
                }  
            });
            //Utils.getBox("Đăng nhập thất bại", Alert.AlertType.WARNING).show();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
