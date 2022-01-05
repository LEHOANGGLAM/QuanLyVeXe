/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quanlyvexeapp;
import com.mycompany.pojo.VeXe;
import com.mycompany.services.ChuyenDiService;
import com.mycompany.services.VeXeService;
import com.mycompany.services.XeKhachService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLGheController implements Initializable {

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
    @FXML private Button btnOk;
    
    private final List<RadioButton> listcb = new ArrayList<>();
    private final List<VeXe> listVeXe = new ArrayList();
    private int soCho;
    public String viTriGhe;
    
    private static final VeXeService vxService = new VeXeService();
    private static final ChuyenDiService cdService = new ChuyenDiService();
    private static final XeKhachService xkService = new XeKhachService();
    public DsVeXeController ds;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         this.listcb.addAll(Arrays.asList(A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16,
                A17, A18, A19, A20, A21, A22, A23, A24, A25, A26, A27, A28, A29));
        
        listcb.forEach(cb -> {
            cb.selectedProperty().addListener(il -> {
                if (cb.isSelected()) {
                    viTriGhe = cb.getText();
                } else {
                    viTriGhe = "";
                }
            });
        });
    }    
    
    public void okHandler(ActionEvent event) throws IOException {
        ds.setTxtVitriGhe(viTriGhe);
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void cancelHandler(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
    
    public void loadForm(String maXe, String MaChuyenDi, DsVeXeController ds){
        try {
            soCho = xkService.getXeKhachByMaXe(maXe).getSoGhe();
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
        
        try {
            listVeXe.addAll(vxService.getVeXeByMaCD(MaChuyenDi));
            listVeXe.forEach(v -> {
                for (int i = 0; i < listcb.size(); i++) {
                    if (v.getViTriGhe().equals(listcb.get(i).getId())) {
                        this.listcb.get(i).setDisable(true);
                    }
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGheController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ds = ds;
    }
}
