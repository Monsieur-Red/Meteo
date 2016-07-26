package com.perso.red.meteo.presenters.CurrentWeather;

import com.perso.red.meteo.models.weather.CurrentlyWeather;
import com.perso.red.meteo.models.weather.daily.DailyWeather;

/**
 * Created by pierr on 25/07/2016.
 */

public interface ICurrentWeatherFinishedListener {

    void onDialog(int title, int msg);

    void onSuccessGetWeather(CurrentlyWeather currentlyWeather, DailyWeather dailyWeather);

}
