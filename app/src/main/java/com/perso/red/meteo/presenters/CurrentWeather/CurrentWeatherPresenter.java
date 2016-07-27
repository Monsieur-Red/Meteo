package com.perso.red.meteo.presenters.currentWeather;

import android.view.View;

import com.perso.red.meteo.R;
import com.perso.red.meteo.Widgets.Tools;
import com.perso.red.meteo.activity.GpsLocation;
import com.perso.red.meteo.activity.MainActivity;
import com.perso.red.meteo.models.weather.CurrentWeather;
import com.perso.red.meteo.models.weather.WeatherJson;
import com.perso.red.meteo.models.weather.daily.DailyDataWeather;
import com.perso.red.meteo.models.weather.daily.DailyWeather;
import com.perso.red.meteo.views.currentWeather.CurrentWeatherView;

import java.util.Locale;

/**
 * Created by pierr on 25/07/2016.
 */

public class CurrentWeatherPresenter implements ICurrentWeatherPresenter, ICurrentWeatherFinishedListener {

    private CurrentWeatherView          view;
    private CurrentWeatherInteractor    interactor;

    public CurrentWeatherPresenter(CurrentWeatherView view) {
        this.view = view;
        interactor = new CurrentWeatherInteractor(view.getContext());
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
        view.getSwipeRefreshLayout().setRefreshing(false);
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccessGetWeather(CurrentWeather currentWeather, DailyWeather dailyWeather) {
        // Update Data
        DailyDataWeather    dailyDataWeather = dailyWeather.getData().get(0);

        // Set Temperature Text
        String  tempStr = String.valueOf((int)currentWeather.getTemperature()) + "°";
        String  tempMinStr = String.valueOf((int)dailyDataWeather.getTemperatureMin()) + "°";
        String  tempMaxStr = String.valueOf((int)dailyDataWeather.getTemperatureMax()) + "°";
        String  tempApparent = String.valueOf("Ressenti " + (int)currentWeather.getApparentTemperature() + "°");
        view.getTempTv().setText(tempStr);
        view.getTempMinTv().setText(tempMinStr);
        view.getTempMaxTv().setText(tempMaxStr);
        view.getTempApparentTv().setText(tempApparent);

        // Set Weather State Text
        switch (currentWeather.getIcon()) {
            case WeatherJson.ICON_CLEAR_DAY:
                view.getWeatherStateTv().setText(R.string.current_weather_clear_day);
                view.getWeatherImg().setImageResource(R.drawable.clear_day);
                break;
            case WeatherJson.ICON_CLEAR_NIGHT:
                view.getWeatherStateTv().setText(R.string.current_weather_clear_night);
                view.getWeatherImg().setImageResource(R.drawable.clear_night);
                break;
            case WeatherJson.ICON_RAIN:
                view.getWeatherStateTv().setText(R.string.current_weather_rain);
                view.getWeatherImg().setImageResource(R.drawable.rain);
                break;
            case WeatherJson.ICON_SNOW:
                view.getWeatherStateTv().setText(R.string.current_weather_snow);
                view.getWeatherImg().setImageResource(R.drawable.snow);
                break;
            case WeatherJson.ICON_SLEET:
                view.getWeatherStateTv().setText(R.string.current_weather_sleet);
                view.getWeatherImg().setImageResource(R.drawable.sleet);
                break;
            case WeatherJson.ICON_WIND:
                view.getWeatherStateTv().setText(R.string.current_weather_wind);
                view.getWeatherImg().setImageResource(R.drawable.wind);
                break;
            case WeatherJson.ICON_FOG:
                view.getWeatherStateTv().setText(R.string.current_weather_fog);
                view.getWeatherImg().setImageResource(R.drawable.fog);
                break;
            case WeatherJson.ICON_CLOUDY:
                view.getWeatherStateTv().setText(R.string.current_weather_cloudy);
                view.getWeatherImg().setImageResource(R.drawable.cloudy);
                break;
            case WeatherJson.ICON_PARTLY_CLOUDY_DAY:
                view.getWeatherStateTv().setText(R.string.current_weather_partly_cloudy_day);
                view.getWeatherImg().setImageResource(R.drawable.partly_cloudy_day);
                break;
            case WeatherJson.ICON_PARTLY_CLOUDY_NIGHT:
                view.getWeatherStateTv().setText(R.string.current_weather_partly_cloudy_night);
                view.getWeatherImg().setImageResource(R.drawable.partly_cloudy_night);
                break;
        }

        // Set Weather Summary
        view.getWeatherSummaryTv().setText(dailyDataWeather.getSummary());
        view.getWeatherSummaryTv().setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // Set Uptodate date
        String  uptodate = "Dernière mise à jour : " + Tools.getCurrentDate("HH:mm:ss", Locale.FRENCH);
        view.getUpdateTv().setText(uptodate);

        // Set ProgressBar Visibility & SwipeRefreshLayout Refreshing
        view.hideProgress();
        view.getSwipeRefreshLayout().setRefreshing(false);
    }

}
