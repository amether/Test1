package com.example.testapp1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarouselAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> carouselList;
    private Context context;
    ImageView imageItem;

    public CarouselAdapter(Context context, ArrayList<String> carouselList) {
        this.context = context;
        this.carouselList = carouselList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.carousel_item, null);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        imageItem = viewHolder.image;
        Picasso.with(context)
                .load(carouselList.get(position))
                .into(viewHolder.image);
    }

    public void refreshData(ArrayList<String> list) {
        carouselList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return carouselList.size();
    }

    public ImageView getImageItem() {
        return imageItem;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.carousel_image);
        }

    }

}
