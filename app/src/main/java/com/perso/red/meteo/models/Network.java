package com.perso.red.meteo.models;

/**
 * Created by pierr on 26/07/2016.
 */
public class Network {

    public static final String  API_KEY = "3bdc20298628c39240dcd31811ca89df";

    /* Requests */
    /* Google map API */
    public static final String URL_GOOGLE_API_MAP = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";

    /* Dark Sky Forecast API */
    public static final String URL_WEATHER_FORECAST = "https://api.forecast.io/forecast/" + API_KEY + "/";
    /* Options */
    public static final String OPTION_LANG_FR = "lang=fr";
    public static final String OPTION_UNITS_AUTO = "units=auto";

}
