/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.conf.Utils;

import java.io.IOException;
import java.net.URL;
import com.mycompany.pojo.DoanhThuChuyenDi;
import com.mycompany.services.ChuyenDiService;
import com.mycompany.services.DoanhThuChuyenDiService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
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

    @FXML
    private Label maVe;
    @FXML
    private Label bienSo;
    @FXML
    private Label noiDi;
    @FXML
    private Label noiDen;
    @FXML
    private Label ghe;
    @FXML
    private Label gia;
    @FXML
    private Label ten;
    @FXML
    private Label khoiHanh;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private static final DoanhThuChuyenDiService dtcdService = new DoanhThuChuyenDiService();
    private static final ChuyenDiService cdService = new ChuyenDiService();
    private String maChuyenDii;

    public void inHandler(ActionEvent event) throws IOException, SQLException {

        // Update Or Them Vao Thong Ke Doanh Thu
        // Kiem tra MaChuyenDi co ton tai trong bang dtcd ko
        // Kiem tra MCD roi thi kiemtra xem co ton tai thoi gian ban ve trog hom nay ko
        if (dtcdService.isMCDExist(maChuyenDii) && dtcdService.isDateExist()) {
            // Co ton tai thi` update
            int doanhThu = dtcdService.getDoanhThuByDateAndMCD(maChuyenDii, Date.valueOf(LocalDate.now()));
            int soVeDat = dtcdService.getSoVeDatByDateAndMCD(maChuyenDii, Date.valueOf(LocalDate.now()));
            doanhThu = doanhThu + (doanhThu / soVeDat);
            soVeDat++;
            DoanhThuChuyenDi dtcd = new DoanhThuChuyenDi(maChuyenDii, doanhThu, soVeDat, Date.valueOf(LocalDate.now()));
            dtcdService.updateSoVeAndDoanhThu(dtcd);
        } else {
            DoanhThuChuyenDi dtcd = new DoanhThuChuyenDi(maChuyenDii, cdService.getGiaVeByMCD(maChuyenDii), 1, Date.valueOf(LocalDate.now()));
            dtcdService.insertDTCD(dtcd);
        }

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Utils.getBox("Bán vé thành công", Alert.AlertType.INFORMATION).show();
    }

    public void loadForm(String maVe, String bienSo, String noiDi, String noiDen, String ghe, String gia, String ten, String khoiHanh, String mCD) {

        this.maVe.setText(maVe);
        this.bienSo.setText(bienSo);
        this.noiDi.setText(noiDi);
        this.noiDen.setText(noiDen);
        this.ghe.setText(ghe);
        this.gia.setText(gia);
        this.ten.setText(ten);
        this.khoiHanh.setText(khoiHanh);
        maChuyenDii = mCD;
    }
}
