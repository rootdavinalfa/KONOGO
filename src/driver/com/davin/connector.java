/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */
package driver.com.davin;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import javafx.scene.Node;
import ui.davin.alert;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.sql.*;

public class connector {

    //public static Connection con;

    public static Connection setConnection(){
        String user_LoginSql = "root";
        String Pass = "Davinalfa123";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_test?useSSL=false",user_LoginSql,Pass);
            /*
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT a.id, a.code_part, a.nama_part, a.material_name, a.berat_part, b.id_part, b.date FROM partlist a,date_produksi b WHERE a.id = b.id_part;");
            while (rs.next())
                System.out.println(rs.getInt(1)+ " "+ rs.getString(2)+" "+ rs.getString(3)+" "+ rs.getString(4)+" "+ rs.getString(5)+" "+ rs.getString(6)+" "+ rs.getString(7)+" ");
            con.close();*/
            return con;
        }
        catch (Exception e){
            alert alert = new alert();
            alert.error_sql();
            System.out.println("Tidak dapat menghubungkan,java log :\n"+ e);
        }
        return null;

    }
}
