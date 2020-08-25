package com.example.testapp1.interfaces;


import com.example.testapp1.models.BestSellerModelClass;
import com.example.testapp1.models.CarouselModelClass;
import com.example.testapp1.models.SimilarModelClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("stellardiver/ebookdata/carousel/")
    Call<ArrayList<CarouselModelClass>> getCarouselItem();

    @GET("stellardiver/ebookdata/similar/")
    Call<ArrayList<SimilarModelClass>> getSimilarItem();

    @GET("stellardiver/ebookdata/best/")
    Call<ArrayList<BestSellerModelClass>> getBestItem();
}
