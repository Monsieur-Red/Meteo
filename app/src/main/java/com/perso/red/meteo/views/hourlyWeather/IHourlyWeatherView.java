package com.perso.red.meteo.views.hourlyWeather;

/**
 * Created by pierr on 26/07/2016.
 */

public interface IHourlyWeatherView {

    void showProgress();

    void hideProgress();

    void setDialog(int title, int msg);
}
