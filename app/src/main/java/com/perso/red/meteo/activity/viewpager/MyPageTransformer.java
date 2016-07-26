package com.perso.red.meteo.activity.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by pierr on 26/07/2016.
 */

public class MyPageTransformer implements ViewPager.PageTransformer {

    public void transformPage(View page, float position) {
        page.setRotationY(position * -30f);
    }
}
