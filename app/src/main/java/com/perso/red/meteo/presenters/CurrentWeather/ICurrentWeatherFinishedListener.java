package com.perso.red.meteo.presenters.CurrentWeather;

/**
 * Created by pierr on 25/07/2016.
 */

public interface ICurrentWeatherFinishedListener {

    void onDialog(int title, int msg);

    void onSuccessGetLocation(String location);


}
