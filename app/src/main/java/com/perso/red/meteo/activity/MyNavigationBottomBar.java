package com.perso.red.meteo.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.perso.red.meteo.R;

/**
 * Created by pierr on 26/07/2016.
 */

public class MyNavigationBottomBar {

    private AHBottomNavigation bottomNavigation;

    public MyNavigationBottomBar(final MainActivity activity, final ViewPager viewPager) {
        bottomNavigation = (AHBottomNavigation) activity.findViewById(R.id.bottom_navigation);
        Context context = activity.getApplicationContext();

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.navigation_bottom_bar_now, R.drawable.ic_today_black_24dp, R.color.primary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.navigation_bottom_bar_today, R.drawable.ic_schedule_black_24dp, R.color.primary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.navigation_bottom_bar_week, R.drawable.ic_view_week_black_24dp, R.color.primary);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        // Set background color
//        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(context, R.color.primary));

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Change colors
//        bottomNavigation.setAccentColor(ContextCompat.getColor(context, R.color.primary));
//        bottomNavigation.setInactiveColor(ContextCompat.getColor(context, R.color.primary_dark));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Force the titles to be not displayed (Respect Material Design guidelines!)
        bottomNavigation.setForceTitlesDisplay(false);

        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);

        // Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                if (!wasSelected)
                    viewPager.setCurrentItem(position);
            }
        });
    }

    public AHBottomNavigation getBar() {
        return bottomNavigation;
    }

}
