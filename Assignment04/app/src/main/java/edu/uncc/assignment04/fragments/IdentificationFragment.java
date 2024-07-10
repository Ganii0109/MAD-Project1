package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.User;
import edu.uncc.assignment04.databinding.FragmentIdentificationBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IdentificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdentificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IdentificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IdentificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IdentificationFragment newInstance(String param1, String param2) {
        IdentificationFragment fragment = new IdentificationFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentIdentificationBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    String name,email,role;
    EditText editTextname,editTextemail;
    RadioButton radioButton;
    FragmentIdentificationBinding binding;

    User user;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    editTextname=binding.editTextName;
                    editTextemail=binding.editTextEmail;
                    name=editTextname.getText().toString();
                    email=editTextemail.getText().toString();
                    int checkedid = binding.radioGroup.getCheckedRadioButtonId();

                    if (name.isEmpty()) {
                        Toast.makeText(getActivity(), "Please Enter the name", Toast.LENGTH_LONG).show();
                    } else if (email.isEmpty()) {
                        Toast.makeText(getActivity(), "Please Enter the Email", Toast.LENGTH_LONG).show();
                    } else if (checkedid == -1) {
                        Toast.makeText(getActivity(), "Please Select the role", Toast.LENGTH_LONG).show();
                    } else {
                        radioButton = binding.radioGroup.findViewById(checkedid);
                        role = radioButton.getText().toString();
                        user = new User(name, email, role, null, null, null, null);
                        ilistner.goToDemographicFragment();
                        ilistner.sendData(user);


                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(getActivity(), "Please Select the role", Toast.LENGTH_LONG).show();

                }
            }
                });

    }
    Iholdingdata ilistner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ilistner= (Iholdingdata) context;
    }

    public interface Iholdingdata{
        void sendData(User user);
        void goToDemographicFragment();
    }

}