package com.example.testapp1.handlers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.testapp1.interfaces.BroadcastInterface;

public class MyBroadcast extends BroadcastReceiver {
    BroadcastInterface broadcastInterface;

    @Override
    public void onReceive(Context context, Intent intent) {
        broadcastInterface = (BroadcastInterface) context;
        String typeFragment = intent.getStringExtra("fragment type");
        switch (typeFragment) {
            case "second": {
                String title = intent.getStringExtra("title");
                String author = intent.getStringExtra("author");
                String price = intent.getStringExtra("price");
                String score = intent.getStringExtra("score");
                String amount = intent.getStringExtra("amount");
                String image = intent.getStringExtra("image");
                broadcastInterface.goSecondFragment(title, author, price, score, amount, image);
                break;
            }
            case "first": {
                broadcastInterface.goFirstFragment();
                break;
            }
            default:
                break;
        }
    }
}
