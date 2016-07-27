package com.perso.red.meteo.presenters.about;

import android.content.Intent;
import android.net.Uri;

import com.perso.red.meteo.R;
import com.perso.red.meteo.models.Network;
import com.perso.red.meteo.views.AboutView;

/**
 * Created by pierr on 27/07/2016.
 */
public class AboutPresenter implements IAboutPresenter {

    private AboutView   view;

    public AboutPresenter(AboutView view) {
        this.view = view;
    }

    @Override
    public void onClick(int viewId) {
        if (viewId == R.id.btn_credits) {
            Uri     uriUrl = Uri.parse(Network.URL_CREDITS_FORECAST);
            Intent  launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);

            view.getActivity().startActivity(launchBrowser);
        }
    }
}
