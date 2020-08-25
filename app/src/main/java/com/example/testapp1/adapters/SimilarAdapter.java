package com.example.testapp1.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SimilarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> similarList;
    private Context context;
    ImageView imageItem;

    public SimilarAdapter(Context context, ArrayList<String> similarList) {
        this.context = context;
        this.similarList = similarList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.similar_item, null);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        imageItem = viewHolder.image;
        Picasso.with(context)
                .load(similarList.get(position))
                .into(viewHolder.image);
    }

    public void refreshData(ArrayList<String> list) {
        similarList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return similarList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.similar_image);
        }

    }

}
