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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.lang3.RandomStringUtils;

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
    @FXML private RadioButton bySDT;
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
                if(v.getTrangThai() == 1)
                    this.btnSell.setDisable(false);
                else 
                    this.btnSell.setDisable(true);
                this.btnUpdate.setDisable(false);
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
                        try {
                            vxService.deleteVeXe(v.getMaVe());
                            Utils.getBox("Hủy thành công", Alert.AlertType.INFORMATION).show();
                            this.loadTableData();
                          //  this.resetForm();
                        } catch (SQLException ex) {
                            Utils.getBox("Hủy thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
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
        
//        String MaXe = this.cbChuyenDi.getSelectionModel().getSelectedItem().getMaXe();    
        FXMLGheController controller = fxmloader.getController();
        controller.setSoCho(20);
        //Utils.getBox(controller.MaXe, Alert.AlertType.INFORMATION).show();
        
        dialog.show();
    }
     
    public void banVeHandler(ActionEvent event) throws IOException{
         VeXe v = this.tbVeXe.getSelectionModel().getSelectedItem();     
        if(v != null){
            try {
                v.setTrangThai(0);
                vxService.updateSellVeXe(v);
                Utils.getBox("Bán thành công", Alert.AlertType.INFORMATION).show();
                this.loadTableData();
                this.resetForm();
            } catch (SQLException ex) {
                Utils.getBox("Bán thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
            }
        }
    }
    public void updateHandler(ActionEvent event) throws IOException{
        VeXe v = this.tbVeXe.getSelectionModel().getSelectedItem();  
        if(v != null){
            try {
                v.setMaChuyenDi(this.cbChuyenDi.getSelectionModel().getSelectedItem().getMaChuyenDi());
                v.setTenKhachHang(this.txtTenKhachHang.getText());
                v.setSdt(Integer.parseInt(this.txtSDT.getText()));
                v.setViTriGhe(this.txtViTriGhe.getText());
                vxService.updateVeXe(v);
                Utils.getBox("Sửa thành công", Alert.AlertType.INFORMATION).show();
                this.loadTableData();
                this.resetForm();
            } catch (SQLException ex) {
                Utils.getBox("Sửa thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
            }
        }
    }
      public void closeHandler(ActionEvent event) throws IOException{
          Button btn = (Button)event.getSource();
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
}
