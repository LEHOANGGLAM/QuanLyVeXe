/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;


import com.mycompany.conf.Utils;
import com.mycompany.pojo.DoanhThuChuyenDi;
import com.mycompany.services.DoanhThuChuyenDiService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ThongKeDoanhThuController implements Initializable {
    
   
    @FXML
    private DatePicker dpEnd;
    @FXML
    private DatePicker dpStart;
    @FXML
    private Button btnTimKiem;
    @FXML
    private Text errorTxting;     
    @FXML
    private TableView<DoanhThuChuyenDi> bangThongKe;
    @FXML
    private Text txtTongDoanhThu;
    /**
     * Initializes the controller class.
     */
    private static final DoanhThuChuyenDiService dtcdService = new DoanhThuChuyenDiService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dpStart.valueProperty().addListener((v, oldVal, newVal) ->{
            dpEnd.setDayCellFactory(p -> new DateCell(){
                @Override
                public void updateItem(LocalDate date, boolean empty){
                    super.updateItem(date, empty);
                    
                    LocalDate dpStartValue = dpStart.getValue();
                    setDisable(empty || date.compareTo(LocalDate.from(dpStartValue)) < 0);
                }
            });
            
        dpEnd.setValue(dpStart.getValue());
        });
        
        this.loadTableView();
    }    
    
    public void timKiemThongKeHandler(ActionEvent event){
        if (dpStart.getValue() == null && dpEnd.getValue() == null){
                 Utils.getBox("Vui lòng lựa chọn ngày thống kê", Alert.AlertType.WARNING).show();
        } else if (dpStart.getValue() == null || dpEnd.getValue() == null){      
            if (dpStart.getValue() == null){
                this.errorTxting.setText("Vui lòng lựa chọn ngày bắt đầu");
            } else if(dpEnd.getValue() == null){
                this.errorTxting.setText("Vui lòng lựa chọn ngày kết thúc");
            }
        } else{
            this.errorTxting.setText("");
            try {
                String sD1 = dpStart.getValue().toString();
                String sD2 = dpStart.getValue().toString();

                Date datefromPicker1 = Date.valueOf(sD1);
                Date datefromPicker2 = Date.valueOf(sD2);
                ObservableList<DoanhThuChuyenDi> doanhThuChuyenDiList = FXCollections.observableArrayList(dtcdService.getDoanhThuFromDateToDate(datefromPicker1, datefromPicker2));
                int total = dtcdService.getTongDoanhThuFromDateToDate(datefromPicker1, datefromPicker2);

                this.bangThongKe.setItems(doanhThuChuyenDiList);        
                this.txtTongDoanhThu.setText(String.format("%,d", total));
            } catch (SQLException ex) {

            }
        }
    }
    
    public void loadTableView(){
        //Table Width Size: 880
        TableColumn colMCD = new TableColumn("Mã chuyến đi");
        colMCD.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        colMCD.setPrefWidth(100);
        // 880 - 100 = 780
        TableColumn colDate = new TableColumn("Thời gian");
        colDate.setCellValueFactory(new PropertyValueFactory("ngay"));
        colDate.setPrefWidth(240);
        // 780 - 240 = 540
        TableColumn colSoVe = new TableColumn("Tổng vé trong ngày");
        colSoVe.setCellValueFactory(new PropertyValueFactory("soVeDat"));
        colSoVe.setPrefWidth(150);
        //540 - 150 = 390
        TableColumn colDoanhThu = new TableColumn("Doanh thu");
        colDoanhThu.setCellValueFactory(new PropertyValueFactory("doanhThu"));
        colDoanhThu.setPrefWidth(390);
        
        this.bangThongKe.getColumns().addAll(colMCD, colDate, colSoVe, colDoanhThu);
    }
    
}   
