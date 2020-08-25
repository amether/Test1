package com.example.testapp1.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarouselModelClass {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("image")
    @Expose
    private String image;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
