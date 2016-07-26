package com.perso.red.meteo.presenters.currentWeather;

import android.view.View;

import com.perso.red.meteo.R;
import com.perso.red.meteo.Widgets.Tools;
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
    public void getWeather() {
        view.showProgress();
        interactor.getWeather(this, ((MainActivity)view.getActivity()).getGpsLocation().getLocation(), Tools.getCurrentDate("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()));
    }

    @Override
    public void onDialog(int title, int msg) {
        view.hideProgress();
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
                break;
            case WeatherJson.ICON_CLEAR_NIGHT:
                view.getWeatherStateTv().setText(R.string.current_weather_clear_night);
                break;
            case WeatherJson.ICON_RAIN:
                view.getWeatherStateTv().setText(R.string.current_weather_rain);
                break;
            case WeatherJson.ICON_SNOW:
                view.getWeatherStateTv().setText(R.string.current_weather_snow);
                break;
            case WeatherJson.ICON_SLEET:
                view.getWeatherStateTv().setText(R.string.current_weather_sleet);
                break;
            case WeatherJson.ICON_WIND:
                view.getWeatherStateTv().setText(R.string.current_weather_wind);
                break;
            case WeatherJson.ICON_FOG:
                view.getWeatherStateTv().setText(R.string.current_weather_fog);
                break;
            case WeatherJson.ICON_CLOUDY:
                view.getWeatherStateTv().setText(R.string.current_weather_cloudy);
                break;
            case WeatherJson.ICON_PARTLY_CLOUDY_DAY:
                view.getWeatherStateTv().setText(R.string.current_weather_partly_cloudy_day);
                break;
            case WeatherJson.ICON_PARTLY_CLOUDY_NIGHT:
                view.getWeatherStateTv().setText(R.string.current_weather_partly_cloudy_night);
                break;
        }

        // Set Weather Summary
        view.getWeatherSummaryTv().setText(dailyDataWeather.getSummary());
        view.getWeatherSummaryTv().setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // Set Uptodate date
        String  uptodate = "Dernière mise à jour : " + Tools.getCurrentDate("HH:mm:ss", Locale.FRENCH);
        view.getUpdateTv().setText(uptodate);

        // Set Progress Bar Visibility
        view.hideProgress();
    }

}
