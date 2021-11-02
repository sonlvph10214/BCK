package com.example.bck.activity;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.bck.R;
import com.example.bck.fragment.HomeFragment;
import com.example.bck.fragment.UserFragment;
import com.example.bck.model.FoodMoi;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TextView tvTrangChu, tvDonHang, tvThongBao, tvAccout, tvTenUser,tvGiaThoai;
    ImageView imgGio;
    String name;
    String user;
    String fullname;
    String photo;
    List<FoodMoi> foodMoiList;
    Uri myUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homee);

        tvTrangChu = findViewById(R.id.tvTrangChu);
        tvDonHang = findViewById(R.id.tvDonHang);
        tvThongBao = findViewById(R.id.tvThongBao);
        tvAccout = findViewById(R.id.tvAccout);
        tvTenUser = findViewById(R.id.tvTenUser);
        imgGio = findViewById(R.id.imgShop);
        tvGiaThoai = findViewById(R.id.tvGiaHopThoai);


        tvDonHang.setTextColor(Color.WHITE);
        tvThongBao.setTextColor(Color.WHITE);
        tvAccout.setTextColor(Color.WHITE);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, new HomeFragment()).commit();


        tvTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, new HomeFragment()).commit();
                tvTrangChu.setTextColor(Color.BLACK);
                tvDonHang.setTextColor(Color.WHITE);
                tvThongBao.setTextColor(Color.WHITE);
                tvAccout.setTextColor(Color.WHITE);
            }
        });
//        imgGio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onFragment(GioHanglFragment.newInstance(foodMoiList));
//                tvTrangChu.setTextColor(Color.WHITE);
//                tvDonHang.setTextColor(Color.WHITE);
//                tvThongBao.setTextColor(Color.WHITE);
//                tvAccout.setTextColor(Color.WHITE);
//            }
//        });
//        tvDonHang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, new MenuFragment()).commit();
//                tvTrangChu.setTextColor(Color.WHITE);
//                tvDonHang.setTextColor(Color.BLACK);
//                tvThongBao.setTextColor(Color.WHITE);
//                tvAccout.setTextColor(Color.WHITE);
//            }
//        });
//        tvThongBao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvTrangChu.setTextColor(Color.WHITE);
//                tvDonHang.setTextColor(Color.WHITE);
//                tvThongBao.setTextColor(Color.BLACK);
//                tvAccout.setTextColor(Color.WHITE);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, new FavouriteFragment()).commit();
//            }
//        });

        //name là cái nào

        tvAccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTrangChu.setTextColor(Color.WHITE);
                tvDonHang.setTextColor(Color.WHITE);
                tvThongBao.setTextColor(Color.WHITE);
                tvAccout.setTextColor(Color.BLACK);

//                if (getIntent() != null) {
//                    Bundle bundle = getIntent().getExtras();
//                     user = bundle.getString("user");
////            fullname = bundle.getString("fullname");
////            email = bundle.getString("email");
//            }
                foodMoiList = new ArrayList<>();
                if (getIntent() != null) {
                    Bundle bundle = getIntent().getExtras();
                    //user = bundle.getString("user");
                    fullname = bundle.getString("personName");
                    myUri = Uri.parse(bundle.getString("personPhoto"));
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragContainer,
                                UserFragment.newInstance(user,fullname,myUri))
                        .commit();
            }
        });

    }

    private void onFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragContainer, fragment)
                .commit();
    }

}