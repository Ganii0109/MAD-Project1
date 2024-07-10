package com.example.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectEducationLevel extends AppCompatActivity {

    static public final String EDUCATION_VALUE="EducationValue";

    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_education_level);
        radioGroup=findViewById(R.id.radiogroupEducation);
        findViewById(R.id.buttonEducationCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.buttonEducationSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String education = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                    Intent intent =new Intent();
                    intent.putExtra(EDUCATION_VALUE,education);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(SelectEducationLevel.this,"Please Select The Education",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}