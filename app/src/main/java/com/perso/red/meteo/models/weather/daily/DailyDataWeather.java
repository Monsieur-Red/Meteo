package com.perso.red.meteo.models.weather.daily;

/**
 * Created by pierr on 26/07/2016.
 */
public class DailyDataWeather {

    private String  summary;
    private String  icon;
    private float   temperatureMin;
    private float   temperatureMax;

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }
}
