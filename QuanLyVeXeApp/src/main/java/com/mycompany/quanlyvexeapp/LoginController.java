/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.conf.HashUtils;
import com.mycompany.conf.Utils;
import com.mycompany.pojo.Account;
import com.mycompany.services.AccountService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 *
 * @author dell
 */
public class LoginController implements Initializable{
    @FXML private TextField txtTaiKhoan;
    @FXML private PasswordField txtMatKhau;
    @FXML private ChoiceBox cbQTC;

    ObservableList<String> ePermissionList = FXCollections.observableArrayList("Quản trị viên", "Nhân viên");
     private static final AccountService acService = new AccountService();
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbQTC.setItems(ePermissionList);
        this.cbQTC.getSelectionModel().selectFirst();
//        try {
//            acService.updateAccount(HashUtils.hashPassword("123456"), "thuantam");
//        System.out.println(HashUtils.hashPassword("123456"));
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }    
    
    public void dangNhapHandler(ActionEvent event) throws SQLException, IOException, UnsupportedEncodingException, NoSuchAlgorithmException{
        if (this.txtTaiKhoan.getText().isEmpty() || this.txtMatKhau.getText().isEmpty()){
             Utils.getBox("Vui lòng nhập tài khoản, mật khẩu.", Alert.AlertType.WARNING).show();
        } else{
            if (this.cbQTC.getSelectionModel().isEmpty()){
                Utils.getBox("Vui lòng lựa chọn quyền truy cập.", Alert.AlertType.WARNING).show();
            } else{
                try {           
                    Account a = acService.getAccount(txtTaiKhoan.getText());             
                    if (a != null) {                      
//                        System.out.println(HashUtils.hashPassword(txtMatKhau.getText()));
//                        System.out.println(a.getMatKhau());
                        if (a.getMatKhau().equals(HashUtils.hashPassword(txtMatKhau.getText()))
                                && a.getMaQuyen() == this.cbQTC.getSelectionModel().getSelectedIndex() + 1) {
                            FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("Main.fxml"));
                            Scene scene = new Scene(fxmloader.load());
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.setTitle("OuBus");
                            stage.show();
                            MainController controller = fxmloader.getController();
                            controller.loadMain(a.getMaQuyen(),a.getMaNhanVien());

                            Button btn = (Button) event.getSource();
                            Stage stagelogin = (Stage) btn.getScene().getWindow();
                            stagelogin.close();
                        } else 
                            Utils.getBox("Nhập sai mật khẩu hoặc tài khoản", Alert.AlertType.WARNING).show();                     
                    } else
                        Utils.getBox("Tài khoản không tồn tại", Alert.AlertType.WARNING).show();
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
