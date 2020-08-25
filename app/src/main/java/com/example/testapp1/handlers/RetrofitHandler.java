package com.example.testapp1.handlers;

import com.example.testapp1.interfaces.RetrofitApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler {

    private static RetrofitHandler retrofitHandler;
    private static final String BASE_URL = "https://my-json-server.typicode.com/";
    private Retrofit retrofit;

    public RetrofitHandler() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitHandler getRetrofitHandler() {
        if (retrofitHandler == null) {
            retrofitHandler = new RetrofitHandler();
        }
        return retrofitHandler;
    }

    public RetrofitApi getApi() {
        return retrofit.create(RetrofitApi.class);
    }
}
