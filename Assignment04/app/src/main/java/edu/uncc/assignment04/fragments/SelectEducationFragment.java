package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.databinding.FragmentSelectEducationBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectEducationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectEducationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectEducationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectEducationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectEducationFragment newInstance(String param1, String param2) {
        SelectEducationFragment fragment = new SelectEducationFragment();
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

    FragmentSelectEducationBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSelectEducationBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    //RadioGroup radioGroup;
    RadioButton radioButton;

    String selectedEdu;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilistner.cancelbtnselectedu();

            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int checkedId = binding.radioGroup.getCheckedRadioButtonId();

                        radioButton = binding.radioGroup.findViewById(checkedId);
                        selectedEdu = radioButton.getText().toString();
                        ilistner.submitbtnselectedu(selectedEdu);
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(getActivity(), "Please select the education Field", Toast.LENGTH_LONG).show();
                }



            }
        });

    }
    Iselecteducation ilistner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ilistner= (Iselecteducation) context;
    }

    public interface Iselecteducation
    {
        void cancelbtnselectedu();
        void submitbtnselectedu(String selectedEdu);
    }

}