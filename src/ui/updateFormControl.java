/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

package ui;

//CORE LIBRARY
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import driver.com.davin.connector;

import javafx.scene.*;
import javafx.stage.Stage;

//SQL CONNECTION DRIVER FOR JAVA
import java.net.URL;
import java.sql.*;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import driver.com.davin.calendar;
import javafx.util.Duration;
import ui.davin.alert;
import static javax.swing.UIManager.getString;

public class updateFormControl implements Initializable {
    //final static ObservableList options = FXCollections.observableArrayList();
        @FXML
        private ComboBox<String> code_part;
        @FXML
        private Label nama_part;
        @FXML
        private Label material;
        @FXML
        private Label berat;
        @FXML
        private TextField ok_textf;
        @FXML
        private TextField ng_textf;
        @FXML
        private CheckBox allowUpdate;
        @FXML
        private Label date;
        @FXML
        private Label kode_produksi;
        @Override
        public void initialize(URL url, ResourceBundle rb) {

            listingCB();
            setCurrentTime();
        }
        private void listingCB(){
            try{

                Class.forName("driver.com.davin.connector");
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT code_part FROM partlist;");

                while(rs.next()){
                    List<String> listPart = new ArrayList<String>();
                    listPart.add(rs.getString("code_part"));

                    //options.add(listPart);
                    code_part.getItems().addAll(FXCollections.observableArrayList(listPart));
                    System.out.println(listPart);
                }
                con.close();
                setCurrentTime();
                //String dateProduksi = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                //System.out.println(dateProduksi);

            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        @FXML protected void setSomeTF(){
            try {
                Class.forName("driver.com.davin.connector");
                Connection con = null;
                Statement stmt = null;
                ResultSet rs = null;
                String id = getID();
                String cp = code_part.getValue();
                calendar cal = new calendar();
                String k = cal.getKodeProduksi();

                String kp = k+id;
                con = connector.setConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT nama_part,material_name,berat_part" +
                        " FROM partlist WHERE code_part='"+cp+"';");
                while (rs.next()) {

                    String a = rs.getString(1);
                    String b = rs.getString(2);
                    String c = rs.getString(3);


                    //code_part.setText(b);
                    calendar ca = new calendar();
                    nama_part.setText(a);
                    material.setText(b);
                    berat.setText(String.valueOf(c));
                    kode_produksi.setText(kp);
                }
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        private String getID(){
            String cp = code_part.getValue();
            String idP="";
            try{
                Class.forName("driver.com.davin.connector");
                Connection con = null;
                Statement stmtgetID = null;
                ResultSet rs = null;
                con = connector.setConnection();
                stmtgetID = con.createStatement();
                rs = stmtgetID.executeQuery("SELECT id FROM partlist WHERE code_part='"+cp+"';");
                while (rs.next()){
                    idP = rs.getString(1);
                }

            }
            catch(Exception e){
                System.out.println(e);
            }
            return idP;

        }
        private String upload(){
            try{
                Calendar time = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String dt = simpleDateFormat.format(time.getTime());
                String aa = ok_textf.getText();
                String b = ng_textf.getText();
                String id = getID();
                int b_ok = Integer.parseInt(aa);
                int b_ng = Integer.parseInt(b);
                int tP = b_ng+b_ok;
                int idP = Integer.parseInt(id);

                Class.forName("driver.com.davin.connector");
                Connection con = null;
                Statement stmtUpload = null;
                ResultSet rs = null;
                String a = kode_produksi.getText();

                con = connector.setConnection();
                stmtUpload = con.createStatement();
                stmtUpload.executeUpdate("INSERT INTO date_produksi (id_part,date,produksi_good,produksi_ng," +
                        "total_produksi,kode_produksi) VALUES ('"+idP+"','"+dt+"','"+b_ok+"','"+b_ng+"','"+tP+"','"+a+"');");
                con.close();
                System.out.println("SUCCESS");
                con.close();
                allowUpdate.setSelected(false);
                clearAll();
            }
            catch (Exception e){
                System.out.println(e);
            }
            return null;
        }
        @FXML protected void update_data(ActionEvent event)throws Exception{
            if (allowUpdate.isSelected()){
                try{
                    try{
                        /*
                        Class.forName("driver.com.davin.connector");
                        Connection con = null;
                        Statement stmt = null;
                        ResultSet rs = null;
                        String a = kode_produksi.getText();
                        String b = code_part.getValue();
                        con = connector.setConnection();
                        stmt = con.createStatement();
                        rs = stmt.executeQuery("SELECT a.code_part,b.kode_produksi FROM partlist a, date_produksi b WHERE b.kode_produksi='"+a+"'&& a.code_part='"+b+"';");

                        if (!rs.next()){
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Tidak dapat melanjutkan");
                            alert.setHeaderText(null);
                            alert.setContentText("Anda belum memasukkan data yang dibutuhkan untuk proses upload");

                            alert.showAndWait();
                            System.out.println("Data not found");
                        }
                        */
                        //else{
                            getID();
                            upload();
                            alert alert = new alert();
                            alert.info_upload();
                       // }
                    }
                    catch (Exception e){
                            System.out.println(e);
                    }


                }
                catch (Exception e){

                }
                System.out.println("OK");
            }
            else{
                //Parent notice = FXMLLoader.load(getClass().getResource("ui/notice.fxml"));
                alert alert = new alert();
                alert.warn_cbUpload();
                System.out.println("ERROR");
            }
        }

        @FXML protected void clearAll(){
            code_part.setValue("");
            nama_part.setText("");
            material.setText("");
            berat.setText("");
            ok_textf.setText("");
            ng_textf.setText("");
            kode_produksi.setText("");
        }
        private void setCurrentTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                date.setText(simpleDateFormat.format(time.getTime()));

                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        }

        /*
        public void start()throws Exception{
            Parent notice = FXMLLoader.load(getClass().getResource("ui/notice.fxml"));
            noticeStage.setTitle("NOTICE!!");
            Scene scene = new Scene(notice,400,400);
            noticeStage.setScene(scene);
            noticeStage.show();
        }*/
}
