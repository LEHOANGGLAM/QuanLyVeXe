/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.conf.Utils;
import com.mycompany.pojo.ChuyenDi;
import com.mycompany.pojo.XeKhach;
import com.mycompany.services.ChuyenDiService;
import com.mycompany.services.XeKhachService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ChuyenDiController implements Initializable {
    @FXML private TableView<ChuyenDi> tbChuyenDi;
    @FXML private ComboBox<XeKhach> cbXeKhach;
    @FXML private TextField diemKhoiHanh;
    @FXML private TextField diemKetThuc;
    @FXML private TextField giaVe;
    @FXML private DatePicker dpThoiGianKhoiHanh;
//    String stringDate = "22/01/2016";
//    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
    //SimpleDateFormat.("yyyy/MM/dd").parse(this.thoiGianKhoiHanh.getText())
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XeKhachService x = new XeKhachService();
        try {
            this.cbXeKhach.setItems(FXCollections.observableList(x.getXeKhach()));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadTableView();
        try {
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void addChuyenDiHandler(ActionEvent event) throws SQLException{
        ChuyenDi c = new ChuyenDi(UUID.randomUUID().toString(), this.cbXeKhach.getSelectionModel().getSelectedItem().getMaXe(),
                Integer.parseInt(this.giaVe.getText()), null, this.diemKhoiHanh.getText(), this.diemKetThuc.getText(), 
                this.cbXeKhach.getSelectionModel().getSelectedItem().getSoGhe(), 0);
        ChuyenDiService s = new ChuyenDiService();  
        try {
            s.addChuyenDi(c);
            Utils.getBox("Thêm thành công", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Utils.getBox("Thêm thất bại", Alert.AlertType.WARNING).show();
        }
        this.loadTableData();
    }
    
    private void loadTableView(){
        TableColumn colId = new TableColumn("Mã Chuyến Đi");
        colId.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        colId.setPrefWidth(100);
        
        TableColumn colXeId = new TableColumn("Mã Xe");
        colXeId.setCellValueFactory(new PropertyValueFactory("maXe"));
        colXeId.setPrefWidth(60);
        
        TableColumn colGiaVe = new TableColumn("Giá Vé");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        colGiaVe.setPrefWidth(60);
        
        TableColumn colThoiGianKhoiHanh = new TableColumn("Thời Gian Khởi Hành");
        colThoiGianKhoiHanh.setCellValueFactory(new PropertyValueFactory("thoiGianKhoiHanh"));
        colThoiGianKhoiHanh.setPrefWidth(200);
        
        TableColumn colDiemKhoiHanh = new TableColumn("Điểm Khởi Hành");
        colDiemKhoiHanh.setCellValueFactory(new PropertyValueFactory("diemKhoiHanh"));
        colDiemKhoiHanh.setPrefWidth(150);
        
        TableColumn colDiemKetThuc = new TableColumn("Điểm Kết Thúc");
        colDiemKetThuc.setCellValueFactory(new PropertyValueFactory("diemKetThuc"));
        colDiemKetThuc.setPrefWidth(150);
        
        TableColumn colGheTrong = new TableColumn("Số Ghế Trống");
        colGheTrong.setCellValueFactory(new PropertyValueFactory("soGheTrong"));
        colGheTrong.setPrefWidth(90);
        
        TableColumn colGheDat = new TableColumn("Số Ghế Đặt");
        colGheDat.setCellValueFactory(new PropertyValueFactory("soGheDat"));
        colGheDat.setPrefWidth(80);
        
        this.tbChuyenDi.getColumns().addAll(colId, colXeId, colGiaVe, colThoiGianKhoiHanh, colDiemKhoiHanh, colDiemKetThuc, colGheTrong, colGheDat);
    }
    
    public void loadTableData() throws SQLException{
        ChuyenDiService c = new ChuyenDiService();
        this.tbChuyenDi.setItems(FXCollections.observableList(c.getChuyenDi()));
    }
}
