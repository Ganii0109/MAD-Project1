package edu.uncc.assignment06.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.assignment06.R;
import edu.uncc.assignment06.SelectionRecyclerViewAdapter;
import edu.uncc.assignment06.databinding.FragmentSelectCategoryBinding;
import edu.uncc.assignment06.models.Data;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectCategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String[] categories = Data.categories;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    public SelectCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectCategoryFragment newInstance(String param1, String param2) {
        SelectCategoryFragment fragment = new SelectCategoryFragment();
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
    FragmentSelectCategoryBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSelectCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    SelectionRecyclerViewAdapter.ISelectionListener mListener;
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        recyclerView = binding.recyclerView;
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        SelectionRecyclerViewAdapter adapter = new SelectionRecyclerViewAdapter(categories, mListener,"category");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectionRecyclerViewAdapter.ISelectionListener) {
            mListener = (SelectionRecyclerViewAdapter.ISelectionListener) context;
        }
    }
}