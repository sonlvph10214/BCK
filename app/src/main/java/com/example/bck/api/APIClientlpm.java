package com.example.bck.api;



import com.example.bck.model.DanhMuc;
import com.example.bck.model.FoodDanhMuc;
import com.example.bck.model.FoodGanDay;
import com.example.bck.model.FoodMoi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIClientlpm {

    @GET("baicuoikhoa")
    Call<List<FoodGanDay>> onGet();
    @GET("monanmoi")
    Call<List<FoodMoi>> onGetMoi();
    @GET("danhmuc")
    Call<List<DanhMuc>> onGetDanhMuc();
    @GET("foodDanhmuc")
    Call<FoodDanhMuc> onFoodDM();

}
