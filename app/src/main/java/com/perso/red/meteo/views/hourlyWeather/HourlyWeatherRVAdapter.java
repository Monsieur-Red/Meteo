package com.perso.red.meteo.views.hourlyWeather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.perso.red.meteo.R;
import com.perso.red.meteo.models.weather.WeatherJson;
import com.perso.red.meteo.models.weather.hourly.HourlyDataWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 26/07/2016.
 */

public class HourlyWeatherRVAdapter extends RecyclerView.Adapter<HourlyWeatherRVAdapter.DataObjectHolder> {

    private Context context;

    private List<HourlyDataWeather> hourlyDataWeathers;

    public HourlyWeatherRVAdapter(Context context) {
        this.context = context;
        hourlyDataWeathers = new ArrayList<>();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        ImageView   imageImg;
        TextView    hourTv;
        TextView    temperatureTv;
        TextView    precipProbabilityTv;

        public DataObjectHolder(View itemView) {
            super(itemView);

            // Init UI Elements
            imageImg = (ImageView) itemView.findViewById(R.id.img_weather);
            hourTv = (TextView) itemView.findViewById(R.id.tv_hour);
            temperatureTv = (TextView) itemView.findViewById(R.id.tv_temperature);
            precipProbabilityTv = (TextView) itemView.findViewById(R.id.tv_precipProbability);
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hourly_weather_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        HourlyDataWeather   data = hourlyDataWeathers.get(position);
        String              tempStr = String.valueOf((int)data.getTemperature()) + "°";

        // Set Hour & Temperature & PrecipProbability
        holder.hourTv.setText(context.getResources().getStringArray(R.array.hourly_weather_view_hour_list)[position]);
        holder.temperatureTv.setText(tempStr);

        // Set Image
        switch (data.getIcon()) {
            case WeatherJson.ICON_CLEAR_DAY:
                break;
            case WeatherJson.ICON_CLEAR_NIGHT:
                break;
            case WeatherJson.ICON_RAIN:
                // Set PrecipProbality
                String              precipProbaStr = String.valueOf((int)(data.getPrecipProbability() * 100)) + "%";
                holder.precipProbabilityTv.setText(precipProbaStr);
                break;
            case WeatherJson.ICON_SNOW:
                break;
            case WeatherJson.ICON_SLEET:
                break;
            case WeatherJson.ICON_WIND:
                break;
            case WeatherJson.ICON_FOG:
                break;
            case WeatherJson.ICON_CLOUDY:
                break;
            case WeatherJson.ICON_PARTLY_CLOUDY_DAY:
                break;
            case WeatherJson.ICON_PARTLY_CLOUDY_NIGHT:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return (hourlyDataWeathers.size());
    }

    public void update(List<HourlyDataWeather> newHourlyDataWeathers) {
        hourlyDataWeathers.clear();
        hourlyDataWeathers.addAll(newHourlyDataWeathers);
        notifyDataSetChanged();
    }

}
