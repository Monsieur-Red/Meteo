package com.perso.red.meteo.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.perso.red.meteo.R;
import com.perso.red.meteo.activity.viewpager.MyPageTransformer;
import com.perso.red.meteo.activity.viewpager.MyPagerAdapter;
import com.perso.red.meteo.activity.viewpager.MyViewPager;

/**
 * Created by pierr on 25/07/2016.
 */

public class MainActivity extends AppCompatActivity {

    private MyViewPager             myViewPager;
    private MyNavigationBottomBar   myNavigationBottomBar;
    private GpsLocation             gpsLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set View
        setContentView(R.layout.activity_main);

        // Init Gps Tracker
        gpsLocation = new GpsLocation(this);

        // Init ViewPager
        myViewPager = new MyViewPager(this);

        // Init Navigation Bottom Bar
        myNavigationBottomBar = new MyNavigationBottomBar(this, myViewPager.getViewPager());

        // Init ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set Status Bar Color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.primary_dark));
        }
    }

    private void initViewPager() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_refresh:
                // Refresh the view
                Fragment    currentF = myViewPager.getCurrentFragment();
                currentF.onViewCreated(currentF.getView(), currentF.getArguments());
                return true;
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public MyNavigationBottomBar getMyNavigationBottomBar() {
        return myNavigationBottomBar;
    }

    public GpsLocation getGpsLocation() {
        return gpsLocation;
    }
}
