package com.perso.red.meteo.presenters.currentWeather;

import com.perso.red.meteo.models.weather.CurrentWeather;
import com.perso.red.meteo.models.weather.daily.DailyWeather;

/**
 * Created by pierr on 25/07/2016.
 */

public interface ICurrentWeatherFinishedListener {

    void onDialog(int title, int msg);

    void onSuccessGetWeather(CurrentWeather currentWeather, DailyWeather dailyWeather);

}
