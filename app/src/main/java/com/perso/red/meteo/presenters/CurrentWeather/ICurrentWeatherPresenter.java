package com.perso.red.meteo.presenters.CurrentWeather;

import android.widget.TextView;

/**
 * Created by pierr on 25/07/2016.
 */

public interface ICurrentWeatherPresenter {

    void getLocation(TextView locationTv);

    void getDate(TextView dateTv);

    void getWeather();

    void onClick(int viewId);
}
