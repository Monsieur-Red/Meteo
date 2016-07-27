package com.perso.red.meteo.presenters.hourlyWeather;

import com.perso.red.meteo.Widgets.Tools;
import com.perso.red.meteo.activity.GpsLocation;
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
        // Set ProgressBar Visibility
        if (!isSwipe)
            view.showProgress();

        // Get GpsLocation
        GpsLocation gpsLocation = ((MainActivity)view.getActivity()).getGpsLocation();
        if (!gpsLocation.isProviderEnabled())
            gpsLocation.showAlert();
        else if (gpsLocation.getLocation() != null)
            interactor.getWeather(this, gpsLocation.getLocation(), Tools.getCurrentDate("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()));
    }

    @Override
    public void onDialog(int title, int msg) {
        view.hideProgress();
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccessGetWeather(List<HourlyDataWeather> hourlyDataWeathers) {
        // Set RecyclerView Data
        view.updateData(hourlyDataWeathers);

        // Smooth Scroll RecyclerView
        view.smoothScrollToPosition(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        // Set ProgressBar Visibility & SwipeRefreshLayout Refreshing
        view.hideProgress();
    }

}
