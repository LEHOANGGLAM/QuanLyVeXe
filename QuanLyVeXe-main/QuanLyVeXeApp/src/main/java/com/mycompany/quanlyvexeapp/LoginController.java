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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 *
 * @author dell
 */
public class LoginController implements Initializable{
    @FXML private TextField txtTaiKhoan;
    @FXML private TextField txtMatKhau;
    @FXML private ChoiceBox cbQTC;

    ObservableList<String> ePermissionList = FXCollections.observableArrayList("Quản trị viên", "Nhân viên");
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       cbQTC.setItems(ePermissionList);
    }    
    
    public void dangNhapHandler(ActionEvent event) throws SQLException{
        if (this.txtTaiKhoan.getText().isEmpty() || this.txtMatKhau.getText().isEmpty()){
             Utils.getBox("Vui lòng nhập tài khoản, mật khẩu.", Alert.AlertType.WARNING).show();
        } else{
            if (this.cbQTC.getSelectionModel().isEmpty()){
                Utils.getBox("Vui lòng lựa chọn quyền truy cập.", Alert.AlertType.WARNING).show();
            } else{
                AccountService s = new AccountService();
                try {           
                    boolean flag = false;
                    List<Account> a = s.getAccounts();
                    for(Account a1 : a){
                        if (!flag){
                           if (a1.getTaiKhoan().equals(this.txtTaiKhoan.getText())){                           
                                if (a1.getMatKhau().equals(this.txtMatKhau.getText())){                           
                                    if (a1.getMaQuyen() == this.cbQTC.getSelectionModel().getSelectedIndex() + 1){
                                        Utils.getBox("Đăng nhập thành công!", Alert.AlertType.INFORMATION).show();   
                                        flag = true;
                                    } else {
                                        Utils.getBox("Tài khoản này sai quyền truy cập!", Alert.AlertType.WARNING).show();
                                    }

                                } else{
                                    if (!a.stream().anyMatch(a2 -> this.txtMatKhau.getText().equals(a2.getTaiKhoan()))){
                                        Utils.getBox("Nhập sai mật khẩu!", Alert.AlertType.WARNING).show();
                                    }
                                        
                                }
                            } else {
                                if (!a.stream().anyMatch(a2 -> this.txtTaiKhoan.getText().equals(a2.getTaiKhoan()))){
                                    Utils.getBox("Tài khoản này không tồn tại!", Alert.AlertType.WARNING).show();
                                }
                               
                            } 
                        } else{
                            break;
                        }                                                                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }     
        }
    }
    
    public void thoatApplication(ActionEvent event){
        Platform.exit();
    }
           
}
