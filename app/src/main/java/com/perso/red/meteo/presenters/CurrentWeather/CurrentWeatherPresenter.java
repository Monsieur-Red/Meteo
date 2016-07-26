package com.perso.red.meteo.presenters.CurrentWeather;

import android.location.Location;

import com.perso.red.meteo.R;
import com.perso.red.meteo.Tools;
import com.perso.red.meteo.activity.MainActivity;
import com.perso.red.meteo.models.weather.CurrentlyWeather;
import com.perso.red.meteo.models.weather.WeatherJson;
import com.perso.red.meteo.models.weather.daily.DailyDataWeather;
import com.perso.red.meteo.models.weather.daily.DailyWeather;
import com.perso.red.meteo.views.CurrentWeather.CurrentWeatherView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public void onSuccessGetWeather(CurrentlyWeather currentlyWeather, DailyWeather dailyWeather) {
        // Update Data
        DailyDataWeather    dailyDataWeather = dailyWeather.getData().get(0);

        // Set Temperature Text
        float   temp = Float.valueOf(currentlyWeather.getTemperature());
        float   tempMin = Float.valueOf(dailyDataWeather.getTemperatureMin());
        float   tempMax = Float.valueOf(dailyDataWeather.getTemperatureMax());
        String  tempStr = String.valueOf((int)temp) + "°";
        String  tempMinStr = String.valueOf((int)tempMin) + "°";
        String  tempMaxStr = String.valueOf((int)tempMax) + "°";
        view.getTempTv().setText(tempStr);
        view.getTempMinTv().setText(tempMinStr);
        view.getTempMaxTv().setText(tempMaxStr);

        // Set Weather State Text
        switch (currentlyWeather.getIcon()) {
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

        // Set Uptodate date
        String  uptodate = "Dernière mise à jour : " + Tools.getCurrentDate("HH:mm:ss", Locale.FRENCH);
        view.getUpdateTv().setText(uptodate);

        // Set Progress Bar Visibility
        view.hideProgress();
    }

}
