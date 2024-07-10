package edu.uncc.weatherapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.uncc.weatherapp.R;
import edu.uncc.weatherapp.databinding.FragmentCitiesBinding;
import edu.uncc.weatherapp.models.City;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CitiesFragment extends Fragment {

    ListView listView;

    ArrayAdapter<String> adapter;

    ArrayList<City> cityObjArray = new ArrayList<City>();

    public CitiesFragment() {
        // Required empty public constructor
    }

    private final OkHttpClient client = new OkHttpClient();
    FragmentCitiesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCitiesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/api/cities")
                .build();
           client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String firstApiResponse = response.body().string();
                        Log.d("demo", firstApiResponse);

                        try {
                            JSONObject fullResponse = new JSONObject(firstApiResponse);

                            JSONArray cities = fullResponse.getJSONArray("cities");
                            //HashMap<String,String> cityList = new HashMap<String,String>();
                            final String[] cityList = new String[cities.length()];

                            for (int i = 0; i < cities.length(); i++) {
                                City cityObj = new City();
                                JSONObject city = cities.getJSONObject(i);
                                cityObj.setName(city.getString("name"));
                                cityObj.setState(city.getString("state"));
                                cityObj.setLat(city.getDouble("lat"));
                                cityObj.setLng(city.getDouble("lng"));
                                cityObjArray.add(cityObj);
                                cityList[i] = cityObj.getName()+" , "+cityObj.getState();
                                //Log.d("demo", city.getString("name"));
                                Log.d("demo", cityList[i]);
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cityList);
                                    listView = binding.listView;
                                    listView.setAdapter(adapter);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            City city = cityObjArray.get(position);
                                            Log.d("demo", "Selected city in Fragment: " + city.getName());
                                            mListener.sendSelectedCity(city);
                                        }
                                    });
                                    }
                            });



                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }



                    }

                }
            });



    }

    ICityFragment mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
            mListener = (ICityFragment) context;
    }

    public interface ICityFragment{
        void sendSelectedCity(City selectedCity);
    }
}