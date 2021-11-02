package com.example.bck.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodDanhMuc {
    @SerializedName("trasua")
    @Expose
    List<TraSua> traSuaList =null;
    @SerializedName("hot")
    @Expose
    List<Hot> hotList =null;
    @SerializedName("bun")
    @Expose
    List<Bun> bunList =null;
    @SerializedName("anvat")
    @Expose
    List<AnVat> anVatList =null;
    @SerializedName("com")
    @Expose
    List<Com> comList =null;

    public FoodDanhMuc(List<TraSua> traSuaList, List<Hot> hotList, List<Com> comList, List<Bun> bunList, List<AnVat> anVatList) {
        this.traSuaList = traSuaList;
        this.hotList = hotList;
        this.comList = comList;
        this.bunList = bunList;
        this.anVatList = anVatList;
    }

    public List<TraSua> getTraSuaList() {
        return traSuaList;
    }

    public void setTraSuaList(List<TraSua> traSuaList) {
        this.traSuaList = traSuaList;
    }

    public List<Hot> getHotList() {
        return hotList;
    }

    public void setHotList(List<Hot> hotList) {
        this.hotList = hotList;
    }

    public List<Com> getComList() {
        return comList;
    }

    public void setComList(List<Com> comList) {
        this.comList = comList;
    }

    public List<Bun> getBunList() {
        return bunList;
    }

    public void setBunList(List<Bun> bunList) {
        this.bunList = bunList;
    }

    public List<AnVat> getAnVatList() {
        return anVatList;
    }

    public void setAnVatList(List<AnVat> anVatList) {
        this.anVatList = anVatList;
    }
}
