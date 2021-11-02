package com.example.bck.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodGanDay {
   @SerializedName("id")
    @Expose
    String id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("avatar")
    @Expose
    String avatar;
    @SerializedName("diachi")
    @Expose
    String diachi;
    @SerializedName("rating")
    @Expose
    String rating;

    public FoodGanDay(String id, String name, String avatar, String diachi, String rating) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.diachi = diachi;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

