/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

package ui;


import driver.com.davin.calendar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.aerofx.AeroFX;

//import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.lang.Thread.sleep;

import lang.loadLang;
public class mainMenuControl implements Initializable {
    @FXML
    private Label dateDisplay;
    @FXML
    private Label timeDisplay;
    @FXML
    private Button btn_upload;
    @FXML
    private Button btn_show;

    private Locale locale;
    private ResourceBundle bundle;
    public void initialize(URL url, ResourceBundle rb){
        //load("en");
        setCurrentTime();
        langRead();

    }
    private void writeLang(){
        String fileName = "LOCALE.lang";
        try{
            PrintWriter outputStream = new PrintWriter(fileName);
            outputStream.println("en");
            outputStream.close();
            System.out.println("OKAY,Please start app again!");
            Platform.exit();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    private void langRead(){
        try {
            File file = new File("LOCALE.lang");
            StringBuffer contents = new StringBuffer();
            BufferedReader reader = null;

            reader = new BufferedReader(new FileReader(file));
            String text = null;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text);
            }
            reader.close();
            System.out.println(contents.toString());
            String lan = contents.toString();
            load(""+lan+"");

    }
    catch(Exception e){
        System.out.println(e);
        System.out.println("Trying to make default configuration...");
        writeLang();
    }
    }

    private void load(String lang){
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("lang.MyLocale",locale);
        btn_upload.setText(bundle.getString("upload.data"));
        btn_show.setText(bundle.getString("show.data"));
    }
    private void writeLoc(String lang){
        try {
            PrintWriter outputStream = new PrintWriter("LOCALE.lang");
            outputStream.println(lang);
            outputStream.close();
        }
        catch(IOException E){
            E.getStackTrace();
        }
        }
    @FXML protected void btn_en(ActionEvent event){
        load("en");
        writeLoc("en");
    }
    @FXML protected void btn_jp(ActionEvent event){
        load("ja");
        writeLoc("ja");
    }
    @FXML protected void uploadForm() throws Exception{
        Parent uploadForm = FXMLLoader.load(getClass().getResource("/ui/updateForm.fxml"));
        Scene scene = new Scene(uploadForm,960,480);
        Stage uploadFormstage = new Stage();
        uploadFormstage.setTitle("Upload Form | prj1_4");
        uploadFormstage.setScene(scene);

        uploadFormstage.show();

    }
    @FXML protected void showForm() throws Exception{
        Parent showForm = FXMLLoader.load(getClass().getResource("/ui/showForm.fxml"));
        Scene scene = new Scene(showForm,800,466);
        Stage showFormstage = new Stage();
        showFormstage.setTitle("Upload Form | prj1_4");
        showFormstage.setScene(scene);
        showFormstage.show();

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

}
