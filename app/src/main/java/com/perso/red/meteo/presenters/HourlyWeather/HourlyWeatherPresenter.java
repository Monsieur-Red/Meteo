package com.perso.red.meteo.presenters.hourlyWeather;

import android.support.v7.widget.LinearLayoutManager;

import com.perso.red.meteo.Widgets.Tools;
import com.perso.red.meteo.activity.MainActivity;
import com.perso.red.meteo.models.weather.hourly.HourlyDataWeather;
import com.perso.red.meteo.views.hourlyWeather.HourlyWeatherView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 26/07/2016.
 */
public class HourlyWeatherPresenter implements IHourlyWeatherPresenter, IHourlyWeatherFinishedListener {

    private HourlyWeatherView           view;
    private HourlyWeatherInteractor     interactor;

    public HourlyWeatherPresenter(HourlyWeatherView view) {
        this.view = view;
        interactor = new HourlyWeatherInteractor(view.getContext());
    }

    @Override
    public void getWeather(boolean isSwipe) {
        if (!isSwipe)
            view.showProgress();
        interactor.getWeather(this, ((MainActivity)view.getActivity()).getGpsLocation().getLocation(), Tools.getCurrentDate("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()));
    }

    @Override
    public void onDialog(int title, int msg) {
        view.hideProgress();
        view.getSwipeRefreshLayout().setRefreshing(false);
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccessGetWeather(List<HourlyDataWeather> hourlyDataWeathers) {
        // Set RecyclerView Data
        view.getHourlyWeatherRVA().update(hourlyDataWeathers);

        // Set ProgressBar Visibility & SwipeRefreshLayout Refreshing
        view.hideProgress();
        view.getSwipeRefreshLayout().setRefreshing(false);

        // Smooth Scroll RecyclerView
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        view.getRecyclerView().smoothScrollToPosition(hour);
//        LinearLayoutManager layoutManager = (LinearLayoutManager) view.getRecyclerView().getLayoutManager();
//        layoutManager.scrollToPositionWithOffset(hour, 0);
    }

}
