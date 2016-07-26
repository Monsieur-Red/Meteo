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

public class CurrentWeatherView extends Fragment implements ICurrentWeatherView {

    private CurrentWeatherPresenter presenter;

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
        weatherImg = (ImageView) view.findViewById(R.id.img_weather);
        temperatureTv = (TextView) view.findViewById(R.id.tv_temperature);
        weatherStateTv = (TextView) view.findViewById(R.id.tv_weather_state);
        updateTv = (TextView) view.findViewById(R.id.tv_update);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.getWeather();
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
}
