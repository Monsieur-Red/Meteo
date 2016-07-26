package com.perso.red.meteo.models.weather;

/**
 * Created by pierr on 26/07/2016.
 */

public class CurrentWeather {

    private String  icon;
    private float   temperature;
    private float   apparentTemperature; // "Feel like"

    public String getIcon() {
        return icon;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getApparentTemperature() {
        return apparentTemperature;
    }
}
