package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.databinding.FragmentSleepQualityBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SleepQualityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SleepQualityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RadioGroup radioGroup;

    int selectedRadioButtonId;

    RadioButton radioButton;

    String selectedSleepQuality;

    public SleepQualityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SleepQualityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SleepQualityFragment newInstance(String param1, String param2) {
        SleepQualityFragment fragment = new SleepQualityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentSleepQualityBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSleepQualityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      binding.buttonSubmitSleepQuality.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              radioGroup = binding.radioGroupSleepQuality;
              selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
              try{
                 if(selectedRadioButtonId == -1){
                     Toast.makeText(getContext(), "Please select a sleep quality", Toast.LENGTH_SHORT).show();
                    }else{
                        radioButton = radioGroup.findViewById(selectedRadioButtonId);
                        selectedSleepQuality = radioButton.getText().toString();
                        mlistener.sendSelectedSleepQuality(selectedSleepQuality);
                 }

              }catch (Exception e){
                  Log.d("demo", "onClick: "+e);
              }


          }
      });


    }
    ISleepQualityFragment mlistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mlistener = (ISleepQualityFragment) context;
    }

    interface ISleepQualityFragment{
        void sendSelectedSleepQuality(String sleepQuality);
    }
}