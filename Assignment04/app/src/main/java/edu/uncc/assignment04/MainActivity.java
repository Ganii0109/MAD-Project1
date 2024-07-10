package edu.uncc.assignment04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import edu.uncc.assignment04.fragments.DemographicFragment;
import edu.uncc.assignment04.fragments.IdentificationFragment;
import edu.uncc.assignment04.fragments.MainFragment;
import edu.uncc.assignment04.fragments.ProfileFragment;
import edu.uncc.assignment04.fragments.SelectEducationFragment;
import edu.uncc.assignment04.fragments.SelectIncomeFragment;
import edu.uncc.assignment04.fragments.SelectLivingStatusFragment;
import edu.uncc.assignment04.fragments.SelectMaritalStatusFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.IMainFragment, IdentificationFragment.Iholdingdata, DemographicFragment.Idemographicfragment, SelectEducationFragment.Iselecteducation, SelectMaritalStatusFragment.IselectedMstatus, SelectLivingStatusFragment.ILivingStatus, SelectIncomeFragment.Iincomefragment, ProfileFragment.Iprofilefragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new MainFragment())
                .commit();
    }

    @Override
    public void moveToIdentificationFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new IdentificationFragment(),"Identification-fragment")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void sendData(User user) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,DemographicFragment.newInstance(user),"Demographic-fragment")
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void goToDemographicFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new DemographicFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void gotoeducationfragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SelectEducationFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoMaritalStatus() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectMaritalStatusFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoLivingStatus() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectLivingStatusFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoIncomefragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new SelectIncomeFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void sendDatatoProfile(User user) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(user))
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void cancelbtnselectedu() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void submitbtnselectedu(String selectedEdu) {
        Log.d("demo", "submitbtnselectedu: "+selectedEdu);
        DemographicFragment fragment= (DemographicFragment) getSupportFragmentManager().findFragmentByTag("Demographic-fragment");
        if(fragment!=null)
        {
            fragment.setSelectedEdu(selectedEdu);
            getSupportFragmentManager().popBackStack();
        }





    }

    @Override
    public void cancelbtnMstatus() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void submitbtnMstatus(String selectedMstatus) {
        DemographicFragment fragment=(DemographicFragment) getSupportFragmentManager().findFragmentByTag("Demographic-fragment");
        if(fragment!=null)
        {
            fragment.setSelectedMstatus(selectedMstatus);
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void cancelBtnLStatus() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void submitBtnLStatus(String selectedLstatus) {
        DemographicFragment fragment=(DemographicFragment) getSupportFragmentManager().findFragmentByTag("Demographic-fragment");
        if(fragment!=null)
        {
            fragment.setSelectedLstatus(selectedLstatus);
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void cancelBtnIncome() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void SubmitBtnIncome(String selectedIncome) {
        DemographicFragment fragment=(DemographicFragment) getSupportFragmentManager().findFragmentByTag("Demographic-fragment");
        if(fragment!=null)
        {
            fragment.setSelectedIncome(selectedIncome);
            Log.d("demo", "SubmitBtnIncome: "+selectedIncome);
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void goToIdentificationfromProfile() {
        //IdentificationFragment fragment= (IdentificationFragment) getSupportFragmentManager().findFragmentByTag("Identification-fragment");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new IdentificationFragment())
                .commit();
    }
}