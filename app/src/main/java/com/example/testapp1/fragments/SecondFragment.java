package com.example.testapp1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testapp1.R;
import com.example.testapp1.handlers.RetrofitHandler;
import com.example.testapp1.transformators.SimilarTransformer;
import com.example.testapp1.adapters.SimilarAdapter;
import com.example.testapp1.models.SimilarModelClass;
import com.gtomato.android.ui.widget.CarouselView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondFragment extends Fragment {
    String title;
    String author;
    String price;
    String score;
    String amount;
    String image;
    SimilarAdapter similarAdapter;
    private ArrayList<String> similarList = new ArrayList<>();

    public SecondFragment(String title, String author, String price, String score, String amount, String image) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.score = score;
        this.amount = amount;
        this.image = image;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        similarAdapter = new SimilarAdapter(getContext(), similarList);
        getSimilarData();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button closeButton = view.findViewById(R.id.button_close);
        Button priceBut = view.findViewById(R.id.left_button);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView titleView = view.findViewById(R.id.title_textView);
        TextView authorView = view.findViewById(R.id.author_textView);
        TextView scoreView = view.findViewById(R.id.score_textView);
        TextView amountView = view.findViewById(R.id.amount_textView);
        CarouselView carouselSimilarView = view.findViewById(R.id.ycal_recycler);
        carouselSimilarView.setTransformer(new SimilarTransformer());
        carouselSimilarView.setGravity(Gravity.CENTER);
        carouselSimilarView.setAdapter(similarAdapter);

        Picasso.with(getContext()).load(image).into(imageView);
        titleView.setText(title);
        authorView.setText(author);
        scoreView.setText(score);
        amountView.setText(" (" + amount + ")");
        priceBut.setText(price + " €");

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("my broadcast");
                intent.putExtra("fragment type", "first");
                getContext().sendBroadcast(intent);
            }
        });
    }

    private void getSimilarData() {
        RetrofitHandler.getRetrofitHandler()
                .getApi()
                .getSimilarItem()
                .enqueue(new Callback<ArrayList<SimilarModelClass>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SimilarModelClass>> call, Response<ArrayList<SimilarModelClass>> response) {
                        if (response.body() != null) {
                            ArrayList<SimilarModelClass> retrofitModelClass = response.body();
                            for (int i = 0; i < retrofitModelClass.size(); i++) {
                                similarList.add(retrofitModelClass.get(i).getImage());
                            }
                            similarAdapter.refreshData(similarList);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SimilarModelClass>> call, Throwable t) {
                    }
                });
    }
}