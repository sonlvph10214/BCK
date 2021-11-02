package com.example.bck.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DanhMuc {
    @SerializedName("anhDM")
    @Expose
    public String anhDM;
    @SerializedName("tenDM")
    @Expose
    public String tenDM;

    public List<FoodMoi> list;

    public DanhMuc(String anhDM, String tenDM) {
        this.anhDM = anhDM;
        this.tenDM = tenDM;
    }

    public String getAnhDM() {
        return anhDM;
    }

    public void setAnhDM(String anhDM) {
        this.anhDM = anhDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    
}
