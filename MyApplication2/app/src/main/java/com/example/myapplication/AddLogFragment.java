package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.myapplication.databinding.FragmentAddLogBinding;

import java.util.Calendar;

public class AddLogFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Double selectedSleepHours;

    private String selectedSleepQuality;

    private Double selectedExerciseHours;

    TextView textViewDateTime;
    Button btnPickDateTime;
    Calendar calendar;

    EditText editTextWeight;

    String weight,dateTime;

    AppDatabase db;

    private boolean dateTimeSelected = false;

    public AddLogFragment() {
        // Required empty public constructor
    }

    public static AddLogFragment newInstance(String param1, String param2) {
        AddLogFragment fragment = new AddLogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    FragmentAddLogBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddLogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewDateTime = binding.textViewDateTime;
        btnPickDateTime = binding.btnPickDateTime;
        calendar = Calendar.getInstance();
        editTextWeight = binding.editTextWeight;

        weight = editTextWeight.getText().toString();




        btnPickDateTime.setOnClickListener(v -> showDateTimeDialog());

        binding.buttonSleepHours.setOnClickListener(v -> mListener.goToSleepHoursFragment());

        binding.buttonSleepQuality.setOnClickListener(v -> mListener.goToSleepQualityFragment());

        binding.buttonExerciseHours.setOnClickListener(v -> mListener.goToExerciseHoursFragment());

        if(selectedSleepHours != null) {
            binding.textViewSleepHours.setText(selectedSleepHours.toString());
        }

        if(selectedSleepQuality == null) {

        }
        else {
            binding.textViewSleepQuality.setText(selectedSleepQuality);
        }

        if(selectedExerciseHours != null) {
                binding.textViewExerciseHours.setText(selectedExerciseHours.toString());
        }


        binding.buttonSubmit.setOnClickListener(v -> {
            if (textViewDateTime.getText().toString().isEmpty() || !dateTimeSelected) {
                Toast.makeText(getContext(), "Please select a date and time.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(binding.textViewSleepHours.getText().toString().equals("N/A")) {
                Toast.makeText(getContext(), "Please select a sleep hours.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(binding.textViewSleepQuality.getText().toString().equals("N/A")) {
                Toast.makeText(getContext(), "Please select a sleep quality.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(binding.textViewExerciseHours.getText().toString().equals("N/A")) {
                Toast.makeText(getContext(), "Please select an exercise hours.", Toast.LENGTH_SHORT).show();
                return;
            }
            else if(editTextWeight.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Please enter a weight.", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Log.d("demo", "onViewCreated: "+"Date/Time "+dateTime+" Sleep Hours "+selectedSleepHours+" Sleep Quality "+selectedSleepQuality+" Exercise Hours "+selectedExerciseHours+" Weight "+editTextWeight.getText().toString());

                db= Room.databaseBuilder(getActivity(),AppDatabase.class,"health-db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();


                LogEntry logEntry=new LogEntry(dateTime,selectedSleepHours,selectedSleepQuality,selectedExerciseHours,Double.parseDouble(editTextWeight.getText().toString()));
                db.logEntryDao().insertAll(logEntry);
                mListener.doneAddingLog();

            }

        });
    }

    private void showDateTimeDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    // Continue with showing the time picker
                    TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                            (timeView, hourOfDay, minute) -> {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                dateTimeSelected = true;  // Date/time has been successfully set
                                updateDateTimeDisplay();
                            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                    timePickerDialog.show();
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void updateDateTimeDisplay() {
        String formattedDateTime = android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", calendar).toString();
        dateTime = formattedDateTime;
        textViewDateTime.setText(formattedDateTime);
    }

    public void setSelectedSleepHours(Double selectedSleepHours) {
        this.selectedSleepHours = selectedSleepHours;
    }

    public void setSelectedSleepQuality(String selectedSleepQuality) {
        this.selectedSleepQuality = selectedSleepQuality;
    }

    public void setSelectedExerciseHours(Double selectedExerciseHours) {
        this.selectedExerciseHours = selectedExerciseHours;
    }

    interface IAddLogListener {
        void goToSleepHoursFragment();
        void goToSleepQualityFragment();

        void goToExerciseHoursFragment();

        void doneAddingLog();
    }

    IAddLogListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (IAddLogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement IAddLogListener");
        }
    }
}
