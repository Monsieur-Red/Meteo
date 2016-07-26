package com.perso.red.meteo.models.weather;

/**
 * Created by pierr on 26/07/2016.
 */

public class CurrentlyWeather {

    private String icon;
    private String temperature;
    private String apparentTemperature; // "Feel like"

    public String getIcon() {
        return icon;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getApparentTemperature() {
        return apparentTemperature;
    }
}
