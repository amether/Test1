package com.example.testapp1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.IntentFilter;
import android.os.Bundle;

import com.example.testapp1.R;
import com.example.testapp1.fragments.MainFragment;
import com.example.testapp1.fragments.SecondFragment;
import com.example.testapp1.handlers.MyBroadcast;
import com.example.testapp1.interfaces.BroadcastInterface;

public class MainActivity extends AppCompatActivity implements BroadcastInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MyBroadcast myBroadcast = new MyBroadcast();
        IntentFilter intentFilter = new IntentFilter("my broadcast");
        registerReceiver(myBroadcast, intentFilter);
        fragmentAdd(new MainFragment());

    }

    private void fragmentAdd(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame, fragment);
        ft.commit();
    }

    private void FragmentReplace(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goSecondFragment(String title, String author, String price, String score, String amount, String image) {
        FragmentReplace(new SecondFragment(title, author, price, score, amount, image));
    }

    @Override
    public void goFirstFragment() {
        FragmentReplace(new MainFragment());
    }
}