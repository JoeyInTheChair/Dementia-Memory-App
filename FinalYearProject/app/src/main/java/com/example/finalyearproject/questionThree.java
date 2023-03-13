package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class questionThree extends AppCompatActivity {
    private Button[] circleButtons = new Button[12];
    private final String [] text = {"1", "A", "2", "B", "3", "C", "4", "D", "5", "E", "6", "F"};
    private List<String> answer = new ArrayList<>();
    private String ans = "", name = "";
    private Button continueButton, resetButton;
    private int score = 0, questionOne = 0, questionTwo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_three);

        sortIDs();
        sortCircleButtons();
        retrieveBundleInformation();

        circleButtons[0].setOnClickListener((view -> inputAnswer(circleButtons[0])));
        circleButtons[1].setOnClickListener((view -> inputAnswer(circleButtons[1])));
        circleButtons[2].setOnClickListener((view -> inputAnswer(circleButtons[2])));
        circleButtons[3].setOnClickListener((view -> inputAnswer(circleButtons[3])));
        circleButtons[4].setOnClickListener((view -> inputAnswer(circleButtons[4])));
        circleButtons[5].setOnClickListener((view -> inputAnswer(circleButtons[5])));
        circleButtons[6].setOnClickListener((view -> inputAnswer(circleButtons[6])));
        circleButtons[7].setOnClickListener((view -> inputAnswer(circleButtons[7])));
        circleButtons[8].setOnClickListener((view -> inputAnswer(circleButtons[8])));
        circleButtons[9].setOnClickListener((view -> inputAnswer(circleButtons[9])));
        circleButtons[10].setOnClickListener((view -> inputAnswer(circleButtons[10])));
        circleButtons[11].setOnClickListener((view -> inputAnswer(circleButtons[11])));

        resetButton.setOnClickListener((view -> {
            answer.removeAll(answer);
            for (Button circle : circleButtons) {
                circle.setEnabled(true);
                circle.setBackgroundResource(R.drawable.circle_outline);
            }
            sortCircleButtons();
            continueButton.setVisibility(View.INVISIBLE);
        }));

        continueButton.setOnClickListener(v -> continueToQuestionFour());
    }

    public void continueToQuestionFour() {
        Intent intent = new Intent(this, questionFour.class);
        intent.putExtra("name", name);
        intent.putExtra("questionOne", questionOne);
        intent.putExtra("questionTwo", questionTwo);
        intent.putExtra("questionThree", score);
        System.out.println("[INFO] " + name + " scored a result of " + score + " in question 3");
        startActivity(intent);
    }

    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null){
            name = patientInfo.getString("name");
            questionOne = patientInfo.getInt("questionOne");
            questionTwo = patientInfo.getInt("questionTwo");
        }
    }

    public void inputAnswer(Button button) {
        ans = button.getText().toString();
        answer.add(ans);
        button.setBackgroundResource(R.drawable.check);
        button.setEnabled(false);
        button.setText("");
        if(answer.size() == 12) {
            continueButton.setVisibility(View.VISIBLE);
            checkAnswers();
        }
    }

    public void checkAnswers() {
        for(int i = 0; i < 12; i++) {
            if(answer.get(i).equals(text[i]))
                score++;
        }
    }
    private void sortIDs() {
        circleButtons[0] = findViewById(R.id.circle1);
        circleButtons[1] = findViewById(R.id.circle2);
        circleButtons[2] = findViewById(R.id.circle3);
        circleButtons[3] = findViewById(R.id.circle4);
        circleButtons[4] = findViewById(R.id.circle5);
        circleButtons[5] = findViewById(R.id.circle6);
        circleButtons[6] = findViewById(R.id.circle7);
        circleButtons[7] = findViewById(R.id.circle8);
        circleButtons[8] = findViewById(R.id.circle9);
        circleButtons[9] = findViewById(R.id.circle10);
        circleButtons[10] = findViewById(R.id.circle11);
        circleButtons[11] = findViewById(R.id.circle12);

        continueButton = findViewById(R.id.continueToQFour);
        continueButton.setVisibility(View.INVISIBLE);

        resetButton = findViewById(R.id.resetButton);
    }

    public void sortCircleButtons() {
        int [] randomListNum = new int [12];
        randomListNum = fillArrayWithNum(randomListNum);
        for(int i = 0; i < circleButtons.length; i++)
            circleButtons[i].setText(text[randomListNum[i]]);
    }
    public int[] fillArrayWithNum(int [] arr) {
        int listSize = 11;
        for (int i = 0; i <= listSize; i++) {
            arr[0] = (int)(Math.random()*listSize)+1;

            while (arr[1] == arr[0])
            {
                arr[1] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[2] == arr[1]) || (arr[2] == arr[0]) )
            {
                arr[2] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[3] == arr[0]) || (arr[3] == arr[1]) || (arr[3] == arr[2]) )
            {
                arr[3] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[4] == arr[0]) || (arr[4] == arr[1]) || (arr[4] == arr[2]) || (arr[4] == arr[3]) )
            {
                arr[4] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[5] == arr[0]) || (arr[5] == arr[1]) || (arr[5] == arr[2]) || (arr[5] == arr[3]) || (arr[5] == arr[4]))
            {
                arr[5] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[6] == arr[0]) || (arr[6] == arr[1]) || (arr[6] == arr[2]) || (arr[6] == arr[3]) || (arr[6] == arr[4]) || (arr[6] == arr[5]))
            {
                arr[6] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[7] == arr[0]) || (arr[7] == arr[1]) || (arr[7] == arr[2]) || (arr[7] == arr[3]) || (arr[7] == arr[4]) || (arr[7] == arr[5])|| (arr[7] == arr[6]))
            {
                arr[7] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[8] == arr[0]) || (arr[8] == arr[1]) || (arr[8] == arr[2]) || (arr[8] == arr[3]) || (arr[8] == arr[4]) || (arr[8] == arr[5])|| (arr[8] == arr[6]) || (arr[8] == arr[7]))
            {
                arr[8] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[9] == arr[0]) || (arr[9] == arr[1]) || (arr[9] == arr[2]) || (arr[9] == arr[3]) || (arr[9] == arr[4]) || (arr[9] == arr[5])|| (arr[9] == arr[6]) || (arr[9] == arr[7]) || (arr[9] == arr[8]))
            {
                arr[9] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[10] == arr[0]) || (arr[10] == arr[1]) || (arr[10] == arr[2]) || (arr[10] == arr[3]) || (arr[10] == arr[4]) || (arr[10] == arr[5])|| (arr[10] == arr[6]) || (arr[10] == arr[7]) || (arr[10] == arr[8]) || (arr[10] == arr[9]))
            {
                arr[10] = (int)(Math.random()*listSize)+1;
            }
            while ((arr[11] == arr[0]) || (arr[11] == arr[1]) || (arr[11] == arr[2]) || (arr[11] == arr[3]) || (arr[11] == arr[4]) || (arr[11] == arr[5])|| (arr[11] == arr[6]) || (arr[11] == arr[7]) || (arr[11] == arr[8]) || (arr[11] == arr[9]) || (arr[11] == arr[10]))
            {
                arr[11] = (int)(Math.random()*listSize)+1;
            }

        }
        return arr;
    }
}