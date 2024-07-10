package edu.uncc.assignment06;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.uncc.assignment06.fragments.AddTaskFragment;
import edu.uncc.assignment06.fragments.SelectCategoryFragment;
import edu.uncc.assignment06.fragments.SelectPriorityFragment;
import edu.uncc.assignment06.fragments.TaskDetailsFragment;
import edu.uncc.assignment06.fragments.TasksFragment;
import edu.uncc.assignment06.models.Data;
import edu.uncc.assignment06.models.Task;

public class MainActivity extends AppCompatActivity implements TasksFragment.ITasksListener, AddTaskFragment.IAddTaskListener, SelectionRecyclerViewAdapter.ISelectionListener, TaskDetailsFragment.ITaskDetailsFragment, TaskRecyclerViewAdapter.ITaskListener {

    private ArrayList<Task> mTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView,new TasksFragment(),"TasksFragment")
                .addToBackStack(null)
                .commit();
        mTasks.addAll(Data.sampleTestTasks); //adding for testing
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return mTasks;
    }

    @Override
    public void gotoAddTaskFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new AddTaskFragment(),"AddTaskFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sortTasks(boolean isAscending) {
        if(isAscending) {
            Collections.sort(mTasks, Comparator.comparing(Task::getPriority));
        }
        else
        {
            Collections.sort(mTasks, Comparator.comparing(Task::getPriority).reversed());
        }
    }

    @Override
    public void clearAllTasks() {
        mTasks.clear();
    }

    @Override
    public void goToSelectCategoryFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SelectCategoryFragment(),"SelectCategoryFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectPriorityFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SelectPriorityFragment(),"SelectPriorityFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void addTask(Task task) {
        mTasks.add(task);
        Log.d("demo", "addTask: "+task.toString());
        getSupportFragmentManager().popBackStack();
    }


    @Override
    public void sendSelectedPriority(String selectedValue) {
        Log.d("demo", "Enter: "+selectedValue);
        AddTaskFragment addTaskFragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag("AddTaskFragment");
        Log.d("demo", "sendPriority: in Main Activity "+selectedValue);
        if(addTaskFragment != null){
            addTaskFragment.setSelectedPriority(selectedValue);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void sendSelectedCategory(String selectedValue) {
        Log.d("demo", "Enter: "+selectedValue);
        AddTaskFragment addTaskFragment = (AddTaskFragment) getSupportFragmentManager().findFragmentByTag("AddTaskFragment");
        Log.d("demo", "sendCategory: in Main Activity "+selectedValue);
        if(addTaskFragment != null){
            addTaskFragment.setSelectedCategory(selectedValue);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void goToTasksFragment() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void deleteTaskFromTaskDetails(Task task) {
        mTasks.remove(task);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void deleteTask(Task task) {
        mTasks.remove(task);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new TasksFragment(),"TasksFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToTaskDetailsFragment(Task task) {
        Log.d("demo", "goToTaskDetailsFragment: "+task.toString());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, TaskDetailsFragment.newInstance(task),"TaskDetailsFragment")
                .addToBackStack(null)
                .commit();
    }



   /* @Override
    public void deleteTask(Task task) {
        mTasks.remove(task);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new TasksFragment(),"TasksFragment")
                .addToBackStack(null)
                .commit();
    }*/
}