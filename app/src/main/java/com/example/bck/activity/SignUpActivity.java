package com.example.bck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.example.bck.R;
import com.example.bck.dao.NguoiDungDao;
import com.example.bck.databinding.ActivityOtpBinding;
import com.example.bck.databinding.ActivitySignUpBinding;
import com.example.bck.model.NguoiDung;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    NguoiDungDao nguoiDungDao;
    List<NguoiDung> nguoiDungList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        nguoiDungDao = new NguoiDungDao(getApplicationContext());


        binding.tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.userName= binding.edUserSignUp.getText().toString();
                nguoiDung.fullName= binding.edFullNameSignUp.getText().toString();
                nguoiDung.email= binding.edEmailSignUp.getText().toString();
                nguoiDung.pass= binding.edPassSignUp.getText().toString();
                if(validate()>0){
                    if (nguoiDungDao.addNguoiDung(nguoiDung)>0){
                        Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
//                        Log.e("Lưu ok","ok");
                        binding.edUserSignUp.setText("");
                        binding.edFullNameSignUp.setText("");
                        binding.edEmailSignUp.setText("");
                        binding.edPassSignUp.setText("");
                        binding.edRePassSignUp.setText("");
                    }else {
                        Toast.makeText(getApplicationContext(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
//                        Log.e("Lưu sai","sai");
                    }
                }
            }
        });

    }
    public  int validate(){
        int check = 1;
        if (binding.edUserSignUp.getText().length()==0 || binding.edFullNameSignUp.getText().length()==0 || binding.edEmailSignUp.getText().length()==0 || binding.edEmailSignUp.getText().length()==0
                || binding.edRePassSignUp.getText().length()==0){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }else if ( binding.edPassSignUp.getText().length()<= 6|| binding.edRePassSignUp.getText().length()<=6) {
            Toast.makeText(getApplicationContext(), "Mật Khẩu lớn hơn 6 kí tự", Toast.LENGTH_SHORT).show();
            check = -1;

        }else {
            String pass = binding.edPassSignUp.getText().toString();
            String repass = binding.edRePassSignUp.getText().toString();
            if (!pass.equals(repass)){
                Toast.makeText(getApplicationContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                check =-1;
            }
        }
        return  check;
    }
}