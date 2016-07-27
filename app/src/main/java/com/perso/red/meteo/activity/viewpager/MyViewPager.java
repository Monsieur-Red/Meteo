package com.perso.red.meteo.activity.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.perso.red.meteo.R;
import com.perso.red.meteo.activity.MainActivity;
import com.perso.red.meteo.activity.MyNavigationBottomBar;

/**
 * Created by pierr on 26/07/2016.
 */

public class MyViewPager {

    private ViewPager       viewPager;
    private MyPagerAdapter  myPagerAdapter;

    public MyViewPager(final MainActivity activity) {
        viewPager = (ViewPager) activity.findViewById(R.id.viewpager);

        // Set Adapter
        myPagerAdapter = new MyPagerAdapter(activity.getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);

        // Set ViewPager pager transformer & limit & pos
        viewPager.setPageTransformer(false, new MyPageTransformer());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);

        // Init Listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // Set Navigation Bottom bar Position
                if (activity.getMyNavigationBottomBar().getBar().getCurrentItem() != viewPager.getCurrentItem())
                    activity.getMyNavigationBottomBar().getBar().setCurrentItem(viewPager.getCurrentItem());

                // Set View Data
                Fragment fragment = myPagerAdapter.getFragment(position);
                fragment.onViewCreated(fragment.getView(), fragment.getArguments());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public Fragment getCurrentFragment() {
        return myPagerAdapter.getFragment(viewPager.getCurrentItem());
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
}
