package com.example.bck.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.bck.R;
import com.example.bck.databinding.FragmentDetailBinding;
import com.example.bck.model.FoodMoi;

import java.text.DecimalFormat;

public class DetailFragment extends Fragment {
    FragmentDetailBinding binding;
    FoodMoi foodMoi;

    public static DetailFragment newInstance(FoodMoi foodMoi) {

        Bundle args = new Bundle();
        args.putString("img", foodMoi.getImgMon());
        args.putString("nameFood", foodMoi.getTenmon());
        args.putDouble("gia", foodMoi.getGiamGia());
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false);
        if (getArguments() != null){
            String img  = getArguments().getString("img");
            String name = getArguments().getString("nameFood");
            double gia  = getArguments().getDouble("gia");
            Glide.with(getActivity()).load(img).into(binding.imgFoodDetail);
            binding.tvNameDetail.setText(name);
            binding.tvDetail.setText(name);
            binding.tvGiaDetail.setText(new DecimalFormat("##.000").format(gia)+"đ");
        }
        binding.imgBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        binding.imgAddDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return binding.getRoot();
    }
}
