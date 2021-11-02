package com.example.bck.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bck.R;
import com.example.bck.model.FoodMoi;
import com.example.bck.onClick.IFoodClickListener;


import java.text.DecimalFormat;
import java.util.List;

public class AdapterFoodMoi extends RecyclerView.Adapter<AdapterFoodMoi.ViewHolder>{

    List<FoodMoi> foodMoiList;
    Context context;
    IFoodClickListener iFoodClickListener;

    public AdapterFoodMoi(List<FoodMoi> foodMoiList, Context context) {
        this.foodMoiList = foodMoiList;
        this.context=context;

    }
    public void setClick(IFoodClickListener iFoodClickListener) {
        this.iFoodClickListener = iFoodClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_moi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodMoi foodMoi = foodMoiList.get(position);
        if (foodMoi == null) {
            return;
        }
        Glide.with(context).load(foodMoi.getImgMon()).into(holder.imgFoodMoi);
        holder.tvTenMoi.setText(foodMoi.getTenmon());
        holder.tvGia.setText(new DecimalFormat("##.000").format(foodMoi.getGiaTien())+"đ");
        holder.tvGiaGiam.setText(new DecimalFormat("##.000").format(foodMoi.getGiamGia())+"đ");
        holder.imgFoodMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFoodClickListener.onFoodClick(foodMoiList.get(position));
            }
        });
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFoodClickListener.onMyClick(foodMoi,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (foodMoiList != null) {
            return foodMoiList.size();
        }
        return 0;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenMoi,tvGia,tvGiaGiam;
        ImageView imgFoodMoi,imgAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenMoi = itemView.findViewById(R.id.tvFoodMoi);
            tvGia = itemView.findViewById(R.id.tvGia);
            tvGiaGiam = itemView.findViewById(R.id.tvGiaGiam);
            imgFoodMoi = itemView.findViewById(R.id.imgFoodMoi);
            imgAdd = itemView.findViewById(R.id.imgAddFood);

        }
    }


}
