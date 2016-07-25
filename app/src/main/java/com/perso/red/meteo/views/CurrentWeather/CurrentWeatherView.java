package com.perso.red.meteo.views.CurrentWeather;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.perso.red.meteo.R;
import com.perso.red.meteo.presenters.CurrentWeather.CurrentWeatherPresenter;

/**
 * Created by pierr on 25/07/2016.
 */

public class CurrentWeatherView extends Fragment implements ICurrentWeatherView, View.OnClickListener {

    private CurrentWeatherPresenter presenter;

    private TextView    locationTv;
    private TextView    dateTv;
    private ImageView   weatherImg;
    private TextView    temperatureTv;
    private TextView    weatherStateTv;
    private TextView    updateTv;

    private ProgressBar progressBar;
    private AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Presenter
        presenter = new CurrentWeatherPresenter(this);

        // Init Dialog
        dialog = new AlertDialog.Builder(getContext())
                .setCancelable(true)
                .create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_currentweather, container, false);

        // Init UI Elements
        locationTv = (TextView) view.findViewById(R.id.tv_location);
        dateTv = (TextView) view.findViewById(R.id.tv_date);
        weatherImg = (ImageView) view.findViewById(R.id.img_weather);
        temperatureTv = (TextView) view.findViewById(R.id.tv_temperature);
        weatherStateTv = (TextView) view.findViewById(R.id.tv_weather_state);
        updateTv = (TextView) view.findViewById(R.id.tv_update);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init Listeners
        view.findViewById(R.id.btn_refresh).setOnClickListener(this);
        view.findViewById(R.id.btn_about).setOnClickListener(this);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.getLocation(locationTv);
        presenter.getDate(dateTv);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDialog(int title, int msg) {
        dialog.setTitle(getString(title));
        dialog.setMessage(getString(msg));
        dialog.show();
    }

    public TextView getLocationTv() {
        return locationTv;
    }
}
