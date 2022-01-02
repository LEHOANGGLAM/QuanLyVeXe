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
import com.mycompany.services.XeKhachService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
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
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import org.apache.commons.lang3.RandomStringUtils;

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
    private final List<RadioButton> listcb = new ArrayList<>();
    private final List<VeXe> listVeXe = new ArrayList();  
    
    private static final VeXeService vxService = new VeXeService();
    private static final ChuyenDiService cdService = new ChuyenDiService();
    private static final XeKhachService xkService = new XeKhachService();
    
    private int soCho;
    String pattern = "dd/MM/yyyy";
    SimpleDateFormat df = new SimpleDateFormat(pattern);
    private Date dateKhoiHanh;
    private Date dateNow;
    long minutes=10;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.listcb.addAll(Arrays.asList(A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16,
                A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29));
        listcb.forEach(c -> {
            c.setDisable(true);
        });
    

        this.loadTableView();
        try {
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.tbChuyenDi.setRowFactory(l1 -> {

            TableRow row = new TableRow();
            row.setOnMouseClicked(l2 -> {
                ChuyenDi c = (ChuyenDi) this.tbChuyenDi.getSelectionModel().getSelectedItem();
                this.txtGiaVe.setText(String.valueOf(c.getGiaVe()));
                this.txtDiemKhoiHanh.setText(c.getDiemKhoiHanh());
                this.txtDiemKetThuc.setText(c.getDiemKetThuc());
                this.txtMaChuyenDi.setText(c.getMaChuyenDi());
                this.txtMaXe.setText(c.getMaXe());
                this.txtThoiGianKhoiHanh.setText(df.format(c.getNgayKhoiHanh()) + " " + c.getGioKhoiHanh().toString());
                try {
                    this.txtBienSoXe.setText(xkService.getBienSoXeByMaXe(c.getMaXe()));
                } catch (SQLException ex) {
                    Logger.getLogger(VeXeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                long tmpp = TimeUnit.MINUTES.toMillis(480); // Không hiểu vì sao c.getGioKhoiHanh().getTime() bị mất 480p nên dòng này để add thêm 480p
                long time = c.getNgayKhoiHanh().getTime() + c.getGioKhoiHanh().getTime() + tmpp;
                dateKhoiHanh = new Date(time);
              //  System.out.println(df.format(dateKhoiHanh));
                
                long millis = System.currentTimeMillis();
                dateNow = new Date(millis);
              
                long s =  dateKhoiHanh.getTime() - dateNow.getTime();
                minutes = TimeUnit.MILLISECONDS.toMinutes(s);
                System.out.println(String.valueOf(minutes));  
          
            });
            return row;
        });
         
        this.txtTimKiem.textProperty().addListener(cl -> {
            try {
                this.tbChuyenDi.setItems(FXCollections.observableList(cdService.getChuyenDiByKwAndSortDate(this.txtTimKiem.getText())));
            } catch (SQLException ex) {
                Utils.getBox(ex.getMessage(), Alert.AlertType.WARNING).show();
            }
        });

        this.txtMaChuyenDi.textProperty().addListener(cl -> {
            try {
                listVeXe.clear();
                listVeXe.addAll(vxService.getVeXeByMaCD(this.txtMaChuyenDi.getText()));
               // if (minutes < 5) {
                    for (RadioButton c : listcb) {
                        c.setDisable(false);
                        c.setSelected(false);
                    }
                    this.setPropertiesRadioButton();
             // }
//                else 
//                    Utils.getBox("Chuyến đi này đã chuẩn bị khởi hành, các ghế trống sẽ được thu hồi", Alert.AlertType.WARNING).show();
            } catch (SQLException ex) {
                Utils.getBox(ex.getMessage(), Alert.AlertType.WARNING).show();
            } catch (Exception ex) {
                Utils.getBox(ex.getMessage(), Alert.AlertType.WARNING).show();
            }

        });

          this.txtMaXe.textProperty().addListener(cl -> {
              try {
                  soCho = xkService.getXeKhachByMaXe(this.txtMaXe.getText()).getSoGhe();

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
                      this.setPropertiesRadioButton();
                  }
              } catch (SQLException ex) {
                  Logger.getLogger(VeXeController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });

        listcb.forEach(cb -> {
            cb.selectedProperty().addListener(il -> {
                if (cb.isSelected()) {
                    this.txtVitriGhe.setText(cb.getText());
                } else {
                    this.txtVitriGhe.setText("");
                }
            });
        });
        txtSdt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtSdt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    
    public void datVeHandler(ActionEvent event) {
        if (minutes > 60) {
            if (checkTextField()) {
                if (checkSDT()) {
                    VeXe v = new VeXe(RandomStringUtils.randomNumeric(6), this.txtHoTenKhachHang.getText(), Date.valueOf(LocalDate.now()),
                            this.txtSdt.getText(), this.txtMaChuyenDi.getText(), this.txtVitriGhe.getText(), "Đặt");
                    try {
                        vxService.addVeXe(v);
                        Utils.getBox("Đặt vé thành công", Alert.AlertType.INFORMATION).show();
                        this.resetForm();
                    } catch (SQLException ex) {
                        Utils.getBox("Đặt vé thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                    }
                } else 
                    Utils.getBox("Số điện thoại không hợp lệ: số điện thoại phải có 10 chữ số", Alert.AlertType.WARNING).show();             
            } else 
                Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.WARNING).show();       
        } else
            Utils.getBox("Đặt vé thất bại: Chỉ được Đặt vé trước 60 phút khi chuyến xe khởi hành.", Alert.AlertType.WARNING).show(); 
    }
    
    public void banVeHandler(ActionEvent event) throws IOException{
        if (minutes > 5) {
            if (checkTextField()) {
                if (checkSDT()) {
                    String maVe = RandomStringUtils.randomNumeric(6);
                    VeXe v = new VeXe(maVe, this.txtHoTenKhachHang.getText(), Date.valueOf(LocalDate.now()),
                            this.txtSdt.getText(), this.txtMaChuyenDi.getText(), this.txtVitriGhe.getText(), "Bán");
                    try {
                        vxService.addVeXe(v);
                        ///Mở form In vé          
                        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("FXMLThongTinInVe.fxml"));
                        Dialog dialog = new Dialog();
                        dialog.getDialogPane().setContent(fxmloader.load());
                        dialog.initStyle(StageStyle.TRANSPARENT);
                        dialog.show();
                        FXMLThongTinInVeController controller = fxmloader.getController();
                        controller.loadForm(maVe,
                                this.txtBienSoXe.getText(), this.txtDiemKhoiHanh.getText(), this.txtDiemKetThuc.getText(),this.txtVitriGhe.getText(),
                                this.txtGiaVe.getText(), this.txtHoTenKhachHang.getText(), this.txtThoiGianKhoiHanh.getText());

                        this.resetForm();
                    } catch (SQLException ex) {
                        Utils.getBox("Bán vé thất bại: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                    }
                } else 
                    Utils.getBox("Số điện thoại không hợp lệ: số điện thoại phải có 10 chữ số", Alert.AlertType.WARNING).show();              
            } else 
                Utils.getBox("Vui lòng nhập đầy đủ thông tin", Alert.AlertType.WARNING).show();          
        } else 
            Utils.getBox("Bán vé thất bại: Chỉ được bán vé trước 5 phút khi chuyến xe khởi hành.", Alert.AlertType.WARNING).show();      
    }
    
     public void openFormDSVeXeHandler(ActionEvent event) throws IOException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("DsVeXe.fxml"));
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(fxmloader.load());
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
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
        
        TableColumn colNgayKhoiHanh = new TableColumn("Ngày Khởi Hành");
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
       
        TableColumn colGioKhoiHanh = new TableColumn("Giờ Khởi Hành");
        colGioKhoiHanh.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
        colGioKhoiHanh.setPrefWidth(90);
    
        this.tbChuyenDi.getColumns().addAll(colId, colDiemKhoiHanh, colDiemKetThuc, colNgayKhoiHanh, colGioKhoiHanh);
    }
    
    public void loadTableData() throws SQLException{
        this.tbChuyenDi.setItems(FXCollections.observableList(cdService.getChuyenDiSortByDate()));
    }
    
    public void setPropertiesRadioButton() throws SQLException{
        listVeXe.forEach(v -> {
            for(int i = 0; i < listcb.size(); i++)
                if(v.getViTriGhe().equals(listcb.get(i).getId()))
                    this.listcb.get(i).setDisable(true);
        });
    }
    
    public void resetForm(){
        this.txtHoTenKhachHang.setText("");
        this.txtSdt.setText("");
        this.txtTimKiem.setText("");
        this.txtVitriGhe.setText("");
        listcb.forEach(c -> {
            c.setDisable(true);
        });
    }
    
    private boolean checkTextField(){
        if(this.txtHoTenKhachHang.getText() == "" || this.txtSdt.getText() == ""
                || this.txtVitriGhe.getText() == "" || this.txtMaChuyenDi.getText() == "")
            return false;
        return true;
    }
    
    private boolean checkSDT(){
        if(this.txtSdt.getText().length() != 10)
            return false;
        return true;
    }
}
