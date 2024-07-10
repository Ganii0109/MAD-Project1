package edu.uncc.assignment05.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import edu.uncc.assignment05.R;
import edu.uncc.assignment05.databinding.FragmentAddUserBinding;
import edu.uncc.assignment05.models.User;


public class AddUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String selectedGender;

    private int selectedAge;

    private String selectedState;

    private String selectedGroup;

    String name,email;
    EditText editTextName, editTextEmail;

    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public void setSelectedGender(String selectedGender) {
        this.selectedGender = selectedGender;
    }

    public void setSelectedAge(int selectedAge) {
        this.selectedAge = selectedAge;
    }



    public AddUserFragment() {
        // Required empty public constructor
    }



    public static AddUserFragment newInstance(String param1, String param2) {
        AddUserFragment fragment = new AddUserFragment();
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

    FragmentAddUserBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentAddUserBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonSelectGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToSelectGenderFragment();

            }
        });
        binding.buttonSelectAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToSelectAgeFragment();

            }
        });
        binding.buttonSelectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToSelectStateFragment();

            }
        });
        binding.buttonSelectGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToSelectGroupFragment();

            }
        });
        if(selectedGender==null)
        {
            binding.textViewGender.setText("N/A");
        }
        else {
            binding.textViewGender.setText(selectedGender);
        }

        if(selectedAge<18 || selectedAge>100)
        {
            binding.textViewAge.setText("N/A");
        }
        else
        {
            binding.textViewAge.setText(String.valueOf(selectedAge));
        }

        if(selectedState==null)
        {
            binding.textViewState.setText("N/A");
        }
        else
        {
            binding.textViewState.setText(String.valueOf(selectedState));
        }

        if(selectedGroup==null)
        {
            binding.textViewGroup.setText("N/A");
        }
        else
        {
            binding.textViewGroup.setText(String.valueOf(selectedGroup));
        }

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextName=binding.editTextName;
                editTextEmail=binding.editTextEmail;
                name=editTextName.getText().toString();
                email=editTextEmail.getText().toString();

                if(name.isEmpty())
                {
                    Toast.makeText(getContext(), "Please enter a valid Name", Toast.LENGTH_LONG).show();
                }
                else if(email.isEmpty()){
                    Toast.makeText(getContext(), "Please enter a valid Email", Toast.LENGTH_LONG).show();
                }
                else if(binding.textViewGender.getText().equals("N/A"))
                {
                    Toast.makeText(getContext(), "Please select a valid Gender", Toast.LENGTH_LONG).show();
                }
                else if(binding.textViewAge.getText().equals("N/A"))
                {
                    Toast.makeText(getContext(), "Please select a valid Age", Toast.LENGTH_LONG).show();
                }
                else if(binding.textViewState.getText().equals("N/A"))
                {
                    Toast.makeText(getContext(), "Please select a valid State", Toast.LENGTH_LONG).show();
                }
                else if(binding.textViewGroup.getText().equals("N/A"))
                {
                    Toast.makeText(getContext(), "Please select a valid Group", Toast.LENGTH_LONG).show();
                }
                else
                {
                    User user = new User(name,email,selectedGender,selectedAge,selectedState,selectedGroup);
                    Log.d("demo", "onClick: "+user);
                    mListener.sendAddedUser(user);
                }
            }
        });


    }

    IAddUserFragment mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if ( context  instanceof IAddUserFragment) {
            mListener = (IAddUserFragment) context;
        }
    }



    public interface IAddUserFragment{
        void goToSelectGenderFragment();
        void goToSelectAgeFragment();
        void goToSelectStateFragment();
        void goToSelectGroupFragment();
        void sendAddedUser(User newlyAddedUser);
    }
}