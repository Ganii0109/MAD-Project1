package edu.uncc.assignment06.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.uncc.assignment06.R;
import edu.uncc.assignment06.TaskRecyclerViewAdapter;
import edu.uncc.assignment06.databinding.FragmentTasksBinding;
import edu.uncc.assignment06.models.Task;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Task> mTasks = new ArrayList<>();

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    TaskRecyclerViewAdapter adapter;

    public TasksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TasksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TasksFragment newInstance(String param1, String param2) {
        TasksFragment fragment = new TasksFragment();
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
    FragmentTasksBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTasksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            recyclerView = binding.recyclerView;
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            mTasks = mListener.getAllTasks();
            listener=(TaskRecyclerViewAdapter.ITaskListener) getContext();
            Log.d("demo", "updated list" + mTasks.toString());
            if (mTasks.size() != 0) {
                Log.d("demo", "before adapter creation");
                Log.d("demo", "listener "+listener);
                adapter = new TaskRecyclerViewAdapter(mTasks,listener);
                recyclerView.setAdapter(adapter);
                Log.d("demo", "after adapter set ");
            }
        } catch (Exception e) {
            Log.d("demo", "onViewCreated: " + e.toString());
        }

        binding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddTaskFragment();
            }
        });

        binding.imageViewSortAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortTasks(true);
                binding.textViewSortIndicator.setText("Sort by Priority Ascending");
                adapter.notifyDataSetChanged();
            }
        });
        binding.imageViewSortDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sortTasks(false);
                binding.textViewSortIndicator.setText("Sort by Priority Descending");
                adapter.notifyDataSetChanged();
            }
        });

        binding.buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clearAllTasks();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ITasksListener) context;
    }
    TaskRecyclerViewAdapter.ITaskListener listener;
    ITasksListener mListener;
    //TODO: The interface for the TasksFragment
    public interface ITasksListener{
        ArrayList<Task> getAllTasks();
        void gotoAddTaskFragment();

        void sortTasks(boolean isAscending);
        //void gotoTaskDetails();
        void clearAllTasks();
    }
}