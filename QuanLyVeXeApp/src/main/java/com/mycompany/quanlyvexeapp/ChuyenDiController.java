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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

 
import org.apache.commons.lang3.RandomStringUtils;
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
    @FXML private DatePicker dpNgayKhoiHanh;
    @FXML private Button btnUpdate;
    @FXML private TextField timKiem;
    @FXML private TextField gio;
 
    private static final ChuyenDiService cdService = new ChuyenDiService();
    private static final XeKhachService xkService = new XeKhachService();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter
                = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }

        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO         
        this.dpNgayKhoiHanh.setConverter(converter);
        
        this.btnUpdate.setDisable(true);
        
        try {
            this.cbXeKhach.setItems(FXCollections.observableList(xkService.getXeKhach()));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.loadTableView();
        try {
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.tbChuyenDi.setRowFactory(l1 -> {
            TableRow row  = new TableRow();
            row.setOnMouseClicked(l2 ->{
                ChuyenDi c = this.tbChuyenDi.getSelectionModel().getSelectedItem();     
                this.giaVe.setText(String.valueOf(c.getGiaVe()));
                this.diemKhoiHanh.setText(c.getDiemKhoiHanh());
                this.diemKetThuc.setText(c.getDiemKetThuc());
                Calendar calen = Calendar.getInstance();
                calen.setTime(c.getNgayKhoiHanh());
                this.dpNgayKhoiHanh.setValue(LocalDate.of(calen.get(Calendar.YEAR),calen.get(Calendar.MONTH)+1,calen.get(Calendar.DAY_OF_MONTH)));
                this.btnUpdate.setDisable(false);
                try {
                    this.cbXeKhach.getSelectionModel().select(xkService.getXeKhachByMaXe(c.getMaXe()));
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                
                this.gio.setText(sdf.format(c.getGioKhoiHanh()));
              
            });
            return row;
        });
        
     
        this.timKiem.textProperty().addListener(cl->{
            try {
                this.tbChuyenDi.setItems(FXCollections.observableList(cdService.getChuyenDiByKw(this.timKiem.getText())));
            } catch (SQLException ex) {
                Utils.getBox(ex.getMessage(), Alert.AlertType.WARNING).show();
            }
        });
        
        giaVe.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    giaVe.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        this.diemKhoiHanh.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (newValue.matches("[0-9]")) {
                    diemKhoiHanh.setText(newValue.replaceAll("[0-9]", ""));
                }
            }
        });

        this.diemKetThuc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (newValue.matches("[0-9]")) {
                    diemKetThuc.setText(newValue.replaceAll("[0-9]", ""));
                }
            }
        });
    }    
     
    
    public void addChuyenDiHandler(ActionEvent event){
        if (checkTextField()) {
            LocalDate local = this.dpNgayKhoiHanh.getValue();
            Date date = Date.valueOf(local); 
            try {
                Time time = Time.valueOf(gio.getText() + ":00");
                ChuyenDi c = new ChuyenDi(RandomStringUtils.randomNumeric(6), this.cbXeKhach.getSelectionModel().getSelectedItem().getMaXe(),
                        Integer.parseInt(this.giaVe.getText()), date, time, this.diemKhoiHanh.getText(), this.diemKetThuc.getText(),
                        this.cbXeKhach.getSelectionModel().getSelectedItem().getSoGhe(), 0, 0);

                if (c.getGiaVe() >50000) {
                    long tmpp = TimeUnit.MINUTES.toMillis(480);
                    if ((c.getNgayKhoiHanh().getTime() + c.getGioKhoiHanh().getTime() + tmpp) > System.currentTimeMillis()) {
                        cdService.addChuyenDi(c);
                        Utils.getBox("Th??m th??nh c??ng", Alert.AlertType.INFORMATION).show();
                        this.loadTableData();
                        this.resetForm();
                    } else {
                        Utils.getBox("Th??m th???t b???i: Th???i gian kh???i h??nh ph???i l???n h??n th???i gian hi???n t???i", Alert.AlertType.INFORMATION).show();
                    }
                } else {
                    Utils.getBox("Gi?? v?? ph???i l???n h??n 50000 VND", Alert.AlertType.INFORMATION).show();
                }
            } catch (SQLException ex) {
                Utils.getBox("Th??m th???t b???i: " + ex.getMessage(), Alert.AlertType.WARNING).show();
            } catch (Exception ex) {
                Utils.getBox("Vui l??ng nh???p th???i gian theo ?????nh dang Gi???:Ph??t", Alert.AlertType.INFORMATION).show();
            }
        } else {
            Utils.getBox("Vui l??ng nh???p ?????y ????? th??ng tin", Alert.AlertType.WARNING).show();
        }
    }
    
    public void updateChuyenDiHandler(ActionEvent event){
        if (checkTextField()) {
            LocalDate local = this.dpNgayKhoiHanh.getValue();
            Date date = Date.valueOf(local); 
            try {
                Time time = Time.valueOf(gio.getText() + ":00");
                ChuyenDi c = new ChuyenDi(this.tbChuyenDi.getSelectionModel().getSelectedItem().getMaChuyenDi(), this.cbXeKhach.getSelectionModel().getSelectedItem().getMaXe(),
                        Integer.parseInt(this.giaVe.getText()), date, time, this.diemKhoiHanh.getText(), this.diemKetThuc.getText(),
                        this.cbXeKhach.getSelectionModel().getSelectedItem().getSoGhe(), 0, 0);
                if (c != null) {
                    if (c.getGiaVe() >50000) {
                        long tmpp = TimeUnit.MINUTES.toMillis(480);
                        if ((c.getNgayKhoiHanh().getTime() + c.getGioKhoiHanh().getTime() + tmpp) > System.currentTimeMillis()) {
                            cdService.updateChuyenDi(c);
                            Utils.getBox("S???a th??nh c??ng", Alert.AlertType.INFORMATION).show();
                            this.loadTableData();
                            this.resetForm();
                        } else {
                            Utils.getBox("S???a th???t b???i: Th???i gian kh???i h??nh ph???i l???n h??n th???i gian hi???n t???i", Alert.AlertType.INFORMATION).show();
                        }
                    } else {
                        Utils.getBox("Gi?? v?? ph???i l???n h??n 50000", Alert.AlertType.INFORMATION).show();
                    }
                }
            } catch (SQLException ex) {
                Utils.getBox("S???a th???t b???i: " + ex.getMessage(), Alert.AlertType.WARNING).show();
            } catch (Exception ex) {
                Utils.getBox("Vui l??ng nh???p th???i gian theo ?????nh dang Gi???:Ph??t", Alert.AlertType.WARNING).show();
            }
        } else {
            Utils.getBox("Vui l??ng nh???p ?????y ????? th??ng tin", Alert.AlertType.WARNING).show();
        }
    }
    
    private void loadTableView(){
        TableColumn colId = new TableColumn("M?? Chuy???n ??i");
        colId.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        colId.setPrefWidth(110);
        
        TableColumn colXeId = new TableColumn("M?? Xe");
        colXeId.setCellValueFactory(new PropertyValueFactory("maXe"));
        colXeId.setPrefWidth(60);
        
        TableColumn colGiaVe = new TableColumn("Gi?? V??");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        colGiaVe.setPrefWidth(60);
        
        TableColumn colNgayKhoiHanh = new TableColumn("Ng??y Kh???i H??nh");
        colNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory("ngayKhoiHanh"));
        colNgayKhoiHanh.setCellFactory(column -> {
            TableCell<ChuyenDi, Date> cell = new TableCell<ChuyenDi, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) 
                        setText(null);
                    else 
                        this.setText(format.format(item));
                }
            };

            return cell;
        });
        colNgayKhoiHanh.setPrefWidth(110);
        
        TableColumn colGioKhoiHanh = new TableColumn("Gi??? Kh???i H??nh");
        colGioKhoiHanh.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
        colGioKhoiHanh.setPrefWidth(90);
        
        TableColumn colDiemKhoiHanh = new TableColumn("??i???m Kh???i H??nh");
        colDiemKhoiHanh.setCellValueFactory(new PropertyValueFactory("diemKhoiHanh"));
        colDiemKhoiHanh.setPrefWidth(120);
        
        TableColumn colDiemKetThuc = new TableColumn("??i???m K???t Th??c");
        colDiemKetThuc.setCellValueFactory(new PropertyValueFactory("diemKetThuc"));
        colDiemKetThuc.setPrefWidth(120);
        
        TableColumn colGheTrong = new TableColumn("S??? Gh??? Tr???ng");
        colGheTrong.setCellValueFactory(new PropertyValueFactory("soGheTrong"));
        colGheTrong.setPrefWidth(90);
        
        TableColumn colGheDat = new TableColumn("S??? Gh??? ?????t");
        colGheDat.setCellValueFactory(new PropertyValueFactory("soGheDat"));
        colGheDat.setPrefWidth(80);
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(l ->{
            Button btn = new Button("X??a");
            btn.setOnAction(eh->{
                Alert confirm = Utils.getBox("B???n c?? ch???c ch???n x??a chuy???n ??i n??y kh??ng?", Alert.AlertType.CONFIRMATION);
                confirm.showAndWait().ifPresent(action ->{
                    if(action == ButtonType.OK){
                        TableCell cell = (TableCell)((Button)eh.getSource()).getParent();
                        ChuyenDi c = (ChuyenDi)cell.getTableRow().getItem();  
                        try {                                                    
                            cdService.deleteChuyenDi(c.getMaChuyenDi());                        
                            Utils.getBox("X??a th??nh c??ng", Alert.AlertType.INFORMATION).show();
                            this.loadTableData();
                            this.resetForm();
                        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
                            try {
                                cdService.softDeleteChuyenDi(c.getMaChuyenDi());
                                Utils.getBox("X??a m???m th??nh c??ng", Alert.AlertType.INFORMATION).show();
                                this.loadTableData();
                                this.resetForm();
                            } catch (SQLException ex1) {
                                Utils.getBox("X??a th???t b???i: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                            }                          
                        }
                        catch (SQLException ex) {
                            Utils.getBox("X??a th???t b???i: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                        }
                    }
                }); 
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        this.tbChuyenDi.getColumns().addAll(colId, colXeId, colGiaVe, colNgayKhoiHanh, colGioKhoiHanh, colDiemKhoiHanh,
                colDiemKetThuc, colGheTrong, colGheDat, colAction);
    }
    
    public void loadTableData() throws SQLException{
        this.tbChuyenDi.setItems(FXCollections.observableList(cdService.getChuyenDi()));
    }

    public void closeHandler(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
    
    public void resetForm(){
        this.tbChuyenDi.getSelectionModel().select(null);
        this.cbXeKhach.getSelectionModel().select(null);
        this.giaVe.setText("");
        this.diemKetThuc.setText("");
        this.diemKhoiHanh.setText("");
        this.btnUpdate.setDisable(true);
        this.dpNgayKhoiHanh.setValue(null);
        this.gio.setText("");
    }
    
    private boolean checkTextField() {
        if (this.giaVe.getText() == "" || this.diemKhoiHanh.getText() == ""
                || this.diemKetThuc.getText() == "" || this.cbXeKhach.getSelectionModel().getSelectedItem() == null
                || this.dpNgayKhoiHanh.getValue() == null) 
            return false;
        return true;
    }
}
