/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class MainController implements Initializable {
    @FXML private Button btnDoanhThu;
    @FXML private Button btnNhanVien;
    @FXML private Button btnChuyenDi; 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
           
    }
    
    public void loadMain(int maQuyen) {
        if (maQuyen == 2) {
            this.btnDoanhThu.setDisable(true);
            this.btnChuyenDi.setDisable(true);
            this.btnNhanVien.setDisable(true);
        }
    }

    public void openFormDSVeXeHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("DsVeXe.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(fxmloader.load());
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
    }

    public void openFormChuyenDiHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("ChuyenDi.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(fxmloader.load());
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
    }

    public void openFormBanVeHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("VeXe.fxml"));

        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Bán vé");
        stage.show();
    }

    public void openFormDoanhThuHandler(ActionEvent event) throws IOException {
<<<<<<< HEAD
      
    }

    public void openFormQLNVHandler(ActionEvent event) throws IOException {

=======
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("ThongKeDoanhThu.fxml"));
        
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Thống Kê Doanh Thu");
        stage.show(); 
    }

    public void openFormQLNVHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("NhanVien.fxml"));
        
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quản Lý Nhân Viên");
        stage.show(); 
>>>>>>> 93d0d9ac59c41cc94c277ff9beec6e7b369e90eb
    }

    public void signOutHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm SignOut");
        alert.setHeaderText("Bạn có chắc chắn đăng xuất?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("login.fxml"));

            Scene scene = new Scene(fxmloader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Đăng nhập");
            stage.show();

            Button btn = (Button) event.getSource();
            Stage stageMain = (Stage) btn.getScene().getWindow();
            stageMain.close();
        }
    }
}
