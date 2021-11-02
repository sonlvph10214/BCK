package com.example.bck.onClick;


import com.example.bck.model.FoodMoi;

public interface IFoodClickListener {
    void onFoodClick(FoodMoi food);
    void onMyClick(FoodMoi food, int position);

//    void onTraSuaClick(TraSua traSua);
//    void onMyClick(TraSua traSua, int position);

//    void onDanhMucClick(DanhMuc danhMuc);
//    void onDanhMucMyClick(DanhMuc danhMuc, int position);
}
