package edu.uncc.weatherapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.uncc.weatherapp.R;
import edu.uncc.weatherapp.WeatherForecastRecyclerViewAdapter;
import edu.uncc.weatherapp.WeatherForecastRecyclerViewAdapter;
import edu.uncc.weatherapp.databinding.FragmentWeatherForecastBinding;
import edu.uncc.weatherapp.models.City;
import edu.uncc.weatherapp.models.Forecast;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class WeatherForecastFragment extends Fragment {
    private static final String ARG_PARAM_CITY = "ARG_PARAM_CITY";
    private City mCity;

    private final OkHttpClient client = new OkHttpClient();

    public String forecastUrl = "";

    public String iconUrl = "";

    ArrayList<Forecast> forecastList = new ArrayList<Forecast>();

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    WeatherForecastRecyclerViewAdapter adapter;

    TextView textViewCityName;

    public WeatherForecastFragment() {
        // Required empty public constructor
    }

    public static WeatherForecastFragment newInstance(City city) {
        WeatherForecastFragment fragment = new WeatherForecastFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCity = (City) getArguments().getSerializable(ARG_PARAM_CITY);

        }
    }

    FragmentWeatherForecastBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherForecastBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewCityName = binding.textViewCityName;
        textViewCityName.setText(mCity.getName()+", "+mCity.getState());
        Log.d("demo", "onViewCreated: in Forecast city lat: " + mCity.getLat());
        Request request = new Request.Builder()
                .url("https://api.weather.gov/points/" + mCity.getLat() + "," + mCity.getLng())
                .build();
        Log.d("demo", "onViewCreated: url after bind: " + request.url().toString());
        Log.d("demo", "just before sending request");
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                e.printStackTrace();
                Log.d("demo", "onFailure: first request failed");
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.d("demo", "onResponse: ");
                if (response.isSuccessful()) {
                    String forecastData = response.body().string();
                    Log.d("demo", "After lat and long : " + forecastData);
                    JSONObject forecastJson = null;
                    try {
                        forecastJson = new JSONObject(forecastData);
                        JSONObject properties = forecastJson.getJSONObject("properties");
                        forecastUrl = properties.getString("forecast");
                        Log.d("demo", "onResponse: " + "checking forecasturl" + forecastUrl);
                        if (forecastUrl != null) {
                            Log.d("demo", "onViewCreated: forecast url: " + forecastUrl);
                            Request forecastRequest = new Request.Builder()
                                    .url(forecastUrl)
                                    .build();

                            client.newCall(forecastRequest).enqueue(new okhttp3.Callback() {
                                @Override
                                public void onFailure(okhttp3.Call call, IOException e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                                    if (response.isSuccessful()) {
                                        String forecastData = response.body().string();
                                        Log.d("demo", "onResponse: forecast data: " + forecastData);
                                        try {
                                            JSONObject forecastJson = new JSONObject(forecastData);
                                            JSONObject properties = forecastJson.getJSONObject("properties");
                                            JSONArray periods = properties.getJSONArray("periods");

                                            for (int i = 0; i < periods.length(); i++) {
                                                Forecast forecast = new Forecast();
                                                JSONObject period = periods.getJSONObject(i);
                                                forecast.setStartTime(period.getString("startTime"));
                                                forecast.setTemperature(period.getString("temperature"));
                                                forecast.setShortForecast(period.getString("shortForecast"));
                                                forecast.setWindSpeed(period.getString("windSpeed"));
                                                forecast.setIcon(period.getString("icon"));
                                                JSONObject humidityObj = period.getJSONObject("relativeHumidity");
                                                forecast.setHumidity(humidityObj.getString("value"));
                                                iconUrl = forecast.getIcon();
                                                Log.d("demo", "onResponse: forecast: " + period.getString("name"));
                                                forecastList.add(forecast);
                                                Log.d("demo", "Forecast list size: "+forecastList.size());
                                            }
                                            Log.d("demo", "onResponse: "+getActivity());
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Log.d("demo", "run: ");
                                                    recyclerView = binding.recyclerView;
                                                    recyclerView.setHasFixedSize(true);
                                                    layoutManager = new LinearLayoutManager(getContext());
                                                    recyclerView.setLayoutManager(layoutManager);
                                                    Log.d("demo", "run: recycler view: "+recyclerView.getId());
                                                    adapter = new WeatherForecastRecyclerViewAdapter(forecastList);
                                                    Log.d("demo", "adapter: "+adapter.getItemCount());
                                                    recyclerView.setAdapter(adapter);
                                                    Log.d("demo", "adapter sett: ");
                                                }
                                            });

                                        } catch (JSONException e) {
                                            Log.d("demo", "onResponse:  In catch forecast data deserializing");
                                            throw new RuntimeException(e);
                                        }

                                    }
                                }
                            });

                        }
                    } catch (JSONException e) {
                        Log.d("demo", "onResponse: In catch property deserializing");
                        throw new RuntimeException(e);
                    }
                }
            }

        });
        Log.d("demo", "onViewCreated: after alllll");

    }
}