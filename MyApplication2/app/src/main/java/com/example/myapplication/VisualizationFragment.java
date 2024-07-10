package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VisualizationFragment extends Fragment {

    private AppDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visualization, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ensure the context is available before setting up the database
        if (getContext() != null) {
            db = Room.databaseBuilder(getContext(), AppDatabase.class, "health-db").allowMainThreadQueries().build(); // Note: Using allowMainThreadQueries() is generally not recommended.
        }

        if (db == null) {
            return; // Early return if db is not initialized
        }


        ExecutorService executorService = Executors.newFixedThreadPool(4); // Creates a thread pool with 4 threads

        executorService.execute(() -> setupChart(view, R.id.chart_sleep_hours, "Sleep Hours", "sleepHours"));
        executorService.execute(() -> setupChart(view, R.id.chart_exercise_hours, "Exercise Hours", "exerciseHours"));
        executorService.execute(() -> setupChart(view, R.id.chart_sleep_quality, "Sleep Quality", "sleepQuality"));
        executorService.execute(() -> setupChart(view, R.id.chart_weight, "Weight", "weight"));

        executorService.shutdown(); // Initiates an orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted


    }

    private Double getSleepQualityByString(String sleepQuality) {

        if ( sleepQuality.equals("Excellent (5)") ){
            return 5.0;
        } else if ( sleepQuality.equals("Very Good (4)") ){
            return 4.0;
        } else if ( sleepQuality.equals("Good (3)") ){
            return 3.0;
        } else if ( sleepQuality.equals("Fair (2)") ){
            return 2.0;
        } else if ( sleepQuality.equals("Poor (1)") ){
            return 1.0;
        } else {
            return 0.0;
        }

    }

    private void setupChart(View view, int chartId, String title, String metricType) {
        AnyChartView anyChartView = view.findViewById(chartId);
        if (anyChartView == null || getActivity() == null) {
            Log.d("demo", "setupChart: anyChartView is null");
            return; // Early return if anyChartView is null or getActivity() returns null
        }

        getActivity().runOnUiThread(() -> {
            APIlib.getInstance().setActiveAnyChartView(anyChartView);
            List<LogEntry> logEntries = db.logEntryDao().getAll();
            List<DataEntry> seriesData = new ArrayList<>();
            Log.d("demo", "setupChart: "+logEntries.size()+" "+logEntries);
            for (LogEntry entry : logEntries) {
                double value = 0;

                if (entry != null) { // Check for null entries
                    switch (metricType) {
                        case "sleepHours":
                            value = entry.getSleepHours();
                            break;
                        case "exerciseHours":
                            value = entry.getExerciseHours();
                            break;
                        case "sleepQuality":
                            value = getSleepQualityByString( entry.getSleepQuality() );
                            break;
                        case "weight":
                            value = entry.getWeight();
                            break;
                    }
                    Log.d("demo", "setupChart: "+metricType+" "+value);
                }
                seriesData.add(new ValueDataEntry(entry.getDateTime(), value));
            }
            Log.d("demo", "setupChart: "+ seriesData);
//            Cartesian cartesian = AnyChart.line();
//            cartesian.animation(true);
//            cartesian.padding(10d, 20d, 5d, 20d);
//            cartesian.crosshair().enabled(true);
//            cartesian.crosshair().yLabel(true).yStroke((Stroke) null, (Number) null, (String) null, (String) null, (String) null);
//            cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
//            cartesian.title(title);
//            cartesian.yAxis(0).title("Value");
//            cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

            Cartesian cartesian = AnyChart.line();

            cartesian.animation(true);

            cartesian.padding(10d, 20d, 5d, 20d);

            cartesian.crosshair().enabled(true);
            cartesian.crosshair()
                    .yLabel(true)
                    // TODO ystroke
                    .yStroke((Stroke) null, null, null, (String) null, (String) null);

            cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

            cartesian.title("Trend of Sales of the Most Popular Products of ACME Corp.");

            cartesian.yAxis(0).title("Number of Bottles Sold (thousands)");
            cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

            Set set = Set.instantiate();
            set.data( seriesData);
            Mapping seriesMapping = set.mapAs("{ x: 'x', value: 'value' }");

            Line series = cartesian.line(seriesMapping);
            series.name(title);
            series.hovered().markers().enabled(true);
            series.hovered().markers().type(MarkerType.CIRCLE).size(4d);
            series.tooltip().position("right").anchor(Anchor.LEFT_CENTER).offsetX(5d).offsetY(5d);

            cartesian.legend().enabled(true);
            cartesian.legend().fontSize(13d);
            cartesian.legend().padding(0d, 0d, 10d, 0d);

            anyChartView.setChart(cartesian);
        });
    }
}