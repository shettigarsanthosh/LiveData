package com.learnkotlin.livedata.ui.main;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient())
            .build();

    public static RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);


}
