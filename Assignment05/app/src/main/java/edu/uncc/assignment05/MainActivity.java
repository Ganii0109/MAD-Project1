package edu.uncc.assignment05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import edu.uncc.assignment05.fragments.AddUserFragment;
import edu.uncc.assignment05.fragments.SelectAgeFragment;
import edu.uncc.assignment05.fragments.SelectGenderFragment;
import edu.uncc.assignment05.fragments.SelectGroupFragment;
import edu.uncc.assignment05.fragments.SelectStateFragment;
import edu.uncc.assignment05.fragments.UsersFragment;
import edu.uncc.assignment05.models.User;

public class MainActivity extends AppCompatActivity implements UsersFragment.IUsersFragmentInterface, AddUserFragment.IAddUserFragment, SelectGenderFragment.ISelectGenderFragment, SelectAgeFragment.ISelectAgeFragment, SelectStateFragment.ISelectStateFragment, SelectGroupFragment.ISelectGroupFragment {

    ArrayList<User> mUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView,new UsersFragment(),"UsersFragment")
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void goToAddUserFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new AddUserFragment(),"AddUser-Fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectGenderFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SelectGenderFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectAgeFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SelectAgeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectStateFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SelectStateFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToSelectGroupFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SelectGroupFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendAddedUser(User newlyAddedUser) {
        Log.d("demo", "sendAddedUser: "+mUsers.size());
        mUsers.add(newlyAddedUser);
        Log.d("demo", "sendAddedUser: "+newlyAddedUser);
        //getSupportFragmentManager().popBackStack();

        UsersFragment fragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag("UsersFragment");
        if(fragment!=null)
        {
            fragment.setUserslist(mUsers);
        }
        getSupportFragmentManager().popBackStack();
        Log.d("demo", "Final userList: in Main Activity "+mUsers);
        Log.d("demo", "sendAddedUser: "+mUsers.size());

    }

    @Override
    public void btnCancelAge() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedAge(int selectedAge) {
        AddUserFragment fragment= (AddUserFragment) getSupportFragmentManager().findFragmentByTag("AddUser-Fragment");
        if(fragment!=null)
        {
            fragment.setSelectedAge(selectedAge);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void btnCancelGender() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedGender(String gender) {
        AddUserFragment fragment= (AddUserFragment) getSupportFragmentManager().findFragmentByTag("AddUser-Fragment");
        if(fragment!=null)
        {
            fragment.setSelectedGender(gender);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void btnCancelGrp() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedGroup(String selectedGroup) {
        AddUserFragment fragment= (AddUserFragment) getSupportFragmentManager().findFragmentByTag("AddUser-Fragment");
        if(fragment!=null)
        {
            fragment.setSelectedGroup(selectedGroup);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void btnCancelState() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedState(String selectedState) {
        AddUserFragment fragment= (AddUserFragment) getSupportFragmentManager().findFragmentByTag("AddUser-Fragment");
        if(fragment!=null)
        {
            fragment.setSelectedState(selectedState);
            getSupportFragmentManager().popBackStack();
        }
    }
}