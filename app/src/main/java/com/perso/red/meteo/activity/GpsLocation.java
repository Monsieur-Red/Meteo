package com.perso.red.meteo.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.perso.red.meteo.R;
import com.perso.red.meteo.models.gpsLocation.LocationJson;
import com.perso.red.meteo.models.Network;

import java.util.List;

/**
 * Created by pierr on 25/07/2016.
 */

public class GpsLocation implements LocationListener {

    private MainActivity    activity;
    private LocationManager locationManager;
    private Location        location;

    public GpsLocation(MainActivity activity) {
        this.activity = activity;

        // Init LocationManager
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        // Check Permission GPS & Request Location Updates
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, this);
        }
    }

    public boolean isProviderEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(activity);

        dialog.setTitle(activity.getString(R.string.location_dialog_title))
                .setCancelable(false)
                .setMessage(R.string.location_dialog_message)
                .setPositiveButton(activity.getString(R.string.location_dialog_settings), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        activity.startActivity(myIntent);
                    }
                })
                .setNegativeButton(R.string.location_dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        // Request GoogleAPI in order to find the city name
        if (this.location == null || location.getLongitude() != this.location.getLongitude() || location.getLatitude() != this.location.getLatitude()) {
            getCityName(location);
            this.location = location;

            // Refresh View
            Fragment currentF = activity.getMyViewPager().getCurrentFragment();
            if (currentF.isResumed())
                currentF.onViewCreated(currentF.getView(), currentF.getArguments());
        }
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

    private void getCityName(Location location) {
        Ion.with(activity.getApplicationContext())
                .load("GET", Network.URL_GOOGLE_API_MAP + location.getLatitude() + "," + location.getLongitude())
                .as(new TypeToken<LocationJson>(){})
                .setCallback(new FutureCallback<LocationJson>() {
                    @Override
                    public void onCompleted(Exception error, LocationJson result) {
                        if (error == null) {
                            if (result.getStatus().equals(LocationJson.STATUS_OK)) {
                                for (com.perso.red.meteo.models.gpsLocation.Location locationData : result.getResults()) {
                                    List<String>    types = locationData.getTypes();

                                    if (types.size() == 2 && types.get(0).equals("locality") && types.get(1).equals("political")) {
                                        activity.setTitle(locationData.getFormatted_address());
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
