package com.perso.red.meteo.presenters.hourlyWeather;

import com.perso.red.meteo.models.weather.hourly.HourlyDataWeather;

import java.util.List;

/**
 * Created by pierr on 26/07/2016.
 */

public interface IHourlyWeatherFinishedListener {

    void onDialog(int title, int msg);

    void onSuccessGetWeather(List<HourlyDataWeather> hourlyDataWeathers);
}
