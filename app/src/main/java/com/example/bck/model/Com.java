package com.example.bck.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Com {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("tenmon")
    @Expose
    String tenmon;
    @SerializedName("imgMon")
    @Expose
    String imgMon;
    @SerializedName("giaTien")
    @Expose
    double giaTien;
    @SerializedName("giamGia")
    @Expose
    double giamGia;

    public Com(String id, String tenmon, String imgMon, double giaTien, double giamGia) {
        this.id = id;
        this.tenmon = tenmon;
        this.imgMon = imgMon;
        this.giaTien = giaTien;
        this.giamGia = giamGia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getImgMon() {
        return imgMon;
    }

    public void setImgMon(String imgMon) {
        this.imgMon = imgMon;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }
}
