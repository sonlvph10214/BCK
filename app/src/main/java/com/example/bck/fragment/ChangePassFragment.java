package com.example.bck.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.bck.R;
import com.example.bck.dao.NguoiDungDao;
import com.example.bck.model.NguoiDung;
import com.example.bck.sqlite.MySQLite;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;


public class ChangePassFragment extends Fragment {
    View view;
    TextInputEditText edPassCu,edPassNew,edRePassNew;
    Button btnSave,btnHuy;
    NguoiDungDao nguoiDungDao;
    List<NguoiDung> nguoiDungList;

    public ChangePassFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_change_pass, container, false);

        edPassCu = view.findViewById(R.id.ed_PassCu);
        edPassNew = view.findViewById(R.id.ed_PassMoi);
        edRePassNew = view.findViewById(R.id.ed_RePassNew);
        btnSave = view.findViewById(R.id.btn_LuuPass);
        btnHuy = view.findViewById(R.id.btn_cancleChange);

        nguoiDungDao = new NguoiDungDao(getActivity());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassCu.setText("");
                edPassNew.setText("");
                edRePassNew.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // đọc user, pass trong sharefre
                SharedPreferences preferences = getActivity().getSharedPreferences("NguoiDung_FILE", Context.MODE_PRIVATE);
                String user =preferences.getString("User","");
                if (validate()>0 ){
                    MySQLite databaseBookManager = new MySQLite(getActivity());
                    NguoiDungDao nguoiDungDAO = new NguoiDungDao(getActivity());
                        NguoiDung nguoiDung = nguoiDungDao.getID(user);
                        nguoiDung.pass = edPassNew.getText().toString();


                    if (nguoiDungDao.updatePass(nguoiDung)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassCu.setText("");
                        edPassNew.setText("");
                        edRePassNew.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
    public int validate() {
        int check = 1;
        if (edPassCu.getText().length() <6 || edPassNew.getText().length() <6 || edRePassNew.getText().length()  <6) {
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin và nhiều hơn 6 kí tự", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences preferences = getActivity().getSharedPreferences("NguoiDung_FILE", Context.MODE_PRIVATE);
            String passCu = preferences.getString("Password", "");
            String passNew = edPassNew.getText().toString();
            String RepassNew = edRePassNew.getText().toString();
            if (!passCu.equals(edPassCu.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!passNew.equals(RepassNew)) {
                Toast.makeText(getContext(), "Mật khẩu mới không trùng nhau", Toast.LENGTH_SHORT).show();
                check = -1;
            }

        }
        return check;

}
}