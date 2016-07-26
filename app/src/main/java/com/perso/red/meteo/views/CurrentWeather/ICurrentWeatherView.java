package com.perso.red.meteo.views.currentWeather;

/**
 * Created by pierr on 25/07/2016.
 */

public interface ICurrentWeatherView {

    void showProgress();

    void hideProgress();

    void setDialog(int title, int msg);
}
