package com.perso.red.meteo.activity.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.perso.red.meteo.views.CurrentWeather.CurrentWeatherView;
import com.perso.red.meteo.views.DailyWeatherView;
import com.perso.red.meteo.views.HourlyWeatherView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 25/07/2016.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);

        // Init Fragment List
        fragments = new ArrayList<>();
        fragments.add(new CurrentWeatherView());
        fragments.add(new HourlyWeatherView());
        fragments.add(new DailyWeatherView());
    }

    @Override
    public Fragment getItem(int position) {
        return (fragments.get(position));
    }

    @Override
    public int getCount() {
        return (fragments.size());
    }

    public Fragment getFragment(int id) {
        return (fragments.get(id));
    }
}

