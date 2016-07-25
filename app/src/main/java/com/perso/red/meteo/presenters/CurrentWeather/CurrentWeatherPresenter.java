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
    public void getLocation(TextView locationTv) {
        // Get Location Name
        final Location location = ((MainActivity) view.getActivity()).getGpsLocation().getLocation();

        if (location != null) {
            view.showProgress();
            interactor.getLocation(this, location.getLatitude(), location.getLongitude());
        }
    }

    @Override
    public void getDate(TextView dateTv) {

    }

    @Override
    public void getWeather() {

    }

    @Override
    public void onClick(int viewId) {

    }

    @Override
    public void onDialog(int title, int msg) {
        view.hideProgress();
        view.setDialog(title, msg);
    }

    @Override
    public void onSuccessGetLocation(String location) {
        view.getLocationTv().setText(location);
        view.hideProgress();
    }
}
