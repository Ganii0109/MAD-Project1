package com.example.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectLivingStatus extends AppCompatActivity {

    static public final String LIVING_VALUE="LivingValue";

    RadioGroup radioGrouplstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_living_status);
        radioGrouplstatus= findViewById(R.id.radioGroupLivingStatus);
        findViewById(R.id.buttonLivingStatusCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.buttonLivingStatusSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String lstatus = ((RadioButton)findViewById(radioGrouplstatus.getCheckedRadioButtonId())).getText().toString();
                    Intent intent=new Intent();
                    intent.putExtra(LIVING_VALUE,lstatus);
                    setResult(RESULT_OK,intent);
                    finish();

                }
                catch (Exception e)
                {
                    Toast.makeText(SelectLivingStatus.this,"Please Select The Living Status",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}