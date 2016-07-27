package com.perso.red.meteo.views.hourlyWeather;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.perso.red.meteo.R;
import com.perso.red.meteo.Widgets.LinearLayoutManagerWithSmoothScroller;
import com.perso.red.meteo.activity.MainActivity;
import com.perso.red.meteo.models.weather.hourly.HourlyDataWeather;
import com.perso.red.meteo.presenters.hourlyWeather.HourlyWeatherPresenter;

import java.util.List;

/**
 * Created by pierr on 26/07/2016.
 */

public class HourlyWeatherView extends Fragment implements IHourlyWeatherView {

    private HourlyWeatherPresenter  presenter;

    private RecyclerView            recyclerView;
    private HourlyWeatherRVAdapter  hourlyWeatherRVA;
    private SwipeRefreshLayout      swipeRefreshLayout;
    private ProgressBar             progressBar;
    private AlertDialog             dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Presenter
        presenter = new HourlyWeatherPresenter(this);

        // Init Dialog
        dialog = new AlertDialog.Builder(getContext())
                .setCancelable(true)
                .create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_hourly_weather, container, false);

        // Init UI Elements
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        // Init RefreshLayout
        initSwipeRefreshLayout();

        // Init Recycler View
        initRecyclerView(view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set ToolBar Visibility
        ((MainActivity)getActivity()).getToolbar().setVisibility(View.VISIBLE);

        // Set Weather Model
        presenter.getWeather(false);
    }

    private void initSwipeRefreshLayout() {
        // Configure the refreshing colors
        swipeRefreshLayout.setColorSchemeResources(R.color.icons);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.primary);

        // Init Refresh Listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getWeather(true);
            }
        });
    }

    private void initRecyclerView(View view) {
        // Init Recycler Views
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // Init & Set Adapter
        hourlyWeatherRVA = new HourlyWeatherRVAdapter(this.getContext());
        recyclerView.setAdapter(hourlyWeatherRVA);

        // Init LayoutManagers
        recyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(getContext()));

        // Set Options to enable toolbar display/hide
        recyclerView.setNestedScrollingEnabled(true);
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);
        recyclerView.setHasFixedSize(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setDialog(int title, int msg) {
        dialog.setTitle(getString(title));
        dialog.setMessage(getString(msg));
        dialog.show();
    }

    @Override
    public void updateData(List<HourlyDataWeather> hourlyDataWeathers) {
        hourlyWeatherRVA.update(hourlyDataWeathers);
    }

    @Override
    public void smoothScrollToPosition(int hour) {
        recyclerView.smoothScrollToPosition(hour);
    }
}
