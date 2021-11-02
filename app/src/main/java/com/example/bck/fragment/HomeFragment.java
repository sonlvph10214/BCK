package com.example.bck.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.bck.R;
import com.example.bck.activity.HomeActivity;
import com.example.bck.adapter.AdapterAnvat;
import com.example.bck.adapter.AdapterBun;
import com.example.bck.adapter.AdapterCom;
import com.example.bck.adapter.AdapterDanhMuc;
import com.example.bck.adapter.AdapterFoodMoi;
import com.example.bck.adapter.AdapterGanDay;
import com.example.bck.adapter.AdapterHot;
import com.example.bck.adapter.AdapterTraSua;
import com.example.bck.adapter.PhotoViewPagerAdapter;
import com.example.bck.adapter.ShoppingCartAdapter;
import com.example.bck.api.APIClient;
import com.example.bck.model.AnVat;
import com.example.bck.model.Bun;
import com.example.bck.model.Com;
import com.example.bck.model.DanhMuc;
import com.example.bck.model.FoodDanhMuc;
import com.example.bck.model.FoodGanDay;
import com.example.bck.model.FoodMoi;
import com.example.bck.model.Hot;
import com.example.bck.model.Photo;
import com.example.bck.model.TraSua;
import com.example.bck.onClick.IDanhMucClick;
import com.example.bck.onClick.IFoodClickListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    List<Photo> photoList;
    Timer timer;
    RecyclerView rcvDM,rcvGanDay,rcvFoodMoi,rcvFoodDM,rcvShopping;
    List<DanhMuc> danhMucList;
    List<FoodGanDay> foodGanDayList;
    List<FoodMoi> foodMoiList,foodMoiList1;
    AdapterFoodMoi adapterFoodMoi;
    List<FoodDanhMuc> foodDanhMucList;
    List<TraSua> traSuaList;
    TextView tvGanday,tvXem,tvMoi,tvGiaThoai,tvGiaBottom;
    BottomSheetBehavior bottomSheetBehavior;
    LinearLayout layoutBottomSheet,linearTotal;
    double sum;

    public static HomeFragment newInstance(FoodMoi foodMoi) {

        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        args.putString("img", foodMoi.getImgMon());
        args.putString("nameFood", foodMoi.getTenmon());
        args.putDouble("gia", foodMoi.getGiamGia());
        fragment.setArguments(args);
        return fragment;
    }

    public static HomeFragment newInstance(List<FoodMoi> foodMoiList) {

        Bundle args = new Bundle();
        args.putSerializable("foodlist", (Serializable) foodMoiList);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.circleIndi);
        rcvDM = view.findViewById(R.id.rcvDanhmuc);
        rcvGanDay = view.findViewById(R.id.rcvGanDay);
        rcvFoodMoi = view.findViewById(R.id.rcvNew);
        rcvFoodDM = view.findViewById(R.id.rcvFoodDM);
        rcvShopping =view.findViewById(R.id.rcvBottom);
        tvGanday = view.findViewById(R.id.tvRecommend);
        tvXem = view.findViewById(R.id.tvSeeall);
        tvMoi = view.findViewById(R.id.tvNew);
        tvGiaThoai = view.findViewById(R.id.tvGiaHopThoai);
        tvGiaBottom = view.findViewById(R.id.tvGiaBottom);
        layoutBottomSheet = view.findViewById(R.id.bottomSheet);
        linearTotal = view.findViewById(R.id.linearTotal);

        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);


        linearTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


        foodGanDayList = new ArrayList<>();
        foodMoiList= new ArrayList<>();
        foodMoiList1= new ArrayList<>();
        danhMucList= new ArrayList<>();




        hienThiSlide();
        autoSlideImg();
        hienThiDM();
        onGetGanDay();
        hienthiFoodMoi();
        //hienthiFoodMoi1();
        danhmucItem();
        return view;

    }
    private void danhmucItem(){

    }

    private void hienThiDM() {
        Call<List<DanhMuc>> call = APIClient.create().onGetDanhMuc();
        call.enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                danhMucList=response.body();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
                AdapterDanhMuc adapterDanhMuc = new AdapterDanhMuc(danhMucList,getActivity());
                adapterDanhMuc.setClick(new IDanhMucClick() {
                    @Override
                    public void onDanhMucMyClick(DanhMuc danhMuc, int position) {
                        rcvGanDay.setVisibility(View.INVISIBLE);
                        rcvFoodMoi.setVisibility(View.INVISIBLE);
                        rcvFoodDM.setVisibility(View.VISIBLE);
                        tvGanday.setVisibility(View.INVISIBLE);
                        tvMoi.setVisibility(View.INVISIBLE);
                        tvXem.setVisibility(View.INVISIBLE);

                        foodDanhMucList = new ArrayList<>();
                        traSuaList = new ArrayList<>();
                        Call<FoodDanhMuc> call = APIClient.create().onFoodDM();
                        call.enqueue(new Callback<FoodDanhMuc>() {
                            @Override
                            public void onResponse(Call<FoodDanhMuc> call, Response<FoodDanhMuc> response) {
                                switch (danhMuc.tenDM){
                                    case "Hot":
                                        getHot(response.body().getHotList());
                                        break;
                                    case "Trà Sữa":
                                        getTraSua(response.body().getTraSuaList());
                                        break;
                                    case "Bún":
                                        getBun(response.body().getBunList());
                                        break;
                                    case "Cơm":
                                        getCom(response.body().getComList());
                                        break;
                                    case "Ăn Vặt":
                                        getAnVat(response.body().getAnVatList());
                                        break;
                                }
                            }

                            @Override
                            public void onFailure(Call<FoodDanhMuc> call, Throwable t) {

                            }
                        });
                    }
                });
                rcvDM.setLayoutManager(linearLayoutManager);
                rcvDM.setAdapter(adapterDanhMuc);

            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getBun(List<Bun> bunList){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        AdapterBun adapterBun = new AdapterBun(bunList,getActivity());
        rcvFoodDM.setLayoutManager(linearLayoutManager);
        rcvFoodDM.setAdapter(adapterBun);
    }
    private void getCom(List<Com> comList){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        AdapterCom adapterCom = new AdapterCom(comList,getActivity());
        rcvFoodDM.setLayoutManager(linearLayoutManager);
        rcvFoodDM.setAdapter(adapterCom);
    }
    private void getAnVat(List<AnVat> anVatList){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        AdapterAnvat adapterAnvat = new AdapterAnvat(anVatList,getActivity());
        rcvFoodDM.setLayoutManager(linearLayoutManager);
        rcvFoodDM.setAdapter(adapterAnvat);
    }
    private void getTraSua(List<TraSua> traSuaList){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        AdapterTraSua adapterTraSua = new AdapterTraSua(traSuaList,getActivity());
        rcvFoodDM.setLayoutManager(linearLayoutManager);
        rcvFoodDM.setAdapter(adapterTraSua);
    }
    private void getHot(List<Hot> hotList){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        AdapterHot adapterHot = new AdapterHot(hotList,getActivity());
        rcvFoodDM.setLayoutManager(linearLayoutManager);
        rcvFoodDM.setAdapter(adapterHot);
    }

    private  void hienthiFoodMoi(){

        Call<List<FoodMoi>> call = APIClient.create().onGetMoi();
        call.enqueue(new Callback<List<FoodMoi>>() {
            @Override
            public void onResponse(Call<List<FoodMoi>> call, Response<List<FoodMoi>> response) {
                foodMoiList=response.body();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                 adapterFoodMoi = new AdapterFoodMoi(foodMoiList,getActivity());
                rcvFoodMoi.setLayoutManager(linearLayoutManager);
                rcvFoodMoi.setAdapter(adapterFoodMoi);

                adapterFoodMoi.setClick(new IFoodClickListener() {
                    @Override
                    public void onFoodClick(FoodMoi food) {
                        DetailFragment detailFragment = DetailFragment.newInstance(food);
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.fragContainer, detailFragment)
                                .addToBackStack("1")
                                .commit();
                    }

                    @Override

                    public void onMyClick(FoodMoi foodMoi ,int position) {
                        foodMoiList1.add(foodMoi);
                         linearTotal.setVisibility(View.VISIBLE);
                        sum += foodMoi.getGiamGia();
                        tvGiaThoai.setText(new DecimalFormat("##.000").format(sum)+"đ");

                        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(foodMoiList1,getActivity());
                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                        rcvShopping.setLayoutManager(linearLayoutManager2);
                        rcvShopping.setAdapter(shoppingCartAdapter);

                        tvGiaBottom.setText(new DecimalFormat("##.000").format(sum)+"đ");
                    }
                });
            }

            @Override
            public void onFailure(Call<List<FoodMoi>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
//    private  void hienthiFoodMoi1(){
//
//        Call<List<FoodDanhMuc>> call = APIClient.create().onFoodDM();
//        call.enqueue(new Callback<List<FoodDanhMuc>>() {
//            @Override
//            public void onResponse(Call<List<FoodDanhMuc>> call, Response<List<FoodDanhMuc>> response) {
//                foodDanhMucList=response.body();
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
//                AdapterTraSua adapterTraSua = new AdapterTraSua(traSuaList,getActivity());
//                rcvFoodMoi.setLayoutManager(linearLayoutManager);
//                rcvFoodMoi.setAdapter(adapterFoodMoi);
//
////                adapterFoodMoi.setClick(new IFoodClickListener() {
////                    @Override
////                    public void onFoodClick(FoodMoi food) {
////                        DetailFragment detailFragment = DetailFragment.newInstance(food);
////                        getActivity().getSupportFragmentManager()
////                                .beginTransaction()
////                                .add(R.id.fragContainer, detailFragment)
////                                .addToBackStack(null)
////                                .commit();
////                    }
////
////                    @Override
////
////                    public void onMyClick(FoodMoi foodMoi ,int position) {
////                        foodMoi =  foodMoiList.get(position);
////                        foodMoiList1.add(foodMoi);
//////                        GioHanglFragment gioHanglFragment = GioHanglFragment.newInstance(foodMoiList1);
//////                        getActivity().getSupportFragmentManager()
//////                                .beginTransaction()
//////                                .add(R.id.fragContainer, gioHanglFragment)
//////                                .addToBackStack(null)
//////                                .commit();
////                    }
////                });
//            }
//
//            @Override
//            public void onFailure(Call<List<FoodDanhMuc>> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    private  void onGetGanDay(){

        Call<List<FoodGanDay>> call = APIClient.create().onGet();
        call.enqueue(new Callback<List<FoodGanDay>>() {
            @Override
            public void onResponse(Call<List<FoodGanDay>> call, Response<List<FoodGanDay>> response) {
                foodGanDayList=response.body();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
                AdapterGanDay adapterGanDay = new AdapterGanDay(foodGanDayList,getActivity());
                rcvGanDay.setLayoutManager(linearLayoutManager);
                rcvGanDay.setAdapter(adapterGanDay);
            }

            @Override
            public void onFailure(Call<List<FoodGanDay>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void hienThiSlide(){
        photoList =getListPhoto();
        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(photoList);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
    }

    private  List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.photo1));
        list.add(new Photo(R.drawable.photo2));
        list.add(new Photo(R.drawable.photo3));
        return  list;
    }
    private void autoSlideImg(){
        if (photoList == null || photoList.isEmpty() || viewPager == null){
            return;
        }
        if(timer == null){
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = photoList.size() - 1;
                        if (currentItem<totalItem){
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        },500,3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
            timer=null;
        }
    }
}
