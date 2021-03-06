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

    @FXML
    private Button btnDoanhThu;
    @FXML
    private Button btnNhanVien;
    @FXML
    private Button btnChuyenDi;
    
    public String MaNV = "1";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO

    }

    public void loadMain(int maQuyen,String MaNV) {
        if (maQuyen == 2) {
            this.btnDoanhThu.setDisable(true);
            this.btnChuyenDi.setDisable(true);
            this.btnNhanVien.setDisable(true);
        }
        this.MaNV = MaNV;
    }

    public void openFormDSVeXeHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("DsVeXe.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(fxmloader.load());
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
         DsVeXeController controller = fxmloader.getController();
        controller.loadForm(this.MaNV);
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
        stage.setTitle("B??n v??");
        stage.show();
        VeXeController controller = fxmloader.getController();
        controller.loadForm(this.MaNV);
    }

    public void openFormDoanhThuHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("ThongKeDoanhThu.fxml"));

        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Th???ng K?? Doanh Thu");
        stage.show();

    }

    public void openFormQLNVHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("NhanVien.fxml"));

        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Qu???n L?? Nh??n Vi??n");
        stage.show();
        stage.show(); 

    }

    public void signOutHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm SignOut");
        alert.setHeaderText("B???n c?? ch???c ch???n ????ng xu???t?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("login.fxml"));

            Scene scene = new Scene(fxmloader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("????ng nh???p");
            stage.show();

            Button btn = (Button) event.getSource();
            Stage stageMain = (Stage) btn.getScene().getWindow();
            stageMain.close();
        }
    }
}
