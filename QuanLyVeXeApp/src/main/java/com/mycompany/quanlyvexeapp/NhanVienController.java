/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.NhanVien;
import com.mycompany.services.NhanVienService;
import java.io.IOException;


import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Admin
 */

   

public class NhanVienController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<NhanVien> tbDSNV;
    
    @FXML
    private RadioButton byMa;

    @FXML
    private RadioButton byMaLoai;

    @FXML
    private RadioButton byTen;

    @FXML
    private ToggleGroup timKienNV;

    @FXML
    private TextField txtFTimKiem;
    /**
     * Initializes the controller class.
     */
    
    private static final NhanVienService nvService = new NhanVienService();
    
    NhanVien tempNV;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.btnUpdate.setDisable(true);
        this.btnDelete.setDisable(true);
        loadTableView();
        try {
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtFTimKiem.textProperty().addListener(cl->{
            try {
                if(byMa.isSelected()){
                    this.tbDSNV.setItems(FXCollections.observableList(nvService.getNhanVienByMaNV(this.txtFTimKiem.getText())));;  
                }
                else if(byTen.isSelected()){
                    this.tbDSNV.setItems(FXCollections.observableList(nvService.getNhanVienByTen(this.txtFTimKiem.getText())));;
                }else {
                    if (isInt(this.txtFTimKiem) == 1){
                        this.tbDSNV.setItems(FXCollections.observableList(nvService.getNhanVienByMaLoaiNV(this.txtFTimKiem.getText())));;
                    }
                }
            }   catch (SQLException ex) {
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(txtFTimKiem.getText() == null || txtFTimKiem.getText() == ""){
                try {
                    this.loadTableData();
                } catch (SQLException ex) {
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.tbDSNV.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection == null){
                tempNV = null;
                this.btnUpdate.setDisable(true);
                this.btnDelete.setDisable(true);
                this.tbDSNV.getSelectionModel().clearSelection();
            } else{
                tempNV = this.tbDSNV.getSelectionModel().getSelectedItem();            
                this.btnUpdate.setDisable(false);
                this.btnDelete.setDisable(false);
            }
        });
        
        this.tbDSNV.setRowFactory(l1 -> {
            TableRow row  = new TableRow();
            row.setOnMouseClicked(l2 ->{
                
            });
            return row;
        });   
    }    
    
    public void loadTableView(){
        //Table Width Size: 780
        TableColumn colMNV = new TableColumn("Mã nhân viên");
        colMNV.setCellValueFactory(new PropertyValueFactory("maNhanVien"));
        colMNV.setPrefWidth(95);
        // 780 - 95 = 685
        TableColumn colTenNV = new TableColumn("Tên nhân viên");
        colTenNV.setCellValueFactory(new PropertyValueFactory("tenNhanVien"));
        colTenNV.setPrefWidth(125);
        // 685 - 125 = 560
        TableColumn colMaLoaiNV = new TableColumn("Mã loại NV");
        colMaLoaiNV.setCellValueFactory(new PropertyValueFactory("maLoaiNhanVien"));
        colMaLoaiNV.setPrefWidth(95);
        //560 - 95 = 465
        
        TableColumn colNgaySinh = new TableColumn("Ngày sinh");
        colNgaySinh.setCellValueFactory(new PropertyValueFactory("ngaySinh"));
        colNgaySinh.setPrefWidth(130);
        colNgaySinh.setCellFactory(column -> {
            TableCell<NhanVien, Date> cell = new TableCell<NhanVien, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        this.setText(format.format(item));
                    }
                }
            };

            return cell;
         });
        //465 - 130 = 335
        
        TableColumn colSDT = new TableColumn("Số điện thoại");
        colSDT.setCellValueFactory(new PropertyValueFactory("soDienThoai"));
        colSDT.setPrefWidth(110);
        //335 - 110 = 225
        TableColumn colCMND = new TableColumn("CMND");
        colCMND.setCellValueFactory(new PropertyValueFactory("cMND"));
        colCMND.setPrefWidth(105);
        //225 - 105 = 120
        TableColumn colQQ = new TableColumn("Quê quán");
        colQQ.setCellValueFactory(new PropertyValueFactory("queQuan"));
        colQQ.setPrefWidth(120);
        
        this.tbDSNV.getColumns().addAll(colMNV, colTenNV, colMaLoaiNV, colNgaySinh, colSDT, colCMND, colQQ);
    }
    
    public void loadTableData() throws SQLException{     
        ObservableList<NhanVien> nhanVienList = FXCollections.observableArrayList(nvService.getNhanVien());
        this.tbDSNV.setItems(nhanVienList);

    }
    
    public int isInt(TextField tf){
        if (tf.getText() != null || tf.getText().compareTo("") != 0){ 
            try{
               Integer.parseInt(tf.getText());
               return 1; // timKiem = so
            } catch (NumberFormatException ex){
                   return -1; // timKiem ko phai so
                }
            }
        return 0; //Trong
    }
    
    public void insertHandler(ActionEvent event) throws IOException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("FXMLThongTinNhanVien.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(fxmloader.load());
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
        FXMLThongTinNhanVienController controller = fxmloader.getController();
        controller.loadForm("Xác nhận thêm");
        resetFormFromScene();
    }
    
    public void updateHandler(ActionEvent event) throws IOException, SQLException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("FXMLThongTinNhanVien.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(fxmloader.load());
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
        FXMLThongTinNhanVienController controller = fxmloader.getController();
        controller.loadForm("Xác nhận sửa");
        if (tempNV != null){
            controller.loadData(tempNV);
        } else{
            Utils.getBox("Vui lòng chọn nhân viên", Alert.AlertType.WARNING).show();

        }
        resetFormFromScene();

    }
    
    public void deleteHandler(ActionEvent event) throws IOException, SQLException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("FXMLThongTinNhanVien.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(fxmloader.load());
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
        FXMLThongTinNhanVienController controller = fxmloader.getController();
        controller.loadForm("Xác nhận xóa");
        if (tempNV != null){
            controller.loadData(tempNV);
        } else{
            Utils.getBox("Vui lòng chọn nhân viên", Alert.AlertType.WARNING).show();

        }
        resetFormFromScene();
        
    }
    
    public void resetFormFromScene(){
        this.btnUpdate.setDisable(true);
        this.btnDelete.setDisable(true);
        loadTableView();
        try {
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
