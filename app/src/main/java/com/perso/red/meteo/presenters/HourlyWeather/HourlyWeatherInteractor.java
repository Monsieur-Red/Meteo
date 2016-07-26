package com.perso.red.meteo.presenters.hourlyWeather;

import android.content.Context;
import android.location.Location;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.perso.red.meteo.R;
import com.perso.red.meteo.models.Network;
import com.perso.red.meteo.models.weather.WeatherJson;

/**
 * Created by pierr on 26/07/2016.
 */

public class HourlyWeatherInteractor {

    private Context context;

    public HourlyWeatherInteractor(Context context) {
        this.context = context;
    }

    public void getWeather(final IHourlyWeatherFinishedListener listener, Location location, String date) {
        Ion.with(context)
                .load("GET", Network.URL_WEATHER_FORECAST + location.getLatitude() + "," + location.getLongitude() + "," + date + "?" + Network.OPTION_LANG_FR + "&" + Network.OPTION_UNITS_AUTO + Network.OPTION_EXCLUDE_BLOCK_HOURLY_WEATHER)
                .as(new TypeToken<WeatherJson>() {
                })
                .setCallback(new FutureCallback<WeatherJson>() {
                    @Override
                    public void onCompleted(Exception error, WeatherJson result) {
                        if (error == null)
                            listener.onSuccessGetWeather(result.getHourly().getData());
                        else
                            listener.onDialog(R.string.connection_problem_dialog_title, R.string.connection_problem_dialog_message);
                    }
                });
    }

}
