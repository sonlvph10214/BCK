package com.example.bck.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.example.bck.R;
import com.example.bck.model.Photo;

import java.util.List;

public class PhotoViewPagerAdapter extends PagerAdapter {

    private List<Photo> photoList;

    public PhotoViewPagerAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView imgPhoto = view.findViewById(R.id.imgPhoto);

        Photo photo = photoList.get(position);
        imgPhoto.setImageResource(photo.getPhoto());

        //add view
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if (photoList != null){
            return photoList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
