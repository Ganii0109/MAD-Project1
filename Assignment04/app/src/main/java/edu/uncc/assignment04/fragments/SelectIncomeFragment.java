package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.content.Intent;
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
import android.widget.SeekBar;
import android.widget.Toast;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.databinding.FragmentSelectIncomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectIncomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectIncomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectIncomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectIncomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectIncomeFragment newInstance(String param1, String param2) {
        SelectIncomeFragment fragment = new SelectIncomeFragment();
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

    FragmentSelectIncomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSelectIncomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    String selectedIncome;

    SeekBar seekBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilistener.cancelBtnIncome();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    seekBar=binding.seekBar;

                    int seekBarValue=seekBar.getProgress();
                    Log.d("demo", "fragment: "+seekBarValue);
                    if(seekBarValue<25)
                    {
                        selectedIncome="$25K";
                        ilistener.SubmitBtnIncome(selectedIncome);


                    }
                    else if(seekBarValue==25 || seekBarValue<50)
                    {
                        selectedIncome="$25K to $50K";
                        ilistener.SubmitBtnIncome(selectedIncome);

                    }
                    else if(seekBarValue==50 ||seekBarValue<100)
                    {
                        selectedIncome="$50K to $100K";
                        ilistener.SubmitBtnIncome(selectedIncome);
                    }
                    else if(seekBarValue==100 || seekBarValue<200)
                    {
                        selectedIncome="$100K to $200K";
                        ilistener.SubmitBtnIncome(selectedIncome);

                    }
                    if(seekBarValue>200)
                    {
                        selectedIncome="$200K";
                        ilistener.SubmitBtnIncome(selectedIncome);

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),"Please Select The HouseHoldIncome",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    Iincomefragment ilistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ilistener= (Iincomefragment) context;
    }



    public interface Iincomefragment{
        void cancelBtnIncome();
        void SubmitBtnIncome(String selectedIncome);
    }
}