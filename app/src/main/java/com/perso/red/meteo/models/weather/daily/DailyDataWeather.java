package com.perso.red.meteo.models.weather.daily;

/**
 * Created by pierr on 26/07/2016.
 */

public class DailyDataWeather {

    private String  summary;
    private String  icon;
    private String  temperatureMin;
    private String  temperatureMax;

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }
}
