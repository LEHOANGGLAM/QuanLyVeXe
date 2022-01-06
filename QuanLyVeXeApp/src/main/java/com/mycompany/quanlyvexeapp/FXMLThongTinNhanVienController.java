/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.Account;
import com.mycompany.pojo.NhanVien;
import com.mycompany.services.AccountService;
import com.mycompany.services.NhanVienService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Admin
 */

public class FXMLThongTinNhanVienController implements Initializable {

    @FXML
    private Button btnConfirm;

    @FXML
    private Button closeBtn;
    
    @FXML
    private TextField txtFiCMND;

    @FXML
    private TextField txtFiLoaiNV;

    @FXML
    private TextField txtFiMK;

    @FXML
    private TextField txtFiMaLoaiNV;

    @FXML
    private TextField txtFiMaNV;

    @FXML
    private DatePicker dpNS;

    @FXML
    private TextField txtFiQQ;

    @FXML
    private TextField txtFiSDT;

    @FXML
    private TextField txtFiTK;

    @FXML
    private TextField txtFiTenNV;
    
    
    private static final NhanVienService nvService = new NhanVienService();
    private static final AccountService aService = new AccountService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numbericTxtFieldOnly(this.txtFiMaNV);
        numbericTxtFieldOnly(this.txtFiMaLoaiNV);
        numbericTxtFieldOnly(this.txtFiCMND);
        numbericTxtFieldOnly(this.txtFiSDT);
        
        this.txtFiMaLoaiNV.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                if (!newValue.isBlank()){
                    try {
                        txtFiLoaiNV.setText(aService.getLoaiNVByMaLoai(Integer.parseInt(newValue)));
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLThongTinNhanVienController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else{
                    txtFiLoaiNV.setText("");
                    if (!txtFiTK.isDisabled() || !txtFiMK.isDisabled()){
                        txtFiTK.setDisable(true);
                        txtFiMK.setDisable(true);
                    }

                }
                                
                if (Integer.parseInt(newValue) == 1 || Integer.parseInt(newValue) == 2){
                    txtFiTK.setDisable(false);
                    txtFiMK.setDisable(false);
                }
            }
        });
        
    }    
    
    // Tai scene 
    public void loadForm(String textBtn){
        this.btnConfirm.setText(textBtn);
        // Load Scene tu` NhanVienForm, tuy` Text cua nut' Confirm 
        if (this.btnConfirm.getText().compareTo("Xác nhận thêm") == 0){
            this.txtFiTK.setDisable(true);
            this.txtFiMK.setDisable(true);
        } else if (this.btnConfirm.getText().compareTo("Xác nhận sửa") == 0){
            if (!this.txtFiLoaiNV.getText().equals("1") || !this.txtFiLoaiNV.getText().equals("2")){
                this.txtFiMaNV.setEditable(false);
                this.txtFiTK.setDisable(true);
                this.txtFiMK.setDisable(true);
            }
        } else if (this.btnConfirm.getText().compareTo("Xác nhận xóa") == 0){
            this.txtFiMaNV.setEditable(false);
            this.txtFiTenNV.setEditable(false);
            this.dpNS.setEditable(false);
            this.txtFiSDT.setEditable(false);
            this.txtFiCMND.setEditable(false);
            this.txtFiQQ.setEditable(false);
            this.txtFiMaLoaiNV.setEditable(false);
            this.txtFiTK.setEditable(false);
            this.txtFiMK.setEditable(false);
        }
    }
    
    public void loadData(NhanVien nv) throws SQLException{
        if (this.btnConfirm.getText().compareTo("Xác nhận sửa") == 0 || (this.btnConfirm.getText().compareTo("Xác nhận xóa") == 0)){
            this.txtFiMaNV.setText(nv.getMaNhanVien());
            this.txtFiTenNV.setText(nv.getTenNhanVien());
            this.dpNS.setValue(nv.getNgaySinh().toLocalDate());
            this.txtFiSDT.setText(nv.getSoDienThoai());
            this.txtFiCMND.setText(nv.getCMND());
            this.txtFiQQ.setText(nv.getQueQuan());
            this.txtFiMaLoaiNV.setText(String.valueOf(nv.getMaLoaiNhanVien()));
            this.txtFiTK.setText(aService.getTKByMaQuyenAndMaNV(nv.getMaLoaiNhanVien(), nv.getMaNhanVien()));
            this.txtFiMK.setText(aService.getMKByMaQuyenAndMaNV(nv.getMaLoaiNhanVien(), nv.getMaNhanVien()));

        }
    }
    
    // Thoat scene
    public void closeBtnHandler(ActionEvent event){
        Stage stage = (Stage) this.closeBtn.getScene().getWindow();
        stage.close();
    }
    
    public void confirmHandler(ActionEvent event) throws SQLException{
        
        //INSERT
        if (this.btnConfirm.getText().compareTo("Xác nhận thêm") == 0){
            if (isFillFull()){
                //Kiem tra ma nhan vien ton tai chua
                if (!nvService.isMaNhanVienExist(this.txtFiMaNV.getText())){
                    NhanVien nv = new NhanVien(this.txtFiMaNV.getText(), this.txtFiTenNV.getText(), Integer.parseInt(this.txtFiMaLoaiNV.getText()), 
                                           Date.valueOf(this.dpNS.getValue()), this.txtFiSDT.getText(), this.txtFiCMND.getText(), this.txtFiQQ.getText());
                    if (nv.getMaLoaiNhanVien() != 1 || nv.getMaLoaiNhanVien() != 2){ // 1 va 2 la co quyen cap tai khoan
                        try{
                            nvService.insertNhanVien(nv);
                            Utils.getBox("Thêm nhân viên thành công!", Alert.AlertType.INFORMATION).show();

                            Button btn = (Button) event.getSource();
                            Stage stage = (Stage) btn.getScene().getWindow();
                            stage.close();

                        } catch (SQLException ex){
                            Utils.getBox("Thêm nhân viên thất bại!", Alert.AlertType.WARNING).show();

                        } 
                    } else{
                        if (!this.txtFiTK.getText().isBlank() && !this.txtFiMK.getText().isBlank()){
                            try{
                                Account a = new Account(this.txtFiTK.getText(), this.txtFiMK.getText(), nv.getMaNhanVien(), nv.getMaLoaiNhanVien());
                                try{
                                    aService.insertAccount(a);
                                    Utils.getBox("Tạo tài khoản thành công!", Alert.AlertType.INFORMATION).show();

                                }catch (SQLException ex){
                                    Utils.getBox("Tạo tài khoản thất bại!", Alert.AlertType.WARNING).show();

                                }
                                nvService.insertNhanVien(nv);
                                Utils.getBox("Thêm nhân viên thành công!", Alert.AlertType.INFORMATION).show();

                                Button btn = (Button) event.getSource();
                                Stage stage = (Stage) btn.getScene().getWindow();
                                stage.close();

                            } catch (SQLException ex){
                                Utils.getBox("Thêm nhân viên thất bại!", Alert.AlertType.WARNING).show();

                            } 
                        } else{ //Thieu tk, mk cua ma loai nv 1, 2
                            Utils.getBox("Vui lòng nhập tài khoản, mật khẩu.", Alert.AlertType.WARNING).show();
                        }
                    }
                } else{
                    Utils.getBox("Mã nhân viên này đã tồn tại.", Alert.AlertType.WARNING).show();

                }
                
                
            } else{ //Chua dien thong tin
                Utils.getBox("Vui lòng điền đầy đủ thông tin", Alert.AlertType.WARNING).show();
            } 
        } else if (this.btnConfirm.getText().compareTo("Xác nhận sửa") == 0){ //UPDATE
            if (isFillFull()){
                NhanVien nv = new NhanVien(this.txtFiMaNV.getText(), this.txtFiTenNV.getText(), Integer.parseInt(this.txtFiMaLoaiNV.getText()), 
                                           Date.valueOf(this.dpNS.getValue()), this.txtFiSDT.getText(), this.txtFiCMND.getText(), this.txtFiQQ.getText());
                if (nv.getMaLoaiNhanVien() != 1 || nv.getMaLoaiNhanVien() != 2){
                        try{
                            nvService.updateNhanVien(nv);
                            Utils.getBox("Cập nhật thành công!", Alert.AlertType.INFORMATION).show();
                            Button btn = (Button) event.getSource();
                            Stage stage = (Stage) btn.getScene().getWindow();
                            stage.close();

                        } catch (SQLException ex){
                            Utils.getBox(ex.getMessage() , Alert.AlertType.WARNING).show();

                        } 
                } else{
                    if (!this.txtFiTK.getText().isBlank() && !this.txtFiMK.getText().isBlank()){
                        try{
                            Account a = new Account(this.txtFiTK.getText(), this.txtFiMK.getText(), this.txtFiMaNV.getText(), Integer.parseInt(this.txtFiMaLoaiNV.getText()));
                                   
                            nvService.updateNhanVien(nv);
                            aService.updateAccount(a);
                            Utils.getBox("Cập nhật thành công!", Alert.AlertType.INFORMATION).show();                        
                            Button btn = (Button) event.getSource();
                            Stage stage = (Stage) btn.getScene().getWindow();
                            stage.close();

                        } catch (SQLException ex){
                            Utils.getBox("Cập nhật thất bại!", Alert.AlertType.WARNING).show();

                        } 
                    } else{ //Thieu tk, mk cua ma loai nv 1, 2
                        Utils.getBox("Vui lòng nhập tài khoản, mật khẩu.", Alert.AlertType.WARNING).show();
                    }

                }
            }
        } else if (this.btnConfirm.getText().compareTo("Xác nhận xóa") == 0){ //DELETE
           if (isFillFull()){
                NhanVien nv = new NhanVien(this.txtFiMaNV.getText(), this.txtFiTenNV.getText(), Integer.parseInt(this.txtFiMaLoaiNV.getText()), 
                                           Date.valueOf(this.dpNS.getValue()), this.txtFiSDT.getText(), this.txtFiCMND.getText(), this.txtFiQQ.getText());
                if (nv.getMaLoaiNhanVien() != 1 || nv.getMaLoaiNhanVien() != 2){
                        try{
                            nvService.deleteNhanVien(nv);
                            Utils.getBox("Xóa thành công!", Alert.AlertType.INFORMATION).show();
                            Button btn = (Button) event.getSource();
                            Stage stage = (Stage) btn.getScene().getWindow();
                            stage.close();

                        } catch (SQLException ex){
                            Utils.getBox(ex.getMessage() , Alert.AlertType.WARNING).show();

                        } 
                } else{
                    if (!this.txtFiTK.getText().isBlank() && !this.txtFiMK.getText().isBlank()){
                        try{
                            Account a = new Account(this.txtFiTK.getText(), this.txtFiMK.getText(), this.txtFiMaNV.getText(), Integer.parseInt(this.txtFiMaLoaiNV.getText()));
                                   
                            nvService.deleteNhanVien(nv);
                            aService.deleteAccount(a);
                            Utils.getBox("Xóa thành công!", Alert.AlertType.INFORMATION).show();                        
                            Button btn = (Button) event.getSource();
                            Stage stage = (Stage) btn.getScene().getWindow();
                            stage.close();

                        } catch (SQLException ex){
                            Utils.getBox(ex.getMessage(), Alert.AlertType.WARNING).show();
                        } 
                    } 
                }
            } 
        }
    }
    
    
    public boolean isFillFull(){
         this.dpNS.setValue( this.dpNS.getConverter().fromString( this.dpNS.getEditor().getText()));
        
        if (!this.txtFiTenNV.getText().isBlank() && !this.txtFiMaNV.getText().isBlank() 
            && this.dpNS.getValue() != null && !this.txtFiSDT.getText().isBlank()
            && !this.txtFiCMND.getText().isBlank() && !this.txtFiQQ.getText().isBlank()
            && !this.txtFiMaLoaiNV.getText().isBlank()){
                return true;
        }
        return false;
    }
    
    public void numbericTxtFieldOnly(TextField txtFi){
        DecimalFormat format = new DecimalFormat( "#.0" );
    
        txtFi.setTextFormatter( new TextFormatter<>(c ->
        {
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        }));
    }
    
}
