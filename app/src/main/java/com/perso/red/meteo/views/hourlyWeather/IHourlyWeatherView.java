package com.perso.red.meteo.views.hourlyWeather;

import com.perso.red.meteo.models.weather.hourly.HourlyDataWeather;

import java.util.List;

/**
 * Created by pierr on 26/07/2016.
 */

public interface IHourlyWeatherView {

    void showProgress();

    void hideProgress();

    void setDialog(int title, int msg);

    void updateData(List<HourlyDataWeather> hourlyDataWeathers);

    void smoothScrollToPosition(int hour);
}
