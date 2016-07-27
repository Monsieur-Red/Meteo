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
        ImageView   weatherImg;
        TextView    hourTv;
        TextView    temperatureTv;
        TextView    precipProbabilityTv;

        public DataObjectHolder(View itemView) {
            super(itemView);

            // Init UI Elements
            weatherImg = (ImageView) itemView.findViewById(R.id.img_weather);
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
                holder.weatherImg.setImageResource(R.drawable.ic_clear_day);
                holder.precipProbabilityTv.setVisibility(View.INVISIBLE);
                break;
            case WeatherJson.ICON_CLEAR_NIGHT:
                holder.weatherImg.setImageResource(R.drawable.ic_clear_night);
                holder.precipProbabilityTv.setVisibility(View.INVISIBLE);
                break;
            case WeatherJson.ICON_RAIN:
                holder.weatherImg.setImageResource(R.drawable.ic_rain);

                // Set PrecipProbality
                String              precipProbaStr = String.valueOf((int)(data.getPrecipProbability() * 100)) + "%";
                holder.precipProbabilityTv.setText(precipProbaStr);
                holder.precipProbabilityTv.setVisibility(View.VISIBLE);
                break;
            case WeatherJson.ICON_SNOW:
                holder.weatherImg.setImageResource(R.drawable.ic_snow);
                holder.precipProbabilityTv.setVisibility(View.INVISIBLE);
                break;
            case WeatherJson.ICON_SLEET:
                holder.weatherImg.setImageResource(R.drawable.ic_sleet);
                holder.precipProbabilityTv.setVisibility(View.INVISIBLE);
                break;
            case WeatherJson.ICON_WIND:
                holder.weatherImg.setImageResource(R.drawable.ic_wind);
                holder.precipProbabilityTv.setVisibility(View.INVISIBLE);
                break;
            case WeatherJson.ICON_FOG:
                holder.weatherImg.setImageResource(R.drawable.ic_fog);
                holder.precipProbabilityTv.setVisibility(View.INVISIBLE);
                break;
            case WeatherJson.ICON_CLOUDY:
                holder.weatherImg.setImageResource(R.drawable.ic_cloudy);
                holder.precipProbabilityTv.setVisibility(View.INVISIBLE);
                break;
            case WeatherJson.ICON_PARTLY_CLOUDY_DAY:
                holder.weatherImg.setImageResource(R.drawable.ic_partly_cloudy_day);
                holder.precipProbabilityTv.setVisibility(View.INVISIBLE);
                break;
            case WeatherJson.ICON_PARTLY_CLOUDY_NIGHT:
                holder.weatherImg.setImageResource(R.drawable.ic_partly_cloudy_night);
                holder.precipProbabilityTv.setVisibility(View.INVISIBLE);
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
