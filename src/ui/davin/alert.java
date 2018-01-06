/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

package ui.davin;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;

public class alert {
    public void warn_showForm(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Tidak dapat melanjutkan");
        alert.setHeaderText(null);
        alert.setContentText("Anda belum memasukkan data yang dibutuhkan untuk search (null data)");
        alert.showAndWait();
    }
    public void error_upload(){

    }
    public void error_sql(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("ERROR Connection");
        alert.setContentText("Tidak dapat menghubungkan ke database.Silahkan hubungi admin");

        Exception ex = new ConnectException("Tidak dapat menghubungkan ke database.Silahkan hubungi admin");

//Log Crawler
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// DIALOG
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
    public void info_upload(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("UPLOAD DATA SUCCESSFULLY");
        alert.setHeaderText(null);
        alert.setContentText("Data yang baru anda masukkan berhasil ter Input");

        alert.showAndWait();
    }
    public void info_export(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("EXPORT DATA SUCCESSFULLY");
        alert.setHeaderText(null);
        alert.setContentText("Data telah terExport,silahkan cek di folder yang sama dengan program ini");

        alert.showAndWait();
    }
    public void warn_cbUpload(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ILLEGAL REQUEST");
        alert.setHeaderText("Tidak Dapat Melanjutkan Proses");
        alert.setContentText("Pastikan untuk menceklist pada checkbox allow update jika ingin merubah data.");

        alert.showAndWait();
    }
}
