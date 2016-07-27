package com.perso.red.meteo.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.perso.red.meteo.R;
import com.perso.red.meteo.activity.MainActivity;
import com.perso.red.meteo.presenters.about.AboutPresenter;

/**
 * Created by pierr on 25/07/2016.
 */

public class AboutView extends Fragment implements View.OnClickListener {

    private AboutPresenter  presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Presenter
        presenter = new AboutPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_about, container, false);

        // Init Listener
        view.findViewById(R.id.btn_credits).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set ToolBar Visibility
        ((MainActivity)getActivity()).getToolbar().setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        presenter.onClick(v.getId());
    }
}
