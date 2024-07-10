package edu.uncc.assignment04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.assignment04.R;
import edu.uncc.assignment04.User;
import edu.uncc.assignment04.databinding.FragmentDemographicBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemographicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemographicFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USERDATA = "USERDATA";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private User user;

    private String selectedEdu;

    private String selectedMstatus;

    private String selectedLstatus;

    private String selectedIncome;

    public void setSelectedIncome(String selectedIncome) {
        this.selectedIncome = selectedIncome;
    }

    public void setSelectedLstatus(String selectedLstatus) {
        this.selectedLstatus = selectedLstatus;
    }

    public void setSelectedMstatus(String selectedMstatus) {
        this.selectedMstatus = selectedMstatus;
    }

    public void setSelectedEdu(String selectedEdu) {
        this.selectedEdu = selectedEdu;
    }



    public DemographicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DemographicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DemographicFragment newInstance(User user) {
        DemographicFragment fragment = new DemographicFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USERDATA, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(ARG_USERDATA);
        }
    }
    FragmentDemographicBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding=FragmentDemographicBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(selectedEdu == null)
        {
            binding.textViewEducation.setText("N/A");
        }
        else {
            binding.textViewEducation.setText(selectedEdu);

        }
        if(selectedMstatus == null)
        {
            binding.textViewMaritalStatus.setText("N/A");
        }
        else {
            binding.textViewMaritalStatus.setText(selectedMstatus);

        }
        if(selectedLstatus == null)
        {
            binding.textViewLivingStatus.setText("N/A");
        }
        else {
            binding.textViewLivingStatus.setText(selectedLstatus);

        }
        if(selectedIncome==null)
        {
            binding.textViewIncomeStatus.setText("N/A");
        }
        else{
            binding.textViewIncomeStatus.setText(selectedIncome);

        }


        binding.buttonSelectEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilistener.gotoeducationfragment();
            }
        });

        binding.buttonSelectMarital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilistener.gotoMaritalStatus();
            }
        });

        binding.buttonSelectLiving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilistener.gotoLivingStatus();
            }
        });
        binding.buttonSelectIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilistener.gotoIncomefragment();
            }
        });
        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setEducation(selectedEdu);
                user.setMstatus(selectedMstatus);
                user.setLstatus(selectedLstatus);
                user.setIncome(selectedIncome);
                ilistener.sendDatatoProfile(user);
            }
        });

    }

    Idemographicfragment ilistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        ilistener= (Idemographicfragment) context;
    }

    public interface Idemographicfragment{
        void gotoeducationfragment();
        void gotoMaritalStatus();

        void gotoLivingStatus();
        void gotoIncomefragment();

        void sendDatatoProfile(User user);
    }


}