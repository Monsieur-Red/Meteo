package com.perso.red.meteo.presenters.CurrentWeather;

import android.content.Context;
import android.location.Location;
import android.support.v7.app.AlertDialog;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.perso.red.meteo.R;
import com.perso.red.meteo.models.AddressComponent;
import com.perso.red.meteo.models.LocationJson;
import com.perso.red.meteo.models.Network;
import com.perso.red.meteo.models.weather.WeatherJson;

import java.util.List;

/**
 * Created by pierr on 25/07/2016.
 */

public class CurrentWeatherInteractor {

    private Context context;

    public CurrentWeatherInteractor(Context context) {
        this.context = context;
    }

    public void getWeather(final ICurrentWeatherFinishedListener listener, Location location, String date) {
        Ion.with(context)
                .load("GET", Network.URL_WEATHER_FORECAST + location.getLatitude() + "," + location.getLongitude() + "," + date + "?" + Network.OPTION_LANG_FR + "&" + Network.OPTION_UNITS_AUTO)
                .as(new TypeToken<WeatherJson>() {
                })
                .setCallback(new FutureCallback<WeatherJson>() {
                    @Override
                    public void onCompleted(Exception error, WeatherJson result) {
                        if (error == null)
                            listener.onSuccessGetWeather(result.getCurrently(), result.getDaily());
                        else
                            listener.onDialog(R.string.connection_problem_dialog_title, R.string.connection_problem_dialog_message);
                    }
                });
    }

}