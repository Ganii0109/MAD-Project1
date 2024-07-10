package com.example.assignment03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Demographic extends AppCompatActivity {

    public static final String KEY="key";
    IdentificationDetails details;

    String education,marital,living,house;

    TextView textViewEducation, textViewMstatus, textViewLstatus, textViewHstatus;

    ActivityResultLauncher<Intent> educationresult= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK && result.getData() != null && result.getData() != null)
            {
                Intent data = result.getData();
                education= data.getStringExtra(SelectEducationLevel.EDUCATION_VALUE);
                details.setEducation(education);
                textViewEducation.setText(education);

            }
            else {

            }

        }
    });

    ActivityResultLauncher<Intent> maritalresult= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK && result.getData() != null && result.getData()!= null)
            {
                Intent data = result.getData();
                marital= data.getStringExtra(SelectMaritalStatus.MARITAL_VALUE);
                details.setMaritalStatus(marital);
                textViewMstatus.setText(marital);
            }


        }
    });

    ActivityResultLauncher<Intent> livingstatusresult= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK && result.getData() != null && result.getData()!= null)
            {
                Intent data = result.getData();
                living= data.getStringExtra(SelectLivingStatus.LIVING_VALUE);
                details.setLivingStatus(living);
                textViewLstatus.setText(living);
            }


        }
    });

    ActivityResultLauncher<Intent> HouseholdIncomeresult=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK && result.getData() != null && result.getData()!= null)
            {
                Intent data = result.getData();
                house= data.getStringExtra(HouseholdIncome.HOUSEHOLD_VALUE);
                details.setIncome(house);
                textViewHstatus.setText(house);
            }

        }
    });

    IdentificationDetails info ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demographic);

        if (getIntent() != null && getIntent().hasExtra(Identification.USER_INFO))
            details = (IdentificationDetails) getIntent().getSerializableExtra(Identification.USER_INFO);
            textViewEducation = findViewById(R.id.textViewEducationValue);
            textViewMstatus = findViewById(R.id.textViewMaritalValue);
            textViewLstatus = findViewById(R.id.textViewLivingValue);
            textViewHstatus = findViewById(R.id.textViewIncomeValue);

            findViewById(R.id.buttonEducation).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Demographic.this, SelectEducationLevel.class);
                    educationresult.launch(intent);

                }
            });

            findViewById(R.id.buttonMarital).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentM = new Intent(Demographic.this, SelectMaritalStatus.class);
                    maritalresult.launch(intentM);
                }
            });

            findViewById(R.id.buttonLiving).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentL = new Intent(Demographic.this, SelectLivingStatus.class);
                    livingstatusresult.launch(intentL);
                }
            });

            findViewById(R.id.buttonIncome).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentH = new Intent(Demographic.this, HouseholdIncome.class);
                    HouseholdIncomeresult.launch(intentH);
                }
            });

            findViewById(R.id.buttonDemographicNext).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentP = new Intent(Demographic.this, Profile.class);
                    intentP.putExtra(KEY,details);
                    startActivity(intentP);
                }
            });
        }
    }