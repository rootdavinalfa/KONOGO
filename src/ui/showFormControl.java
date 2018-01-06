/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

package ui;
import com.aquafx_project.AquaFx;
import com.sun.scenario.effect.Effect;
import driver.com.davin.connector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui.davin.alert;
import ui.listShow;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.application.Application.STYLESHEET_MODENA;
import static javafx.application.Application.setUserAgentStylesheet;

public class showFormControl implements Initializable{
    final ToggleGroup group = new ToggleGroup();
    @FXML
    private Label nama_partTXT;
    @FXML
    private Label total_produksiokTXT;
    @FXML
    private Label total_produksingTXT;
    @FXML
    private Label total_produksi;
    @FXML
    private TextField txt_kodeproduksi;
    @FXML
    private TextField txt_kodepart;
    @FXML
    private DatePicker date;
    @FXML
    private RadioButton rdbtn_kp;
    @FXML
    private RadioButton rdbtn_cp;
    @FXML
    private RadioButton rdbtn_date;
    @FXML
    private Label dateDisplay;
    @FXML
    private TableView<listShow> tableView;
    @FXML
    private TableColumn<listShow,String> code_partCOL;
    @FXML
    private TableColumn<listShow,String> nama_partCOL;
    @FXML
    private TableColumn<listShow,String> material_nameCOL;
    @FXML
    private TableColumn<listShow,String> berat__partCOL;
    @FXML
    private TableColumn<listShow,String> dateCOL;
    @FXML
    private TableColumn<listShow,String> produksi_goodCOL;
    @FXML
    private TableColumn<listShow,String> produksi_ngCOL;
    @FXML
    private TableColumn<listShow,String> total_produksiCOL;
    @FXML
    private TableColumn<listShow,String> kode_produksiCOL;


    private ObservableList<listShow> data = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb){

        radiobutton();
        setCurrentTime();

    }
    private void radiobutton(){
        txt_kodeproduksi.setEditable(true);
        txt_kodepart.setEditable(true);
        date.setEditable(true);
        rdbtn_cp.setToggleGroup(group);
        rdbtn_kp.setToggleGroup(group);
        rdbtn_date.setToggleGroup(group);
        rdbtn_kp.setSelected(true);
        if(rdbtn_kp.isSelected()){
            rdbtn_kp();
        }
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    //System.out.println(group.getSelectedToggle().getUserData().toString());
                    RadioButton button = (RadioButton)group.getSelectedToggle();
                    String a =button.getId();
                    if(a =="rdbtn_kp"){
                        rdbtn_kp();

                    }else if(a =="rdbtn_cp"){
                        rdbtn_cp();

                    }else if(a =="rdbtn_date"){
                        rdbtn_date();

                    }
                    System.out.println(a);
                }
            }
        });
    }
    private void setCurrentTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss E");
                                dateDisplay.setText("Time: "+simpleDateFormat.format(time.getTime()));

                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    @SuppressWarnings("Duplicates")
    private void ifKP(){
        String a = txt_kodeproduksi.getText();
        //data = FXCollections.observableArrayList();
        try{

            Class.forName("driver.com.davin.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.code_part,a.nama_part,a.material_name,a.berat_part,b.date,b.produksi_good,b.produksi_ng,b.total_produksi,b.kode_produksi FROM partlist a,date_produksi b WHERE a.id = b.id_part && b.kode_produksi ='"+a+"';");
            while(rs.next()){
                //List<String> table = new ArrayList<String>;
                data.add(new listShow(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                code_partCOL.setCellValueFactory(cellData->cellData.getValue().cpProperty());
                nama_partCOL.setCellValueFactory(cellData->cellData.getValue().npProperty());
                material_nameCOL.setCellValueFactory(cellData->cellData.getValue().mnProperty());
                berat__partCOL.setCellValueFactory(cellData->cellData.getValue().bpProperty());
                dateCOL.setCellValueFactory(cellData->cellData.getValue().dateProperty());
                produksi_goodCOL.setCellValueFactory(cellData->cellData.getValue().pgProperty());
                produksi_ngCOL.setCellValueFactory(cellData->cellData.getValue().pnProperty());
                total_produksiCOL.setCellValueFactory(cellData->cellData.getValue().tpProperty());
                kode_produksiCOL.setCellValueFactory(cellData->cellData.getValue().kpProperty());
            }
            tableView.setItems(getlistShow());
            con.close();
            getTotal();
        }
        catch(Exception e){

        }
    }
    @SuppressWarnings("Duplicates")
    private void ifCP(){
        String b = txt_kodepart.getText();
        try{
            Class.forName("driver.com.davin.connector");
            Connection conCP = null;
            Statement stmtCP = null;
            ResultSet rsCP = null;
            conCP = connector.setConnection();
            stmtCP = conCP.createStatement();
            rsCP = stmtCP.executeQuery("SELECT a.code_part,a.nama_part,a.material_name,a.berat_part,b.date,b.produksi_good,b.produksi_ng,b.total_produksi,b.kode_produksi FROM partlist a,date_produksi b WHERE a.id = b.id_part && a.code_part ='"+b+"';");
            while(rsCP.next()){
                //List<String> table = new ArrayList<String>;
                data.add(new listShow(rsCP.getString(1),rsCP.getString(2),rsCP.getString(3),rsCP.getString(4),rsCP.getString(5),rsCP.getString(6),rsCP.getString(7),rsCP.getString(8),rsCP.getString(9)));
                code_partCOL.setCellValueFactory(cellData->cellData.getValue().cpProperty());
                nama_partCOL.setCellValueFactory(cellData->cellData.getValue().npProperty());
                material_nameCOL.setCellValueFactory(cellData->cellData.getValue().mnProperty());
                berat__partCOL.setCellValueFactory(cellData->cellData.getValue().bpProperty());
                dateCOL.setCellValueFactory(cellData->cellData.getValue().dateProperty());
                produksi_goodCOL.setCellValueFactory(cellData->cellData.getValue().pgProperty());
                produksi_ngCOL.setCellValueFactory(cellData->cellData.getValue().pnProperty());
                total_produksiCOL.setCellValueFactory(cellData->cellData.getValue().tpProperty());
                kode_produksiCOL.setCellValueFactory(cellData->cellData.getValue().kpProperty());
            }
            tableView.setItems(getlistShow());
            conCP.close();
        }
        catch (Exception e){

        }
    }
    @SuppressWarnings("Duplicates")
    private void ifDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String c = (date.getValue()).format(formatter);
        try{
            Class.forName("driver.com.davin.connector");
            Connection conDA = null;
            Statement stmtDA = null;
            ResultSet rsDA = null;
            conDA = connector.setConnection();
            stmtDA = conDA.createStatement();
            rsDA = stmtDA.executeQuery("SELECT a.code_part,a.nama_part,a.material_name,a.berat_part,b.date,b.produksi_good,b.produksi_ng,b.total_produksi,b.kode_produksi FROM partlist a,date_produksi b WHERE a.id = b.id_part && b.date ='"+c+"';");
            while(rsDA.next()){
                //List<String> table = new ArrayList<String>;
                data.add(new listShow(rsDA.getString(1),rsDA.getString(2),rsDA.getString(3),rsDA.getString(4),rsDA.getString(5),rsDA.getString(6),rsDA.getString(7),rsDA.getString(8),rsDA.getString(9)));
                code_partCOL.setCellValueFactory(cellData->cellData.getValue().cpProperty());
                nama_partCOL.setCellValueFactory(cellData->cellData.getValue().npProperty());
                material_nameCOL.setCellValueFactory(cellData->cellData.getValue().mnProperty());
                berat__partCOL.setCellValueFactory(cellData->cellData.getValue().bpProperty());
                dateCOL.setCellValueFactory(cellData->cellData.getValue().dateProperty());
                produksi_goodCOL.setCellValueFactory(cellData->cellData.getValue().pgProperty());
                produksi_ngCOL.setCellValueFactory(cellData->cellData.getValue().pnProperty());
                total_produksiCOL.setCellValueFactory(cellData->cellData.getValue().tpProperty());
                kode_produksiCOL.setCellValueFactory(cellData->cellData.getValue().kpProperty());
            }
            tableView.setItems(getlistShow());
            conDA.close();
        }
        catch (Exception e){

        }
    }

    @FXML protected void clearTable(ActionEvent event){
        clearTF();
    }
    @FXML protected void searchData(ActionEvent event){
        String a = txt_kodeproduksi.getText();
        String b = txt_kodepart.getText();

        if (rdbtn_kp.isSelected()){
            if(a.isEmpty()){
                alert alert = new alert();
                alert.warn_showForm();
            }

            else {
                ifKP();
                System.out.println("A ACTIVE");
            }
        }
        else if(rdbtn_cp.isSelected()){
            if(b.isEmpty()){
                alert alert = new alert();
                alert.warn_showForm();
            }

            else {
                ifCP();
                System.out.println("B ACTIVE");
            }

        }
        else if(rdbtn_date.isSelected()){
            ifDate();
            System.out.println("C ACTIVE");

        }
    }
    private void clearTF(){
        txt_kodepart.setText("");
        txt_kodeproduksi.setText("");
        date.setValue(null);
        tableView.getItems().clear();
    }
    @FXML protected void rdbtn_kp(){
        System.out.print("A");
        txt_kodeproduksi.setEditable(true);
        txt_kodeproduksi.setDisable(false);
        txt_kodepart.setDisable(true);
        date.setDisable(true);
        clearTF();

    }
    @FXML protected void rdbtn_cp(){
        System.out.print("B");
        txt_kodepart.setEditable(true);
        txt_kodepart.setDisable(false);
        txt_kodeproduksi.setDisable(true);
        date.setDisable(true);
        clearTF();
    }
    @FXML protected void rdbtn_date(){
        System.out.print("C");
        date.setEditable(true);
        date.setDisable(false);
        txt_kodepart.setDisable(true);
        txt_kodeproduksi.setDisable(true);
        clearTF();
    }
    public ObservableList<listShow> getlistShow() {
        return data;
    }
    private void getTotal(){
        if(rdbtn_kp.isSelected()){
            //Getting nama_part
            System.out.println("OKOKOKO");
            listShow tableRow = tableView.getItems().get(0);
            System.out.println(tableRow);
            System.out.println(tableView.getSelectionModel().getSelectedItem().getNP());
           
        }
        else if (rdbtn_cp.isSelected()){

        }
        else if (rdbtn_date.isSelected()){

        }
    }

}
