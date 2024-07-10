package edu.uncc.assignment06;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.uncc.assignment06.models.Task;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.UserViewHolder>{

    ArrayList<Task> mTasks;

    ITaskListener mListener;

    public TaskRecyclerViewAdapter(ArrayList<Task> mTasks, ITaskListener mListener) {
        this.mTasks = mTasks;
        Log.d("demo", "TaskRecyclerViewAdapter: "+mTasks.toString());
        this.mListener = mListener;

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view, mListener);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Task task = mTasks.get(position);
        Log.d("demo", "onBindViewHolder: "+task.toString()+" "+task.getName()+" "+task.getCategory()+" "+task.getPriority());
        Log.d("demo", "onBindViewHolder: "+holder.textViewName.getText().toString());
        holder.textViewName.setText(task.getName());
        holder.textViewCategory.setText(task.getCategory());
        holder.textViewPriority.setText(task.getPriorityStr());
        holder.task = task;
        Log.d("demo", "onBindViewHolder: "+task.getName());
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewCategory;
        TextView textViewPriority;
        Task task;
        int position;

        ITaskListener mListener;

        public UserViewHolder(@NonNull View itemView, ITaskListener mListener) {
            super(itemView);
            this.mListener = mListener;
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewPriority = itemView.findViewById(R.id.textViewPriority);
            Log.d("demo", "UserViewHolder: "+textViewName.getText().toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Log.d("demo", "onClick: " + task.toString());
                        if(mListener!=null) {
                            mListener.goToTaskDetailsFragment(task);
                        }
                    }catch (Exception e) {
                        Log.d("demo", "onClick: " + e.toString());
                    }
                }
            });
            itemView.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.deleteTask(task);
                }
            });
        }
    }

    public interface ITaskListener{
        void deleteTask(Task task);

        void goToTaskDetailsFragment(Task task);
    }

}
