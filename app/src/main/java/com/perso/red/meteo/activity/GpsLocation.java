package com.perso.red.meteo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.perso.red.meteo.R;
import com.perso.red.meteo.models.AddressComponent;
import com.perso.red.meteo.models.LocationJson;

import java.util.List;

/**
 * Created by pierr on 25/07/2016.
 */

public class GpsLocation implements LocationListener {

    private MainActivity    activity;
    private Location        location;

    public GpsLocation(MainActivity activity) {
        this.activity = activity;

        // Init LocationManager
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);

            location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 10, this);
        }
        else
            showAlert(activity);

    }

    private void showAlert(final MainActivity activity) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(activity);

        dialog.setTitle(activity.getString(R.string.location_dialog_title))
                .setCancelable(true)
                .setMessage(R.string.location_dialog_message)
                .setPositiveButton(activity.getString(R.string.location_dialog_settings), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        activity.startActivity(myIntent);
                    }
                })
                .setNegativeButton(activity.getString(R.string.location_dialog_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;

        // Request GoogleAPI in order to find the city name
        getCityName();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void getCityName() {
        Ion.with(activity.getApplicationContext())
                .load("GET", "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()))
                .as(new TypeToken<LocationJson>(){})
                .setCallback(new FutureCallback<LocationJson>() {
                    @Override
                    public void onCompleted(Exception error, LocationJson result) {
                        if (error == null) {
                            if (result.getStatus().equals(LocationJson.STATUS_OK)) {
                                List<AddressComponent> addressComponents = result.getResults().get(0).getAddress_components();

                                for (AddressComponent addressComponent : addressComponents) {
                                    for (String type : addressComponent.getTypes()) {
                                        if (type.equals("locality"))
                                            activity.setTitle(addressComponent.getLong_name());
                                            break;
                                        }
                                    }
                                }

                            }
                        else {
                            final AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                            dialog.setTitle(activity.getString(R.string.connection_problem_dialog_title))
                                    .setMessage(R.string.connection_problem_dialog_message)
                                    .setCancelable(true)
                                    .show();
                        }
                    }
                });
    }

    public Location getLocation() {
        return location;
    }
}
