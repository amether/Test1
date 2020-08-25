package com.example.testapp1.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp1.models.BestSellerModelClass;
import com.example.testapp1.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class BestSellerRecAdapter extends RecyclerView.Adapter<BestSellerRecAdapter.ViewHolder> {
    private ArrayList<BestSellerModelClass> bestSellerList;
    private Context context;

    public BestSellerRecAdapter(Context context, ArrayList<BestSellerModelClass> bestSellerList) {
        this.context = context;
        this.bestSellerList = bestSellerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.best_seller_item, parent, false);
        return new BestSellerRecAdapter.ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BestSellerModelClass bestSellerItem = bestSellerList.get(position);
        Picasso.with(context).load(bestSellerItem.getImage()).into(holder.bookImage);
        holder.bookName.setText(bestSellerItem.getTitle());
        holder.bookAuthor.setText(bestSellerItem.getAuthor());
        holder.bookPrice.setText(String.valueOf(bestSellerItem.getPrice()) + " €");
        holder.bookAmount.setText(" (" + bestSellerItem.getRateAmount() + ")");
        holder.bookScore.setText(String.valueOf(bestSellerItem.getRateScore()));
    }

    public void refreshData(ArrayList<BestSellerModelClass> list) {
        bestSellerList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        int returnedCount = 0;
        if (bestSellerList != null) {
            returnedCount = bestSellerList.size();
        }
        return returnedCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;
        TextView bookAuthor;
        TextView bookPrice;
        TextView bookScore;
        TextView bookAmount;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.book_image);
            bookName = itemView.findViewById(R.id.book_name);
            bookAuthor = itemView.findViewById(R.id.book_author);
            bookPrice = itemView.findViewById(R.id.book_price);
            bookScore = itemView.findViewById(R.id.book_score);
            bookAmount = itemView.findViewById(R.id.book_amount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent("my broadcast");
                    intent.putExtra("fragment type", "second");
                    intent.putExtra("title", bestSellerList.get(getAdapterPosition()).getTitle());
                    intent.putExtra("author", bestSellerList.get(getAdapterPosition()).getAuthor());
                    intent.putExtra("price", bestSellerList.get(getAdapterPosition()).getPrice().toString());
                    intent.putExtra("score", String.valueOf(bestSellerList.get(getAdapterPosition()).getRateScore()));
                    intent.putExtra("amount", String.valueOf(bestSellerList.get(getAdapterPosition()).getRateAmount()));
                    intent.putExtra("image", bestSellerList.get(getAdapterPosition()).getImage());
                    context.sendBroadcast(intent);
                }
            });
        }
    }
}
