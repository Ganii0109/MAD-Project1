package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewChooseCases;

    public int[] caseIds = {R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9,R.id.imageView10};
    public int[] closedCases = {R.drawable.suitcase_position_1,R.drawable.suitcase_position_2,R.drawable.suitcase_position_3,R.drawable.suitcase_position_4,R.drawable.suitcase_position_5,
            R.drawable.suitcase_position_6,R.drawable.suitcase_position_7,R.drawable.suitcase_position_8,R.drawable.suitcase_position_9,R.drawable.suitcase_position_10};

    public int[] openCases = {R.drawable.suitcase_open_1,R.drawable.suitcase_open_10,R.drawable.suitcase_open_50,R.drawable.suitcase_open_100,R.drawable.suitcase_open_300,R.drawable.suitcase_open_1000,R.drawable.suitcase_open_10000,R.drawable.suitcase_open_50000,R.drawable.suitcase_open_100000,R.drawable.suitcase_open_500000};

    public int[] rewardIds = {R.id.imageView11,R.id.imageView12,R.id.imageView17,R.id.imageView13
            ,R.id.imageView18,R.id.imageView14,R.id.imageView15
            ,R.id.imageView19,R.id.imageView16,R.id.imageView21};
    public int[] closedRewards = {R.drawable.reward_1,R.drawable.reward_10,R.drawable.reward_50,R.drawable.reward_100,R.drawable.reward_300,R.drawable.reward_1000,
            R.drawable.reward_10000,R.drawable.reward_50000,R.drawable.reward_100000,R.drawable.reward_500000};

    public int[] openRewards = {R.drawable.reward_open_1,R.drawable.reward_open_10,R.drawable.reward_open_50,R.drawable.reward_open_100,R.drawable.reward_open_300,R.drawable.reward_open_1000,R.drawable.reward_open_10000,R.drawable.reward_open_50000,R.drawable.reward_open_100000,R.drawable.reward_open_500000};


    public boolean [] isOpened = {false,false,false,false,false,false,false,false,false,false};
    int[] amounts = {1,10,50,100,300,1000,10000,50000,100000,500000};
    ArrayList<Integer> shuffledOpenCases = new ArrayList<>();


    int round;
    int casesOpened;
    int dealAmount = 0;
    int count = 0;
    int casesLeft = 5;
    boolean gameOver = false;
    boolean waiting = false;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewChooseCases = findViewById(R.id.textViewChooseCases);
        reset();
        
        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        findViewById(R.id.buttonDeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewChooseCases.setText("You won : $" + dealAmount );
                findViewById(R.id.buttonDeal).setVisibility(View.INVISIBLE);
                findViewById(R.id.buttonNodeal).setVisibility(View.INVISIBLE);
                gameOver = true;
                waiting = false;
            }
        });

        findViewById(R.id.buttonNodeal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                round++;
                findViewById(R.id.buttonDeal).setVisibility(View.INVISIBLE);
                findViewById(R.id.buttonNodeal).setVisibility(View.INVISIBLE);
                casesOpened = 0;
                if (round == 2) {
                    casesLeft = 4;
                }
                else {
                    casesLeft = 1;
                }
                int c = casesLeft - casesOpened;
                dealAmount = 0;
                count = 0;
                waiting = false;
                textViewChooseCases.setText("Choose " + c + " Cases");
            }
        });
    }

    public void reset() {
        gameOver = false;
        waiting = false;
        round = 1;
        casesOpened = 0;
        casesLeft = 4;
        int n = casesLeft - casesOpened;
        textViewChooseCases.setText("Choose " + n + " Cases");
        shuffledOpenCases.clear();
        for (int i = 0; i < 10; i++) {
            shuffledOpenCases.add(i);
            isOpened[i] = false;
        }
        Collections.shuffle(shuffledOpenCases);
        for(int i=0;i<10;i++) {
            ImageView boxImg = findViewById(caseIds[i]);
            boxImg.setOnClickListener(this);
            boxImg.setImageResource(closedCases[i]);
            ImageView reward = findViewById(rewardIds[i]);
            reward.setImageResource(closedRewards[i]);
        }
        findViewById(R.id.buttonDeal).setVisibility(View.INVISIBLE);
        findViewById(R.id.buttonNodeal).setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View v) {
        int caseNumber = -1;
        if(!gameOver && !waiting) {
            for(int i=0;i<caseIds.length;i++) {
                if(caseIds[i] == v.getId()) {
                    caseNumber = i;
                }
            }
            if(round == 3 && caseNumber != -1 ){
                if(!isOpened[shuffledOpenCases.get(caseNumber)]) {
                    gameOver = true;
                    ImageView selectedCase = findViewById(v.getId());
                    selectedCase.setImageResource(openCases[shuffledOpenCases.get(caseNumber)]);
                    ImageView reward = findViewById(rewardIds[shuffledOpenCases.get(caseNumber)]);
                    reward.setImageResource(openRewards[shuffledOpenCases.get(caseNumber)]);
                    isOpened[shuffledOpenCases.get(caseNumber)] = true;
                    for(int i=0;i<10;i++) {
                        if(!isOpened[i]) {
                            dealAmount = amounts[i];
                            break;
                        }
                    }
                    textViewChooseCases.setText("You won: $" + dealAmount);
                }

            } else if(caseNumber != -1 && casesOpened >= 3) {
                waiting = true;
                if(casesOpened == 3) {
                    for(int i=0;i<caseIds.length;i++) {
                        if(caseIds[i] == v.getId()) {
                            caseNumber = i;
                        }
                    }

                    ImageView selectedCase = findViewById(v.getId());
                    selectedCase.setImageResource(openCases[shuffledOpenCases.get(caseNumber)]);
                    ImageView reward = findViewById(rewardIds[shuffledOpenCases.get(caseNumber)]);
                    reward.setImageResource(openRewards[shuffledOpenCases.get(caseNumber)]);
                    findViewById(R.id.buttonDeal).setVisibility(View.VISIBLE);
                    findViewById(R.id.buttonNodeal).setVisibility(View.VISIBLE);
                    isOpened[shuffledOpenCases.get(caseNumber)] = true;

                }
                for(int i=0;i<10;i++) {
                    if(!isOpened[i]) {
                        dealAmount += amounts[i];
                        count++;
                    }
                }
                dealAmount = dealAmount / count;
                dealAmount = (dealAmount * 60) / 100;
                Log.d("deal amount", "onClick: "+dealAmount);
                textViewChooseCases.setText("Bank Deal: $" + dealAmount);
                casesOpened++;
            } else if(caseNumber != -1 && !isOpened[shuffledOpenCases.get(caseNumber)]) {
                casesOpened++;
                int n = casesLeft - casesOpened;
                textViewChooseCases.setText("Choose " + n + " Cases");
                ImageView selectedCase = findViewById(v.getId());
                selectedCase.setImageResource(openCases[shuffledOpenCases.get(caseNumber)]);
                ImageView reward = findViewById(rewardIds[shuffledOpenCases.get(caseNumber)]);
                reward.setImageResource(openRewards[shuffledOpenCases.get(caseNumber)]);
                isOpened[shuffledOpenCases.get(caseNumber)] = true;
            }

        }
    }
}



