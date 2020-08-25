package com.example.testapp1.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BestSellerModelClass {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("rate")
    @Expose
    private Rate rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRateAmount() {
        return rate.getAmount();
    }

    public double getRateScore() {
        return rate.getScore();
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

}

class Rate {

    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}
