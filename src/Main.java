/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

import driver.com.davin.connector;
import java.sql.*;
import javax.swing.*;
import javafx.application.Application;
public class Main {

    public static void main(String args[])  {
        /*
        try {
            Class.forName("driver.com.davin.connector");
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            con = connector.setConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT a.id, a.code_part, a.nama_part, a.material_name, a.berat_part, b.id_part, b.date FROM partlist a,date_produksi b WHERE a.id = b.id_part;");
            while (rs.next())
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " ");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        */
        Application.launch(mainForm.class, args);
    }
}
