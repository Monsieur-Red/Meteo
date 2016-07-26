package com.perso.red.meteo.presenters.CurrentWeather;

import android.content.Context;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.perso.red.meteo.R;
import com.perso.red.meteo.models.AddressComponent;
import com.perso.red.meteo.models.Location;
import com.perso.red.meteo.models.LocationJson;

import java.util.List;

/**
 * Created by pierr on 25/07/2016.
 */

public class CurrentWeatherInteractor {

    private Context context;

    public CurrentWeatherInteractor(Context context) {
        this.context = context;
    }

//    public void getLocation(final ICurrentWeatherFinishedListener listener, double latitude, double longitude) {
//        Ion.with(context)
//                .load("GET", "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + String.valueOf(latitude) + "," + String.valueOf(longitude))
//                .as(new TypeToken<LocationJson>(){})
//                .setCallback(new FutureCallback<LocationJson>() {
//                    @Override
//                    public void onCompleted(Exception error, LocationJson result) {
//                        if (error == null) {
//                            if (result.getStatus().equals(LocationJson.STATUS_OK)) {
//                                List<AddressComponent>  addressComponents = result.getResults().get(0).getAddress_components();
//                                String location = null;
//
//                                for (AddressComponent addressComponent : addressComponents) {
//                                    for (String type : addressComponent.getTypes()) {
//                                        if (type.equals("locality")) {
//                                            listener.onSuccessGetLocation(addressComponent.getLong_name());
//                                            break;
//                                        }
//                                    }
//                                }
//
//                            }
//                        }
//                        else
//                            listener.onDialog(R.string.connection_problem_dialog_title, R.string.connection_problem_dialog_message);
//                    }
//                });
//    }
}
