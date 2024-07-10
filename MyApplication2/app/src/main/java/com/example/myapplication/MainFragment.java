package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.FragmentMainBinding;
import com.example.myapplication.databinding.LogEntryItemBinding;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
    ArrayList<LogEntry> logEntries = new ArrayList<>();

    AppDatabase db;

    FragmentMainBinding binding;

    LogEntryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new LogEntryAdapter();
        binding.recyclerViewLogs.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewLogs.setAdapter(adapter);
        binding.btnAddLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddLog();
            }
        });
        binding.btnVisualize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoVisualization();
            }
        });

        db= Room.databaseBuilder(getActivity(),AppDatabase.class,"health-db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        logEntries.clear();
        Log.d("demo", "onViewCreated: "+db.logEntryDao().getAll());
        logEntries.addAll(db.logEntryDao().getAll());
        adapter.notifyDataSetChanged();


    }

    class LogEntryAdapter extends RecyclerView.Adapter<LogEntryAdapter.LogEntryViewHolder>{

      @NonNull
      @Override
      public LogEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          LogEntryItemBinding rowbinding = LogEntryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
          return new LogEntryViewHolder(rowbinding);
      }

      @Override
      public void onBindViewHolder(@NonNull LogEntryViewHolder holder, int position) {
          LogEntry logEntry = logEntries.get(position);
          holder.setupUI(logEntry);



      }

      @Override
      public int getItemCount() {
          return logEntries.size();
      }

      class LogEntryViewHolder extends RecyclerView.ViewHolder{

          LogEntryItemBinding mBinding;

          LogEntry mLogEntry;
            public LogEntryViewHolder(@NonNull LogEntryItemBinding rowbinding) {
                super(rowbinding.getRoot());
                mBinding = rowbinding;
            }
            public void setupUI(LogEntry logEntry){
                mLogEntry = logEntry;
                mBinding.textViewDateTime.setText(logEntry.getDateTime());
                //convert the logEntry.getSleepHours() to a string
                mBinding.textViewSleepHours.setText(logEntry.getSleepHours()+"");
                mBinding.textViewSleepQuality.setText(logEntry.getSleepQuality());
                mBinding.textViewExerciseHours.setText(logEntry.getExerciseHours()+"");
                mBinding.textViewWeight.setText(logEntry.getWeight()+"");
                mBinding.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.logEntryDao().delete(mLogEntry);
                        logEntries.clear();
                        logEntries.addAll(db.logEntryDao().getAll());
                        adapter.notifyDataSetChanged();
                    }
                });


            }

        }

  }
  MainFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MainFragmentListener) {
            mListener = (MainFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement MainFragmentListener");
        }

    }

    interface MainFragmentListener{
      void gotoAddLog();

      void gotoVisualization();
  }


}