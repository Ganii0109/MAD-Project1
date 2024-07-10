package com.example.assignment03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Identification extends AppCompatActivity implements Serializable {
        final static String USER_NAME="USER_NAME";
        final static String USER_EMAIL="USER_EMAIL";
        final static String USER_ROLE="USER_ROLE";

        EditText editTextName,editTextEmail;

        RadioButton radioButton;

        RadioGroup radioGroup;

        String name, email, role;
    IdentificationDetails infodetails;
    final static String USER_INFO = "USER_INFO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        radioGroup = findViewById(R.id.radioGroup);
        findViewById(R.id.identificationextbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Identification.this, Demographic.class);
                    editTextName = findViewById(R.id.editTextName);
                    name = editTextName.getText().toString();
                    editTextEmail = findViewById(R.id.editTextEmail);
                    email = editTextEmail.getText().toString();
                    int id = radioGroup.getCheckedRadioButtonId();
                    if (name.isEmpty())
                    {
                        Toast.makeText(Identification.this, "Please enter Name", Toast.LENGTH_LONG).show();

                    }
                    else if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
                    {
                        Toast.makeText(Identification.this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
                    } else if (id == -1) {
                        Toast.makeText(Identification.this, "Please select your role", Toast.LENGTH_LONG).show();

                    } else {
                        infodetails = new IdentificationDetails(name, email, role, null, null, null, null);
                        intent.putExtra(USER_INFO, infodetails);
                        startActivity(intent);
                    }
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(Identification.this,"Please fill all the details!!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}