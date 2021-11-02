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
import com.example.bck.model.DanhMuc;
import com.example.bck.model.FoodMoi;
import com.example.bck.onClick.IDanhMucClick;
import com.example.bck.onClick.IFoodClickListener;


import java.util.List;

public class AdapterDanhMuc extends RecyclerView.Adapter<AdapterDanhMuc.ViewHolder>{

    List<DanhMuc> danhMucList;
    Context context;

    List<FoodMoi> foodMoiList;
    IDanhMucClick iDanhMucClick;

    public AdapterDanhMuc(List<DanhMuc> danhMucList, Context context) {
        this.danhMucList = danhMucList;
        this.context=context;

    }
    public void setClick(IDanhMucClick iDanhMucClick) {
        this.iDanhMucClick = iDanhMucClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danhmuc,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DanhMuc danhMuc = danhMucList.get(position);
        if (danhMuc == null) {
            return;
        }
        Glide.with(context).load(danhMuc.getAnhDM()).into(holder.imgDM);
        holder.tenDM.setText(danhMuc.getTenDM());

        holder.imgDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDanhMucClick.onDanhMucMyClick(danhMucList.get(position),position);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (danhMucList != null) {
            return danhMucList.size();
        }
        return 0;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView tenDM;
        ImageView imgDM;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenDM = itemView.findViewById(R.id.tenDM);
            imgDM = itemView.findViewById(R.id.imgRcvDM);

        }
    }


}
