package com.example.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectMaritalStatus extends AppCompatActivity {

    static public final String MARITAL_VALUE="MaritalValue";

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_marital_status);
        radioGroup= findViewById(R.id.radioGroupMarital);


        findViewById(R.id.buttonMaritalCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.buttonMaritalSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String mstatus = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                    Intent intent=new Intent();
                    intent.putExtra(MARITAL_VALUE,mstatus);
                    setResult(RESULT_OK,intent);
                    finish();

                }
                catch (Exception e)
                {
                    Toast.makeText(SelectMaritalStatus.this,"Please Select The Marital Status",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}