package com.example.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class HouseholdIncome extends AppCompatActivity {


    static public final String HOUSEHOLD_VALUE="HouseHoldValue";

    SeekBar seekBar;

    String Householdincomevalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_income);
        seekBar=findViewById(R.id.seekBar);

        findViewById(R.id.buttonHouseholdCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.buttonHouseholdsubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int seekBarValue=seekBar.getProgress();
                    if(seekBarValue<25)
                    {
                        Householdincomevalue="$25K";
                        Intent intent=new Intent();
                        intent.putExtra(HOUSEHOLD_VALUE,Householdincomevalue);
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                    if(seekBarValue==25 || seekBarValue<50)
                    {
                        Householdincomevalue="$25K to $50K";
                        Intent intent=new Intent();
                        intent.putExtra(HOUSEHOLD_VALUE,Householdincomevalue);
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                    if(seekBarValue==50 ||seekBarValue<100)
                    {
                        Householdincomevalue="$50K to $100K";
                        Intent intent=new Intent();
                        intent.putExtra(HOUSEHOLD_VALUE,Householdincomevalue);
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                    if(seekBarValue==100 && seekBarValue<200)
                    {
                        Householdincomevalue="$100K to $200K";
                        Intent intent=new Intent();
                        intent.putExtra(HOUSEHOLD_VALUE,Householdincomevalue);
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                    if(seekBarValue>200)
                    {
                        Householdincomevalue="$200K";
                        Intent intent=new Intent();
                        intent.putExtra(HOUSEHOLD_VALUE,Householdincomevalue);
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(HouseholdIncome.this,"Please Select The HouseHoldIncome",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}