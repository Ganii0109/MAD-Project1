package com.example.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    IdentificationDetails details;

    TextView textViewName,textViewEmail, textViewRole,textViewIncome, textViewEducation, textViewMstatus, textViewLstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (getIntent() != null && getIntent().hasExtra(Demographic.KEY))
            details = (IdentificationDetails) getIntent().getSerializableExtra(Demographic.KEY);
        textViewName=findViewById(R.id.textViewNameValue);
        textViewEmail=findViewById(R.id.textViewEmailValue);
        textViewRole=findViewById(R.id.textViewRoleValue);
        textViewIncome=findViewById(R.id.textViewProfileIncomeValue);
        textViewEducation=findViewById(R.id.textViewProfileEducationValue);
        textViewMstatus=findViewById(R.id.textViewProfileMaritalStatusValue);
        textViewLstatus=findViewById(R.id.textViewProfileLivingStatusValue);
        textViewName.setText(details.getName());
        textViewEmail.setText(details.getEmail());
        textViewRole.setText(details.getRole());
        textViewIncome.setText(details.getIncome());
        textViewEducation.setText(details.getEducation());
        textViewMstatus.setText(details.getMaritalStatus());
        textViewLstatus.setText(details.getLivingStatus());



    }
}