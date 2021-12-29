/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.ChuyenDi;
import com.mycompany.pojo.VeXe;
import com.mycompany.services.ChuyenDiService;
import com.mycompany.services.VeXeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class DsVeXeController implements Initializable {
    @FXML private TableView<VeXe> tbVeXe;
    @FXML private ComboBox<ChuyenDi> cbChuyenDi;
    @FXML private TextField txtViTriGhe;
    @FXML private TextField txtTenKhachHang;
    @FXML private TextField txtSDT;
    @FXML private TextField txtTimKiem;
    @FXML private RadioButton byMa;
    @FXML private RadioButton byTen;
    @FXML private Button btnUpdate;
    @FXML private Button btnSell;
    @FXML private Button btnChoose;
    
    private static final VeXeService vxService = new VeXeService();
    private static final ChuyenDiService cdService = new ChuyenDiService();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.btnSell.setDisable(true);
        this.btnUpdate.setDisable(true);
        this.btnChoose.setDisable(true);
        this.loadTableView();
         try {
             this.loadTableData();
         } catch (SQLException ex) {
             Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         try {
            this.cbChuyenDi.setItems(FXCollections.observableList(cdService.getChuyenDi()));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         this.tbVeXe.setRowFactory(l1 -> {
            TableRow row  = new TableRow();
            row.setOnMouseClicked(l2 ->{
                VeXe v = this.tbVeXe.getSelectionModel().getSelectedItem();     
                this.txtTenKhachHang.setText(String.valueOf(v.getTenKhachHang()));
                this.txtSDT.setText(String.valueOf(v.getSdt()));
                this.txtViTriGhe.setText(v.getViTriGhe());
                try {
                    String maCD = vxService.getMaChuyenDiByMaVe(v.getMaVe());
                    this.cbChuyenDi.getSelectionModel().select(cdService.getChuyenDiByMaChuyenDi(maCD));
                } catch (SQLException ex) {
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (v.getTrangThai().equals("Đặt")) {
                    this.btnUpdate.setDisable(false);
                    this.btnSell.setDisable(false);
                } else {
                    this.btnUpdate.setDisable(true);
                    this.btnSell.setDisable(true);
                }
                this.btnChoose.setDisable(false);
            });
            return row;
        });
      
         
        this.txtTimKiem.textProperty().addListener(cl->{
            try {
                if(byMa.isSelected()){
                    this.tbVeXe.setItems(FXCollections.observableList(vxService.getVeXeByMaCD(this.txtTimKiem.getText())));;  
                }
                else if(byTen.isSelected()){
                    this.tbVeXe.setItems(FXCollections.observableList(vxService.getVeXeByMaTen(this.txtTimKiem.getText())));;
                }else {
                this.tbVeXe.setItems(FXCollections.observableList(vxService.getVeXeByMaSDT(this.txtTimKiem.getText())));;
                }
            } catch (SQLException ex) {
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(txtTimKiem.getText() == null || txtTimKiem.getText() == ""){
                try {
                    this.loadTableData();
                } catch (SQLException ex) {
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.txtSDT.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtSDT.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }    
    
     private void loadTableView(){
        TableColumn colId = new TableColumn("Mã Vé");
        colId.setCellValueFactory(new PropertyValueFactory("maVe"));
        colId.setPrefWidth(80);
        
        TableColumn colMaChuyenDi = new TableColumn("Mã Chuyến Đi");
        colMaChuyenDi.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        colMaChuyenDi.setPrefWidth(90);
        
        TableColumn colTen = new TableColumn("Tên Khách Hàng");
        colTen.setCellValueFactory(new PropertyValueFactory("tenKhachHang"));
        colTen.setPrefWidth(120);
        
        TableColumn colSDT = new TableColumn("Số Điện Thoại");
        colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));
        colSDT.setPrefWidth(100);
        
        TableColumn colNgayDat = new TableColumn("Ngày Đặt");
        colNgayDat.setCellValueFactory(new PropertyValueFactory("ngayDat"));
        colNgayDat.setPrefWidth(120);
        
        TableColumn colViTriGhe = new TableColumn("Vị Trí Ghế");
        colViTriGhe.setCellValueFactory(new PropertyValueFactory("viTriGhe"));
        colViTriGhe.setPrefWidth(90);
        
        TableColumn colTrangTrai = new TableColumn("Trạng Thái");
        colTrangTrai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        colTrangTrai.setPrefWidth(90);
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(l ->{
            Button btn = new Button("Hủy vé");
            btn.setOnAction(eh->{
               
                Alert confirm = Utils.getBox("Bạn có chắc chắn hủy vé này không?", Alert.AlertType.CONFIRMATION);
                confirm.showAndWait().ifPresent(action ->{
                    if(action == ButtonType.OK){
                        TableCell cell = (TableCell)((Button)eh.getSource()).getParent();
                        VeXe v = (VeXe)cell.getTableRow().getItem();  
                        if(v.getTrangThai().equals("Bán")){
                              Utils.getBox("Hủy thất bại: Vé đã bán không thế hủy", Alert.AlertType.WARNING).show();
                        }
                        else{
                            try {
                                vxService.deleteVeXe(v);
                                Utils.getBox("Hủy thành công", Alert.AlertType.INFORMATION).show();
                                this.loadTableData();
                                //  this.resetForm();
                            } catch (SQLException ex) {
                                Utils.getBox("Hủy thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                            }
                        }
                    }
                }); 
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbVeXe.getColumns().addAll(colId, colMaChuyenDi, colTen, colSDT, colNgayDat,
                colViTriGhe, colTrangTrai, colAction);
    }
    
    public void loadTableData() throws SQLException{
        this.tbVeXe.setItems(FXCollections.observableList(vxService.getVeXe()));
    }
    
    public void openFXMLGheHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("FXMLGhe.fxml"));
            
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(fxmloader.load());
        dialog.initStyle(StageStyle.TRANSPARENT);     
        dialog.show();
        
        String MaXe = this.cbChuyenDi.getSelectionModel().getSelectedItem().getMaXe();    
        String MaChuyenDi = this.cbChuyenDi.getSelectionModel().getSelectedItem().getMaChuyenDi();
        FXMLGheController controller = fxmloader.getController();
        controller.loadForm(MaXe, MaChuyenDi, this);
    }
     
    public void banVeHandler(ActionEvent event) throws IOException {
        if (checkTextField()) {
            if (checkSDT()) {
                VeXe v = this.tbVeXe.getSelectionModel().getSelectedItem();
                ChuyenDi c = this.cbChuyenDi.getSelectionModel().getSelectedItem();
                if (v != null) {
                    try {
                        v.setTrangThai("Bán");
                        vxService.updateSellVeXe(v);
                        this.loadTableData();                    
                        ///Mở form In vé          
                        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("FXMLThongTinInVe.fxml"));
                        Dialog dialog = new Dialog();
                        dialog.getDialogPane().setContent(fxmloader.load());
                        dialog.initStyle(StageStyle.TRANSPARENT);
                        dialog.show();
                        FXMLThongTinInVeController controller = fxmloader.getController();
                        controller.loadForm(v.getMaVe(),
                                c.getMaXe(), c.getDiemKhoiHanh(), c.getDiemKetThuc(),
                                v.getViTriGhe(), String.valueOf(c.getGiaVe()), v.getTenKhachHang());
                        
                        this.resetForm();
                    } catch (SQLException ex) {
                        Utils.getBox("Bán thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                    }
                }
            } else
                Utils.getBox("Số điện thoại không hợp lệ: số điện thoại phải có 10 chữ số", Alert.AlertType.WARNING).show();
        } else 
            Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.WARNING).show();
    }
    public void updateHandler(ActionEvent event) throws IOException{
        if (checkTextField()) {
            if (checkSDT()) {
                VeXe v = this.tbVeXe.getSelectionModel().getSelectedItem();
                if (v != null) {
                    try {
                        v.setMaChuyenDi(this.cbChuyenDi.getSelectionModel().getSelectedItem().getMaChuyenDi());
                        v.setTenKhachHang(this.txtTenKhachHang.getText());
                        v.setSdt(this.txtSDT.getText());
                        v.setViTriGhe(this.txtViTriGhe.getText());
                        vxService.updateVeXe(v);
                        Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                        this.loadTableData();
                        this.resetForm();
                    } catch (SQLException ex) {
                        Utils.getBox("Sửa thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                    }
                }
            } else
                Utils.getBox("Số điện thoại không hợp lệ: số điện thoại phải có 10 chữ số", Alert.AlertType.WARNING).show();
        } else 
            Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.WARNING).show();
    }

    public void closeHandler(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
    
    public void resetForm(){
        this.tbVeXe.getSelectionModel().select(null);
        this.cbChuyenDi.getSelectionModel().select(null);
        this.txtSDT.setText("");
        this.txtTenKhachHang.setText("");
        this.txtTimKiem.setText("");
        this.txtViTriGhe.setText("");
    }
    
    public void setTxtVitriGhe(String a){
        this.txtViTriGhe.setText(a);
    }
    
    private boolean checkTextField() {
        if (this.txtSDT.getText() == "" || this.txtTenKhachHang.getText() == ""
                || this.txtViTriGhe.getText() == "" || this.cbChuyenDi.getSelectionModel().getSelectedItem() == null) 
            return false;
        return true;
    }
    
    private boolean checkSDT(){
        if(this.txtSDT.getText().length() != 10)
            return false;
        return true;
    }
}
