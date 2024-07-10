// Assignment1
// MainActivity.java
// Goda Kodati , Ganesh Vasa
// Group 8

package com.example.inclass01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textviewProgress, textViewDiscount, textViewFinalPrice;
    EditText editTextMain;
    RadioGroup radioGroup;

    String afterdiscountstr = "0.00";
    String finalpricestr = "0.00";
    double afterdiscount,finalprice,originalvalue,discountpercent=0.1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=findViewById(R.id.seekbar);
        textviewProgress=findViewById(R.id.textViewprogress);
        editTextMain=findViewById(R.id.price);
        textViewDiscount=findViewById(R.id.textViewdiscount);
        textViewFinalPrice=findViewById(R.id.textviewfinalprice);
        radioGroup=findViewById(R.id.radiogroup);
        seekBar.setProgress(25);
        textviewProgress.setText("25%");


        findViewById(R.id.resetbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextMain.setText("");
                textViewDiscount.setText("0.00");
                textViewFinalPrice.setText("0.00");
                seekBar.setProgress(25);
                radioGroup.check(R.id.radioButton10);

            }
        });


        findViewById(R.id.calculatebutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String input = editTextMain.getText().toString();

                    if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton10) {
                        discountpercent = (10 * 1.0) / 100;
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton15) {
                        discountpercent = (15 * 1.0) / 100;
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton18) {
                        discountpercent = (18 * 1.0) / 100;
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtoncustom) {
                        discountpercent = (seekBar.getProgress()*1.0)/100;
                    }


                    originalvalue = Double.parseDouble(editTextMain.getText().toString());
                    Log.d("original value", "onCheckedChanged: " + originalvalue);
                    afterdiscount = discountpercent * originalvalue;
                    Log.d("original value", "AD: " + afterdiscount);
                    afterdiscountstr = Double.toString(afterdiscount);
                    finalprice = originalvalue - afterdiscount;
                    Log.d("original value", "FP: " + finalprice);
                    finalpricestr = Double.toString(finalprice);
                    textViewDiscount.setText(afterdiscountstr);
                    textViewFinalPrice.setText(finalpricestr);
                }catch (NumberFormatException e){
                    textViewDiscount.setText("");
                    textViewFinalPrice.setText("");
                    Toast.makeText(MainActivity.this, "Enter Item Price", Toast.LENGTH_LONG).show();
                };


            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textviewProgress.setText(String.valueOf(progress)+"%") ;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textviewProgress.setText("");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}