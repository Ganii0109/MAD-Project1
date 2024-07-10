package edu.uncc.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import edu.uncc.weatherapp.fragments.CitiesFragment;
import edu.uncc.weatherapp.fragments.WeatherForecastFragment;
import edu.uncc.weatherapp.models.City;

public class MainActivity extends AppCompatActivity implements CitiesFragment.ICityFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CitiesFragment())
                .commit();

    }

    @Override
    public void sendSelectedCity(City city) {
        Log.d("demo", "Selected city in main: " + city.getName());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, WeatherForecastFragment.newInstance(city))
                .addToBackStack(null)
                .commit();
    }
}