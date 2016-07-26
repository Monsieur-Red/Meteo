package com.perso.red.meteo.presenters.CurrentWeather;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.perso.red.meteo.activity.MainActivity;
import com.perso.red.meteo.views.CurrentWeather.CurrentWeatherView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 25/07/2016.
 */

public class CurrentWeatherPresenter implements ICurrentWeatherPresenter, ICurrentWeatherFinishedListener {

    private CurrentWeatherView          view;
    private CurrentWeatherInteractor    interactor;

    public CurrentWeatherPresenter(CurrentWeatherView view) {
        this.view = view;
        interactor = new CurrentWeatherInteractor(view.getContext());
    }

    @Override
    public void getWeather() {
//        view.showProgress();
    }

    @Override
    public void onDialog(int title, int msg) {
        view.hideProgress();
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccessGetWeather() {
        view.hideProgress();
    }

}
