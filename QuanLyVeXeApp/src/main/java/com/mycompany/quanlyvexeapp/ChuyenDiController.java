/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;

import com.mycompany.pojo.ChuyenDi;
import com.mycompany.services.ChuyenDiService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ChuyenDiController implements Initializable {
    @FXML private TableView<ChuyenDi> tbChuyenDi;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTableView();
        try {
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        colThoiGianKhoiHanh.setCellValueFactory(new PropertyValueFactory("thoiGIanKhoiHanh"));
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
