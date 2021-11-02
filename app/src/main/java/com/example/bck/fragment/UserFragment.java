package com.example.bck.fragment;

import android.content.Intent;
import android.net.Uri;
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
import com.example.bck.activity.LoginActivity;
import com.example.bck.databinding.FragmentUserBinding;


public class UserFragment extends Fragment {
    FragmentUserBinding binding;
    String name,user;
    Uri photo;

    public static UserFragment newInstance(String user,String name, Uri photo) {

        Bundle args = new Bundle();
        //args.putString("user",user);
        args.putString("personName",name);
        args.putString("personPhoto", String.valueOf(photo));
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user,container,false);

        binding.tvDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer, new ChangePassFragment()).commit();
            }
        });
        if (getArguments()!= null){
            user = getArguments().getString("user");
            name = getArguments().getString("personName");
            photo = Uri.parse(getArguments().getString("personPhoto"));
            binding.tvTenUser.setText(name);
            Glide.with(getActivity())
                    .load(photo)
                    .into(binding.imgAcc);
        }

        binding.btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return binding.getRoot();
    }

}
