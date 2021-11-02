package com.example.bck.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.bck.R;
import com.example.bck.model.FoodMoi;

import java.text.DecimalFormat;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    List<FoodMoi> foodList;
    Context context;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();


    public ShoppingCartAdapter(List<FoodMoi> foodList, Context context) {
        this.context= context;
        this.foodList = foodList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_gio_hang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodMoi food = foodList.get(position);
        if (food == null) {
            return;
        }
        viewBinderHelper.bind(holder.swipeRevealLayout, food.getTenmon());
        Glide.with(context).load(food.getImgMon()).into(holder.imgRcvBottom);
        holder.tvName.setText(food.getTenmon());
        holder.tvPrice.setText(new DecimalFormat("##.000").format(food.getGiamGia())+"đ");

        holder.linearDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (foodList != null) {
            return foodList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvPrice,tvSoluong;
        ImageView imgRcvBottom,imgTru,imgCong;
        SwipeRevealLayout swipeRevealLayout;
        LinearLayout linearDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRcvBottom = itemView.findViewById(R.id.imgRcvBottom);
            imgTru = itemView.findViewById(R.id.imgTru);
            imgCong = itemView.findViewById(R.id.imgCong);
            tvName = itemView.findViewById(R.id.tvNameRcvBotom);
            tvPrice = itemView.findViewById(R.id.tvGiaRcvBottom);
            tvSoluong = itemView.findViewById(R.id.tvSoLuong);
            swipeRevealLayout = itemView.findViewById(R.id.swipeLayout);
            linearDel = itemView.findViewById(R.id.linearDel);


        }
    }
}
