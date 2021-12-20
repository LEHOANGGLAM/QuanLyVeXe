/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.ChuyenDi;
import com.mycompany.pojo.VeXe;
import com.mycompany.pojo.XeKhach;
import com.mycompany.services.ChuyenDiService;
import com.mycompany.services.VeXeService;
import com.mycompany.services.XeKhachService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class VeXeController implements Initializable {
    @FXML private TextField txtMaChuyenDi;
    @FXML private TextField txtMaXe;
    @FXML private TextField txtDiemKhoiHanh;
    @FXML private TextField txtDiemKetThuc;
    @FXML private TextField txtBienSoXe;
    @FXML private TextField txtGiaVe;
    @FXML private TextField txtThoiGianKhoiHanh;
    @FXML private TextField txtHoTenKhachHang;
    @FXML private TextField txtSdt;
    @FXML private TextField txtVitriGhe;
    @FXML private TextField txtTimKiem;
    @FXML private TableView tbChuyenDi;
  
    @FXML private RadioButton A1;
    @FXML private RadioButton A2;
    @FXML private RadioButton A3;
    @FXML private RadioButton A4;
    @FXML private RadioButton A5;
    @FXML private RadioButton A6;
    @FXML private RadioButton A7;
    @FXML private RadioButton A8;
    @FXML private RadioButton A9;
    @FXML private RadioButton A10;
    @FXML private RadioButton A11;
    @FXML private RadioButton A12;
    @FXML private RadioButton A13;
    @FXML private RadioButton A14;
    @FXML private RadioButton A15;
    @FXML private RadioButton A16;
    @FXML private RadioButton A17;
    @FXML private RadioButton A18;
    @FXML private RadioButton A19;
    @FXML private RadioButton A20;
    @FXML private RadioButton A21;
    @FXML private RadioButton A22;
    @FXML private RadioButton A23;
    @FXML private RadioButton A24;
    @FXML private RadioButton A25;
    @FXML private RadioButton A26;
    @FXML private RadioButton A27;
    @FXML private RadioButton A28;
    @FXML private RadioButton A29;
    private List<RadioButton> listcb = new ArrayList<>();
    private List<VeXe> listVeXe = new ArrayList();
   
    
    private static final VeXeService vxService = new VeXeService();
    private static final ChuyenDiService cdService = new ChuyenDiService();
    private static final XeKhachService xkService = new XeKhachService();
    
    private int soCho;
   // private int soChoDefault = 29;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.listcb.addAll(Arrays.asList(A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,
                A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29));
         for(RadioButton c : listcb)
                    c.setDisable(true);
        
        this.loadTableView();
        try {
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         this.tbChuyenDi.setRowFactory(l1 -> {
           
            TableRow row  = new TableRow();
            row.setOnMouseClicked(l2 ->{
                ChuyenDi c = (ChuyenDi) this.tbChuyenDi.getSelectionModel().getSelectedItem();     
                this.txtGiaVe.setText(String.valueOf(c.getGiaVe()));
                this.txtDiemKhoiHanh.setText(c.getDiemKhoiHanh());
                this.txtDiemKetThuc.setText(c.getDiemKetThuc());
                this.txtMaChuyenDi.setText(c.getMaChuyenDi());
                this.txtMaXe.setText(c.getMaXe());
              //  this.txtThoiGianKhoiHanh(c.getThoiGianKhoiHanh().toString());
                
            });
            return row;
        });
        
        this.txtTimKiem.textProperty().addListener(cl->{
            try {
                this.tbChuyenDi.setItems(FXCollections.observableList(cdService.getChuyenDiByKwAndSortDate(this.txtTimKiem.getText())));
            } catch (SQLException ex) {
                Utils.getBox(ex.getMessage(), Alert.AlertType.WARNING).show();
            }
        });
         
        this.txtMaChuyenDi.textProperty().addListener(cl->{
            try {
                listVeXe.clear();
                for(RadioButton c : listcb){
                    c.setDisable(false);
                    c.setSelected(false);
                }
                listVeXe.addAll(vxService.getVeXeByMaChuyenDi(this.txtMaChuyenDi.getText()));
                this.setPropertiesRadioButton();
            } catch (SQLException ex) {
                Utils.getBox(ex.getMessage(), Alert.AlertType.WARNING).show();
            } catch (Exception ex){
                 Utils.getBox(ex.getMessage(), Alert.AlertType.WARNING).show();
            }
            
        });
        
        this.txtMaXe.textProperty().addListener(cl->{
            try {
                soCho = xkService.getXeKhachByMaXe(this.txtMaXe.getText()).getSoGhe();
            } catch (SQLException ex) {
                Logger.getLogger(VeXeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (soCho == 24) {
                this.A25.setDisable(true);
                this.A26.setDisable(true);
                this.A27.setDisable(true);
                this.A28.setDisable(true);
                this.A29.setDisable(true);
            } else {
                this.A25.setDisable(false);
                this.A26.setDisable(false);
                this.A27.setDisable(false);
                this.A28.setDisable(false);
                this.A29.setDisable(false);
            }
        });     
        
        for(RadioButton cb : listcb)
              cb.selectedProperty().addListener(il->{
                  if(cb.isSelected())
                       this.txtVitriGhe.setText(cb.getText());
                  else 
                       this.txtVitriGhe.setText("");
              });

        
        
    }    
    
    public void datVeHandler(ActionEvent event){
        
    }
    
    public void banVeHandler(ActionEvent event){
        
    }
    
    private void loadTableView(){
        TableColumn colId = new TableColumn("Mã Chuyến Đi");
        colId.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        colId.setPrefWidth(110);
        
        TableColumn colDiemKhoiHanh = new TableColumn("Điểm Khởi Hành");
        colDiemKhoiHanh.setCellValueFactory(new PropertyValueFactory("diemKhoiHanh"));
        colDiemKhoiHanh.setPrefWidth(110);
        
        TableColumn colDiemKetThuc = new TableColumn("Điểm Kết Thúc");
        colDiemKetThuc.setCellValueFactory(new PropertyValueFactory("diemKetThuc"));
        colDiemKetThuc.setPrefWidth(100);
        
        TableColumn colThoiGianKhoiHanh = new TableColumn("Thời Gian Khởi Hành");
        colThoiGianKhoiHanh.setCellValueFactory(new PropertyValueFactory("thoiGianKhoiHanh"));
        colThoiGianKhoiHanh.setPrefWidth(180);
    
        this.tbChuyenDi.getColumns().addAll(colId, colDiemKhoiHanh, colDiemKetThuc, colThoiGianKhoiHanh);
    }
    
    public void loadTableData() throws SQLException{
        this.tbChuyenDi.setItems(FXCollections.observableList(cdService.getChuyenDiSortByDate()));
    }
    
    public void setPropertiesRadioButton() throws SQLException{
       // listVeXe = 
        for(VeXe v : listVeXe)
             for(int i = 0; i < listcb.size(); i++)
                if(v.getViTriGhe().equals(listcb.get(i).getId()))
                    this.listcb.get(i).setDisable(true);
    }
}
