package com.perso.red.meteo.models.weather.hourly;

/**
 * Created by pierr on 26/07/2016.
 */

public class HourlyDataWeather {

    private String  icon;
    private float   temperature;
    private float   precipProbability;

    public String getIcon() {
        return icon;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPrecipProbability() {
        return precipProbability;
    }
}
