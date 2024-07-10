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
import edu.uncc.assignment04.databinding.FragmentProfileBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_USERDATAMODIFIED = "USERDATA";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private User userfinaldata;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USERDATAMODIFIED, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userfinaldata= (User) getArguments().getSerializable(ARG_USERDATAMODIFIED);

        }
    }

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textViewName.setText(userfinaldata.getName());
        binding.textViewEmail.setText(userfinaldata.getEmail());
        binding.textViewEdu.setText(userfinaldata.getEducation());
        binding.textViewMaritalStatus.setText(userfinaldata.getMstatus());
        binding.textViewLivingStatus.setText(userfinaldata.getLstatus());
        binding.textViewIncomeValue.setText(userfinaldata.getIncome());
        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilistener.goToIdentificationfromProfile();
            }
        });
    }
    Iprofilefragment ilistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ilistener= (Iprofilefragment) context;
    }

    public interface Iprofilefragment{
        void goToIdentificationfromProfile();
    }
}