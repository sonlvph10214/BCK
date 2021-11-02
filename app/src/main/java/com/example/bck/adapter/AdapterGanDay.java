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
import com.example.bck.model.FoodGanDay;


import java.util.List;

public class AdapterGanDay extends RecyclerView.Adapter<AdapterGanDay.ViewHolder>{

    List<FoodGanDay> foodGanDayList;
    Context context;

    public AdapterGanDay(List<FoodGanDay> foodGanDayList,Context context) {
        this.foodGanDayList = foodGanDayList;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recently,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodGanDay foodGanDay = foodGanDayList.get(position);
        if (foodGanDay == null) {
            return;
        }
        Glide.with(context).load(foodGanDay.getAvatar()).into(holder.imgGanday);
        holder.tenGanDay.setText(foodGanDay.getName());
        holder.tvDiachi.setText(foodGanDay.getDiachi());
        holder.tvRating.setText(foodGanDay.getRating());

    }

    @Override
    public int getItemCount() {
        if (foodGanDayList != null) {
            return foodGanDayList.size();
        }
        return 0;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenGanDay,tvRating,tvDiachi;
        ImageView imgGanday,imgStar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenGanDay = itemView.findViewById(R.id.tvTenGanDay);
            tvDiachi = itemView.findViewById(R.id.tvDiaChiGanDay);
            tvRating = itemView.findViewById(R.id.tvStarGanDay);
            imgGanday = itemView.findViewById(R.id.imgRcvGanDay);
            imgStar = itemView.findViewById(R.id.imgStar);


        }
    }


}
