/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

package ui;


import driver.com.davin.calendar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.aerofx.AeroFX;

//import java.awt.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.lang.Thread.sleep;

public class mainMenuControl implements Initializable {
    @FXML
    private Label dateDisplay;
    @FXML
    private Label timeDisplay;
    public void initialize(URL url, ResourceBundle rb){
        /*
        Thread clock = new Thread(){
            public void run(){
                for(;;){
                    calendar cal = new calendar();
                    int Year = cal.getYear();
                    int Month = cal.getMonth();
                    int Day = cal.getDay();
                    int Hour =cal.getHour();
                    int Min = cal.getMin();
                    int Sec = cal.getSec();
                    //System.out.println(cal.getYear()+cal.getMonth()+cal.getDay()+cal.getHour()+cal.getMin()+cal.getSec());
                    int m = cal.getMonth()+1;
                    //dateDisplay.setText(String.valueOf("Date: "+Day+"/"+Month+"/"+Year));
                    //timeDisplay.setText(String.valueOf("Time: "+Hour+":"+Min+":"+Sec));
                    System.out.println(Year+Month+Day+Hour+Min+Sec);
                    try {
                        sleep(1000);

                    }catch(Exception e){
                        System.out.println(e);
                        //Logger.getLogger(updateFormControl.class.getName()).log(Level.SEVERE);
                    }

                }
            }
        };
        //AeroFX.style();
        clock.start();*/
        setCurrentTime();


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
