package com.example.testapp1.transformators;

import android.view.View;

import com.gtomato.android.ui.manager.CarouselLayoutManager;
import com.gtomato.android.ui.widget.CarouselView;

public class CarouselTransformer implements CarouselView.ViewTransformer {

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
        double mHorizontalViewPort = 1.1;
        double a = parentWidth * mHorizontalViewPort / 2.0;

        double x = a * rotateRad - parentWidth / 5;
        if (position == 0) {
            view.setScaleX(1.2f);
            view.setScaleY(1.2f);
        } else {
            view.setScaleX(1);
            view.setScaleY(1);
        }
        view.setTranslationX((float) x);
        view.setPadding(15, 10, 15, 10);

    }
}
