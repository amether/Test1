package com.example.testapp1.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp1.transformators.CarouselTransformer;
import com.example.testapp1.R;
import com.example.testapp1.handlers.RetrofitHandler;
import com.example.testapp1.adapters.BestSellerRecAdapter;
import com.example.testapp1.adapters.CarouselAdapter;
import com.example.testapp1.models.BestSellerModelClass;
import com.example.testapp1.models.CarouselModelClass;
import com.gtomato.android.ui.widget.CarouselView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {


    CarouselView carouselView;
    private ArrayList<String> carouselList = new ArrayList<>();
    CarouselAdapter carouselRecyclerAdapter;

    private ArrayList<BestSellerModelClass> bestSellerModelClassArrayList = new ArrayList<>();
    BestSellerRecAdapter bestSellerRecAdapter;
    RecyclerView bestSellersView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        carouselRecyclerAdapter = new CarouselAdapter(getContext(), carouselList);
        bestSellerRecAdapter = new BestSellerRecAdapter(getContext(), bestSellerModelClassArrayList);
        getCarouselData();
        getBestSellerData();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView titleName = view.findViewById(R.id.title_name);
        titleName.setText("Bookly");
        TextView bestSellerView = view.findViewById(R.id.best_seller_text);
        bestSellerView.setText("Best Seller");
        carouselView = view.findViewById(R.id.carousel);
        carouselView.setTransformer(new CarouselTransformer());
        carouselView.setGravity(Gravity.CENTER);
        carouselView.setAdapter(carouselRecyclerAdapter);


        bestSellersView = view.findViewById(R.id.best_seller_view);
        bestSellersView.setLayoutManager(new LinearLayoutManager(getContext()));
        bestSellersView.setAdapter(bestSellerRecAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

    private void getCarouselData() {
        RetrofitHandler.getRetrofitHandler()
                .getApi()
                .getCarouselItem()
                .enqueue(new Callback<ArrayList<CarouselModelClass>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CarouselModelClass>> call, Response<ArrayList<CarouselModelClass>> response) {
                        if (response.body() != null) {
                            ArrayList<CarouselModelClass> retrofitModelClass = response.body();
                            for (int i = 0; i < retrofitModelClass.size(); i++) {
                                carouselList.add(retrofitModelClass.get(i).getImage());
                            }
                            carouselRecyclerAdapter.refreshData(carouselList);

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<CarouselModelClass>> call, Throwable t) {
                    }
                });
    }

    private void getBestSellerData() {
        RetrofitHandler.getRetrofitHandler()
                .getApi()
                .getBestItem()
                .enqueue(new Callback<ArrayList<BestSellerModelClass>>() {
                    @Override
                    public void onResponse(Call<ArrayList<BestSellerModelClass>> call, Response<ArrayList<BestSellerModelClass>> response) {
                        if (response.body() != null) {
                            ArrayList<BestSellerModelClass> retrofitModelClass = response.body();
                            bestSellerModelClassArrayList.addAll(retrofitModelClass);
                            bestSellerRecAdapter.refreshData(bestSellerModelClassArrayList);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<BestSellerModelClass>> call, Throwable t) {
                    }
                });
    }
}
