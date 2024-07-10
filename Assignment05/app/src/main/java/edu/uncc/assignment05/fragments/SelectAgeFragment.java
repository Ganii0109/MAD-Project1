package edu.uncc.assignment05.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.assignment05.R;
import edu.uncc.assignment05.databinding.FragmentSelectAgeBinding;




public class SelectAgeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView listView;

    ArrayList<Integer> age = new ArrayList<>();

     int n=18;

    ArrayAdapter<Integer> adapter;

    int selectedAge;

    public SelectAgeFragment() {
        // Required empty public constructor
    }






    FragmentSelectAgeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSelectAgeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for(int i=0;n!=101;i++)
        {
            age.add(n);
            n++;
        }
        listView = binding.listView;
        adapter = new ArrayAdapter<Integer>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,age);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedAge = age.get(position);
                mlistener.sendSelectedAge(selectedAge);
            }
        });
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.btnCancelAge();

            }
        });
    }
    ISelectAgeFragment mlistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mlistener= (ISelectAgeFragment) context;
    }

    public interface ISelectAgeFragment
    {
        void btnCancelAge();

        void sendSelectedAge(int selectedAge);
    }

}