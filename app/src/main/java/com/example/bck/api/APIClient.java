package com.example.bck.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL ="https://demo2856359.mockable.io/";

    public static Retrofit retrofit = null;

    public static APIClientlpm  create(){
        return getClient(BASE_URL).create(APIClientlpm.class);
    }

    public static Retrofit getClient(String url){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
