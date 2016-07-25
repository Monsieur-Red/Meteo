package com.perso.red.meteo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.perso.red.meteo.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by pierr on 25/07/2016.
 */

public class MainActivity extends AppCompatActivity {

    private GpsLocation gpsLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set View
        setContentView(R.layout.activity_main);

        // Init Gps Tracker
        gpsLocation = new GpsLocation(this);

        // Init ViewPager
        initViewPager();
    }

    private void initViewPager() {
        ViewPager   viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Set Adapter
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);

        // Set Pager Indicator
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.pager_indicator);
        indicator.setViewPager(viewPager);

        // Set ViewPager limit & pos
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
    }


    public GpsLocation getGpsLocation() {
        return gpsLocation;
    }
}
