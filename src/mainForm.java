/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import static javax.swing.UIManager.getString;


public class mainForm extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    //final ObservableList options = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            //fxmlLoader.setResources(ResourceBundle.getBundle("MyLocale", locale));
            Parent root = FXMLLoader.load(getClass().getResource("/ui/mainMenu.fxml"));//
            primaryStage.setTitle("Prj1_4Jan");
            Scene scene = new Scene(root, 960, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

}

/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.UIManager.getString;


public class mainForm extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    //final ObservableList options = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception {
        //isiComboBox();
        Parent root = FXMLLoader.load(getClass().getResource("/ui/mainMenu.fxml"));//
        primaryStage.setTitle("Prj1_4Jan");
        //FlowPane flowLayout= new FlowPane();
        Scene scene = new Scene(root,960,600);
        //ComboBox code_part = new ComboBox(options);
        //HBox hbox = new HBox(5);
        //hbox.getChildren().addAll(load, code_part);
        //updateFormControl.setDataCodePart();
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /*
    public void isiComboBox(){
        try{

            Class.forName("driver.com.davin.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT code_part FROM partlist;");

            while(rs.next()){
                options.add((rs.getString("code_part")));
            }
        }
        catch (Exception e){

        }
    }
*/
}

