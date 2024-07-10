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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.uncc.assignment05.R;
import edu.uncc.assignment05.databinding.FragmentUsersBinding;
import edu.uncc.assignment05.models.User;
import edu.uncc.assignment05.models.UserAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    ArrayList<User> userlist = new ArrayList();

    //private ArrayList<User> userslist = new ArrayList<>();



    ListView listView;

    //ArrayAdapter<User> adapter;

    UserAdapter adapter;




    public UsersFragment() {
        // Required empty public constructor
    }


    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void setUserslist(ArrayList<User> userlist) {
        this.userlist = userlist;
        /*try {

            if (userlist.isEmpty()) {


            } else {
                listView = binding.listView;
                adapter = new UserAdapter(getActivity(), R.layout.user_list_item, userlist);
                listView.setAdapter(adapter);
            }
        }catch (Exception e)
        {

        }*/

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    FragmentUsersBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {

            if (userlist.isEmpty()) {


            } else {
                listView = binding.listView;
                adapter = new UserAdapter(this.getContext(),R.layout.user_list_item,userlist);
                listView.setAdapter(adapter);
            }
        }catch (Exception e)
        {

        }
        Log.d("Demo", "Final userList: in USER FRAGMENT onViewCreated "+userlist);


        binding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToAddUserFragment();
            }
        });
        Log.d("demo", "onViewCreated: ADDED");
    }
    IUsersFragmentInterface mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (IUsersFragmentInterface)context;
    }
    public interface IUsersFragmentInterface
    {
        void goToAddUserFragment();

    }
}