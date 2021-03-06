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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.ArrayList;
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
    @FXML private RadioButton bySDT;
    @FXML private Button btnUpdate;
    @FXML private Button btnSell;
    @FXML private Button btnChoose;
    
    private final List<VeXe> listVeXe = new ArrayList(); 
    
    private static final VeXeService vxService = new VeXeService();
    private static final ChuyenDiService cdService = new ChuyenDiService();
    
    public String MaNV;
    
    String pattern = "dd/MM/yyyy HH:mm:ss";
    SimpleDateFormat df = new SimpleDateFormat(pattern);
    private Date dateKhoiHanh;
    private Date dateNow;
    long minutes = 10;
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
            this.cbChuyenDi.setItems(FXCollections.observableList(cdService.getChuyenDiSortByDate()));
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
                    
                    if (cdService.getChuyenDiByMaChuyenDi(maCD).getIsDelete() == 1 || !v.getTrangThai().equals("?????t")) {
                        this.btnUpdate.setDisable(true);
                        this.btnSell.setDisable(true);
                    } else {
                        this.btnUpdate.setDisable(false);
                        this.btnSell.setDisable(false);
                    }
                } catch (SQLException ex) {
                  
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.btnChoose.setDisable(false);
         
                
                try {
                    ChuyenDi c = cdService.getChuyenDiByMaChuyenDi(v.getMaChuyenDi());
                    long tmpp = TimeUnit.MINUTES.toMillis(480);
                    long time = c.getNgayKhoiHanh().getTime() + c.getGioKhoiHanh().getTime() + tmpp;
                    dateKhoiHanh = new Date(time);
                } catch (SQLException ex) {
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                               
                long millis = System.currentTimeMillis();
                dateNow = new Date(millis); 
                
                long s =  dateKhoiHanh.getTime() - dateNow.getTime();
                minutes = TimeUnit.MILLISECONDS.toMinutes(s);
                System.out.println(String.valueOf(minutes));         
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
        
        this.byMa.selectedProperty().addListener(cl->{
            if (byMa.isSelected()) 
                try {
                    this.tbVeXe.setItems(FXCollections.observableList(vxService.getVeXeByMaCD(this.txtTimKiem.getText())));
                } catch (SQLException ex) {
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        
        this.bySDT.selectedProperty().addListener(cl->{
            if (bySDT.isSelected()) 
                try {
                    this.tbVeXe.setItems(FXCollections.observableList(vxService.getVeXeByMaSDT(this.txtTimKiem.getText())));
                } catch (SQLException ex) {
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        
        this.byTen.selectedProperty().addListener(cl->{
            if (byTen.isSelected()) 
                try {
                    this.tbVeXe.setItems(FXCollections.observableList(vxService.getVeXeByMaTen(this.txtTimKiem.getText())));
                } catch (SQLException ex) {
                    Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
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
        
//        this.txtTenKhachHang.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue,
//                    String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    txtTenKhachHang.setText(newValue.replaceAll("[^a-z]", ""));
//                }
//            }
//        });
        
         try {
             if (autoDeleteVeXe()) {              
                 Utils.getBox("H??? th???ng ???? t??? ?????ng h???y nh???ng v?? c???a chuy???n ??i s???p kh???i h??nh trong 30 ph??t v?? ch??a c?? ng?????i nh???n v??",
                         Alert.AlertType.INFORMATION).showAndWait();
             }
         } catch (SQLException ex) {
             Logger.getLogger(DsVeXeController.class.getName()).log(Level.SEVERE, null, ex);
         } 
    }
 
     private void loadTableView(){
        TableColumn colId = new TableColumn("M?? V??");
        colId.setCellValueFactory(new PropertyValueFactory("maVe"));
        colId.setPrefWidth(80);
        
        TableColumn colMaChuyenDi = new TableColumn("M?? Chuy???n ??i");
        colMaChuyenDi.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        colMaChuyenDi.setPrefWidth(90);
        
        TableColumn colTen = new TableColumn("T??n Kh??ch H??ng");
        colTen.setCellValueFactory(new PropertyValueFactory("tenKhachHang"));
        colTen.setPrefWidth(120);
        
        TableColumn colSDT = new TableColumn("S??? ??i???n Tho???i");
        colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));
        colSDT.setPrefWidth(100);
        
        TableColumn colNgayDat = new TableColumn("Ng??y ?????t");
        colNgayDat.setCellValueFactory(new PropertyValueFactory("ngayDat"));
        colNgayDat.setPrefWidth(120);
        colNgayDat.setCellFactory(column -> {
             TableCell<ChuyenDi, Date> cell = new TableCell<ChuyenDi, Date>() {
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
        
        TableColumn colViTriGhe = new TableColumn("V??? Tr?? Gh???");
        colViTriGhe.setCellValueFactory(new PropertyValueFactory("viTriGhe"));
        colViTriGhe.setPrefWidth(90);
        
        TableColumn colTrangTrai = new TableColumn("Tr???ng Th??i");
        colTrangTrai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        colTrangTrai.setPrefWidth(90);
        
        TableColumn colMaNV = new TableColumn("M?? NV");
        colMaNV.setCellValueFactory(new PropertyValueFactory("maNhanVien"));
        colMaNV.setPrefWidth(50);
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(l ->{
            Button btn = new Button("Ho??n v??");
            btn.setOnAction(eh->{
               
                Alert confirm = Utils.getBox("B???n c?? ch???c ch???n h???y v?? n??y kh??ng?", Alert.AlertType.CONFIRMATION);
                confirm.showAndWait().ifPresent(action ->{
                    if(action == ButtonType.OK){
                        TableCell cell = (TableCell)((Button)eh.getSource()).getParent();
                        VeXe v = (VeXe)cell.getTableRow().getItem();  
                        if(v.getTrangThai().equals("B??n")){
                              Utils.getBox("Ho??n th???t b???i: V?? ???? b??n kh??ng th??? ho??n", Alert.AlertType.WARNING).show();
                        }
                        else{
                            try {
                                vxService.deleteVeXe(v);
                                Utils.getBox("Ho??n th??nh c??ng", Alert.AlertType.INFORMATION).show();
                                this.loadTableData();
                                this.resetForm();
                            } catch (SQLException ex) {
                                Utils.getBox("Ho??n th???t b???i: " + ex.getMessage(), Alert.AlertType.WARNING).show();
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
                colViTriGhe, colTrangTrai, colMaNV, colAction);
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
                        List<VeXe> listVeXe = new ArrayList();
                        listVeXe.addAll(vxService.getVeXeByMaCD(v.getMaChuyenDi()));

                        for (VeXe vexe : listVeXe) {
                            if (vexe.getViTriGhe().equals(v.getViTriGhe())) {
                                Utils.getBox("B??n th???t b???i: Ch??? ng???i ???? t???n t???i", Alert.AlertType.WARNING).show();
                                return;
                            }
                        }
                        v.setTrangThai("B??n");                       
                        vxService.updateSellVeXe(v);
                        this.loadTableData();                    
                        ///M??? form In v??          
                        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("FXMLThongTinInVe.fxml"));
                        Dialog dialog = new Dialog();
                        dialog.getDialogPane().setContent(fxmloader.load());
                        dialog.initStyle(StageStyle.TRANSPARENT);
                        dialog.show();
                        FXMLThongTinInVeController controller = fxmloader.getController();
                        controller.loadForm(v.getMaVe(),
                                c.getMaXe(), c.getDiemKhoiHanh(), c.getDiemKetThuc(),
                                v.getViTriGhe(), String.valueOf(c.getGiaVe()), v.getTenKhachHang(), c.getNgayKhoiHanh().toString(),c.getMaChuyenDi());

                        this.resetForm();
                    } catch (SQLException ex) {
                        Utils.getBox("B??n th???t b???i: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                    }
                }
            } else
                Utils.getBox("S??? ??i???n tho???i kh??ng h???p l???: s??? ??i???n tho???i ph???i c?? 10 ch??? s???", Alert.AlertType.WARNING).show();
        } else 
            Utils.getBox("Vui l??ng nh???p ?????y ????? th??ng tin", Alert.AlertType.WARNING).show();
    }
    public void updateHandler(ActionEvent event) throws IOException{
        if(minutes > 60)
            if (checkTextField()) {
                if (checkSDT()) {
                    VeXe v = this.tbVeXe.getSelectionModel().getSelectedItem();
                    
                    if (v != null) {                               
                        try {
                            List<VeXe> listVeXe = new ArrayList();
                            listVeXe.addAll(vxService.getVeXeByMaCD(v.getMaChuyenDi()));

                            for (VeXe vexe : listVeXe) {
                                if (vexe.getViTriGhe().equals(v.getViTriGhe())) {
                                    Utils.getBox("S???a th???t b???i: Ch??? ng???i ???? t???n t???i", Alert.AlertType.WARNING).show();
                                    return;
                                }
                            }
                            
                            vxService.updateSoGheCDBeforeUpdateVeXe(v.getMaChuyenDi());
                            v.setMaChuyenDi(this.cbChuyenDi.getSelectionModel().getSelectedItem().getMaChuyenDi());
                            v.setTenKhachHang(this.txtTenKhachHang.getText());
                            v.setSdt(this.txtSDT.getText());
                            v.setViTriGhe(this.txtViTriGhe.getText());
                            v.setMaNhanVien(this.MaNV);                           
                            vxService.updateVeXe(v);
                            Utils.getBox("S???a th??nh c??ng", Alert.AlertType.INFORMATION).show();
                            this.loadTableData();
                            this.resetForm();
                        } catch (SQLException ex) {
                            Utils.getBox("S???a th???t b???i: " + ex.getMessage(), Alert.AlertType.WARNING).show();
                        }
                    }
                } else 
                    Utils.getBox("S??? ??i???n tho???i kh??ng h???p l???: s??? ??i???n tho???i ph???i c?? 10 ch??? s???", Alert.AlertType.WARNING).show();                
            } else
                Utils.getBox("Vui l??ng nh???p ?????y ????? th??ng tin", Alert.AlertType.WARNING).show();
        else 
            Utils.getBox("S???a th???t b???i: Ch??? ???????c ?????i v?? tr?????c 60 ph??t khi chuy???n ??i kh???i h??nh", Alert.AlertType.WARNING).show();
    }
    
    public boolean autoDeleteVeXe() throws SQLException{
       boolean flag = false;
       this.listVeXe.addAll(vxService.getVeXe());
       for(VeXe v : listVeXe){  
           ChuyenDi c = cdService.getChuyenDiByMaChuyenDi(v.getMaChuyenDi());
           long tmpp = TimeUnit.MINUTES.toMillis(480);
           long time = c.getNgayKhoiHanh().getTime() + c.getGioKhoiHanh().getTime() + tmpp;
           dateKhoiHanh = new Date(time);
           
           long millis = System.currentTimeMillis();
           dateNow = new Date(millis);

           long s = dateKhoiHanh.getTime() - dateNow.getTime();
           minutes = TimeUnit.MILLISECONDS.toMinutes(s);
           if (minutes <= 30) {
               if (v.getTrangThai().equals("?????t")) {
                   vxService.deleteVeXe(v);
                   flag = true;
               }
           }
       }
       return flag;
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
    
     public void loadForm(String MaNV) {
        this.MaNV = MaNV;
    }
}
