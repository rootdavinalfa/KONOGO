/*
 * Copyright (c) $today.year.Davin Alfarizky Putra Basudewa.This software is for educational only
 */

package ui;

import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public class listShow {
    private SimpleStringProperty code_part;
    private SimpleStringProperty nama_part;
    private SimpleStringProperty material_name;
    private SimpleStringProperty berat_part;
    private SimpleStringProperty date;
    private SimpleStringProperty produksi_good;
    private SimpleStringProperty produksi_ng;
    private SimpleStringProperty total_produksi;
    private SimpleStringProperty kode_produksi;

    /*
    public listTable(){
        this(null,null,null,null,null,null,null,null,null);
    }*/
    public listShow(String code_part, String nama_part, String material_name, String berat_part, String date, String produksi_good, String produksi_ng, String total_produksi, String kode_produksi){
        this.code_part = new SimpleStringProperty(code_part);
        this.nama_part = new SimpleStringProperty(nama_part);
        this.material_name = new SimpleStringProperty(material_name);
        this.berat_part = new SimpleStringProperty(berat_part);
        this.date = new SimpleStringProperty(date);
        this.produksi_good = new SimpleStringProperty(produksi_good);
        this.produksi_ng = new SimpleStringProperty(produksi_ng);
        this.total_produksi = new SimpleStringProperty(total_produksi);
        this.kode_produksi = new SimpleStringProperty(kode_produksi);

    }
    public String getCP(){
        return code_part.get();
    }
    public String getNP(){
        return nama_part.get();
    }
    public String getMN(){
        return material_name.get();
    }
    public String getBP(){
        return berat_part.get();
    }
    public String getDate(){
        return date.get();
    }
    public String getPG(){
        return produksi_good.get();
    }
    public String getPN(){
        return produksi_ng.get();
    }
    public String getTP(){
        return total_produksi.get();
    }
    public String getKP(){
        return kode_produksi.get();
    }

    public void setCP(String code_part){
        this.code_part.set(code_part);
    }
    public void setNP(String nama_part){
        this.nama_part.set(nama_part);
    }
    public void setMN(String material_name){
        this.material_name.set(material_name);
    }
    public void setBP(String berat_part){
        this.berat_part.set(berat_part);
    }
    public void setDate(String date){
        this.date.set(date);
    }
    public void setPG(String produksi_good){
        this.produksi_good.set(produksi_good);
    }
    public void setPN(String produksi_ng){
        this.produksi_ng.set(produksi_ng);
    }
    public void setTP(String total_produksi){
        this.total_produksi.set(total_produksi);
    }
    public void setKP(String kode_produksi){
        this.kode_produksi.set(kode_produksi);
    }
    public StringProperty cpProperty(){
        return code_part;
    }
    public StringProperty npProperty(){
        return nama_part;
    }
    public StringProperty mnProperty(){
        return material_name;
    }
    public StringProperty bpProperty(){
        return berat_part;
    }
    public StringProperty dateProperty(){
        return date;
    }
    public StringProperty pgProperty(){
        return produksi_good;
    }
    public StringProperty pnProperty(){
        return produksi_ng;
    }
    public StringProperty tpProperty(){
        return total_produksi;
    }
    public StringProperty kpProperty(){
        return kode_produksi;
    }


}
