package com.example.testapp1.transformators;

import android.view.View;

import com.gtomato.android.ui.manager.CarouselLayoutManager;
import com.gtomato.android.ui.widget.CarouselView;

public class SimilarTransformer implements CarouselView.ViewTransformer {

    @Override
    public void onAttach(CarouselLayoutManager layoutManager) {
        layoutManager.setDrawOrder(CarouselView.DrawOrder.CenterFront);
    }

    @Override
    public void transform(View view, float position) {
        int parentWidth = ((View) view.getParent()).getMeasuredWidth();

        int mPieRad = 1;
        double rotateRad = position * mPieRad;

        //коэф yвеличения радиуса прокрутки
        double mHorizontalViewPort = 0.6;
        double a = parentWidth * mHorizontalViewPort / 2.0;

        double x = a * rotateRad - parentWidth / 5;
        view.setTranslationX((float) x);
        view.setPadding(15, 10, 15, 10);

    }
}
