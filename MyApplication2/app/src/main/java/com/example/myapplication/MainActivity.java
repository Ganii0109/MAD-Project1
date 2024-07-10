package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener, AddLogFragment.IAddLogListener, SleepHoursFragment.ISleepHoursFragment, SleepQualityFragment.ISleepQualityFragment, ExerciseHoursFragment.IExerciseHoursFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView,new MainFragment(),"MainFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoAddLog() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new AddLogFragment(),"AddLogFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoVisualization() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new VisualizationFragment(),"VisualizationFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendSelectedsleepHours(double hours) {
        Log.d("demo", "sendSelectedsleepHours: "+hours);
        AddLogFragment addLogFragment = (AddLogFragment) getSupportFragmentManager().findFragmentByTag("AddLogFragment");
        if(addLogFragment != null){
            addLogFragment.setSelectedSleepHours(hours);
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void btnCancelSleepHours() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void goToSleepHoursFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SleepHoursFragment(),"SleepHoursFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSleepQualityFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SleepQualityFragment(),"SleepQualityFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToExerciseHoursFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new ExerciseHoursFragment(),"ExerciseHoursFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void doneAddingLog() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedSleepQuality(String sleepQuality) {
        Log.d("demo", "sendSelectedSleepQuality: "+sleepQuality);
        AddLogFragment addLogFragment = (AddLogFragment) getSupportFragmentManager().findFragmentByTag("AddLogFragment");
        if(addLogFragment != null){
            addLogFragment.setSelectedSleepQuality(sleepQuality);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void sendSelectedExerciseHrs(double hours) {
        Log.d("demo", "sendSelectedExerciseHrs: "+hours);
        AddLogFragment addLogFragment = (AddLogFragment) getSupportFragmentManager().findFragmentByTag("AddLogFragment");
        if(addLogFragment != null){
            addLogFragment.setSelectedExerciseHours(hours);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void gotoMain() {
        getSupportFragmentManager().popBackStack();

    }
}