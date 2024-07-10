package edu.uncc.weatherapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.uncc.weatherapp.models.Forecast;

public class WeatherForecastRecyclerViewAdapter extends RecyclerView.Adapter<WeatherForecastRecyclerViewAdapter.ViewHolder> {
    ArrayList<Forecast> forecastList;

    public WeatherForecastRecyclerViewAdapter(ArrayList<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public WeatherForecastRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("demo", "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherForecastRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d("demo", "onBindViewHolder: ");
        try {
            Forecast forecast = forecastList.get(position);
            Log.d("demo", "onBindViewHolder: forecast:" + forecast.getStartTime());
            holder.textViewDateTime.setText(forecast.getStartTime());
            holder.textViewTemperature.setText(forecast.getTemperature()+" F");
            holder.textViewHumidity.setText("Humidity: "+forecast.getHumidity()+"%");
            holder.textViewWindSpeed.setText("Wind Speed: "+forecast.getWindSpeed());
            holder.textViewShortForecast.setText(forecast.getShortForecast());
            ImageView imageViewIcon = holder.imageViewIcon;
            Picasso.get().load(forecast.getIcon()).into(imageViewIcon);
        } catch (Exception e) {
            Log.d("demo", "onBindViewHolder: error"+ e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDateTime;
        TextView textViewTemperature;
        TextView textViewHumidity;
        TextView textViewWindSpeed;
        TextView textViewShortForecast;
        ImageView imageViewIcon;

        View rootView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("demo", "ViewHolder: ");
            rootView=itemView;
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewTemperature = itemView.findViewById(R.id.textViewTemperature);
            textViewHumidity = itemView.findViewById(R.id.textViewHumidity);
            textViewWindSpeed = itemView.findViewById(R.id.textViewWindSpeed);
            textViewShortForecast = itemView.findViewById(R.id.textViewForecast);
            imageViewIcon = itemView.findViewById(R.id.imageView);

        }
    }
}
