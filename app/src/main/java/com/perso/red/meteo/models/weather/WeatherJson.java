package com.perso.red.meteo.models.weather;

import com.perso.red.meteo.models.weather.daily.DailyWeather;

/**
 * Created by pierr on 26/07/2016.
 */

public class WeatherJson {

    public static final String  ICON_CLEAR_DAY = "clear-day";
    public static final String  ICON_CLEAR_NIGHT = "clear-night";
    public static final String  ICON_RAIN = "rain";
    public static final String  ICON_SNOW= "snow";
    public static final String  ICON_SLEET = "sleet";
    public static final String  ICON_WIND = "wind";
    public static final String  ICON_FOG = "fog";
    public static final String  ICON_CLOUDY = "cloudy";
    public static final String  ICON_PARTLY_CLOUDY_DAY = "partly-cloudy-day";
    public static final String  ICON_PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";

    private CurrentlyWeather    currently;
    private HourlyWeather       hourly;
    private DailyWeather daily;

    public CurrentlyWeather getCurrently() {
        return currently;
    }

    public HourlyWeather getHourly() {
        return hourly;
    }

    public DailyWeather getDaily() {
        return daily;
    }
}
