package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class questionThree extends AppCompatActivity {

    public Button [] circButtons = new Button[12];
    private final String [] text = {"A", "1", "B", "2", "C", "3", "D", "4", "E", "5", "F", "6"};
    public List<String> answer = new ArrayList<>();
    public String ans = "";
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_three);

        setCircleButtons();
        sortCircleButtons();

        circButtons[0].setOnClickListener((view -> inputAnswer(circButtons[0])));
        circButtons[1].setOnClickListener((view -> inputAnswer(circButtons[1])));
        circButtons[2].setOnClickListener((view -> inputAnswer(circButtons[2])));
        circButtons[3].setOnClickListener((view -> inputAnswer(circButtons[3])));
        circButtons[4].setOnClickListener((view -> inputAnswer(circButtons[4])));
        circButtons[5].setOnClickListener((view -> inputAnswer(circButtons[5])));
        circButtons[6].setOnClickListener((view -> inputAnswer(circButtons[6])));
        circButtons[7].setOnClickListener((view -> inputAnswer(circButtons[7])));
        circButtons[8].setOnClickListener((view -> inputAnswer(circButtons[8])));
        circButtons[9].setOnClickListener((view -> inputAnswer(circButtons[9])));
        circButtons[10].setOnClickListener((view -> inputAnswer(circButtons[10])));
        circButtons[11].setOnClickListener((view -> inputAnswer(circButtons[11])));

        Button reset = findViewById(R.id.resetButton);
        continueButton = findViewById(R.id.continueToQFour);

        continueButton.setVisibility(View.INVISIBLE);

        reset.setOnClickListener((view -> {
            answer.removeAll(answer);
            for (Button circButton : circButtons) circButton.setVisibility(View.VISIBLE);
            continueButton.setVisibility(View.INVISIBLE);
        }));

        continueButton.setOnClickListener(v -> continueToQuestionFour());

    }

    public void continueToQuestionFour() {
        Intent intent = new Intent(this, questionFour.class);
        startActivity(intent);
    }
    public void inputAnswer(Button button) {
        ans = button.getText().toString();
        answer.add(ans);
        button.setVisibility(View.INVISIBLE);
        if(answer.size() == 12) {
            continueButton.setVisibility(View.VISIBLE);
            System.out.println("[FINAL ANSWER]: " + answer);
        }
    }
    public void setCircleButtons() {
        circButtons[0] = findViewById(R.id.circ1);
        circButtons[1] = findViewById(R.id.circ2);
        circButtons[2] = findViewById(R.id.circ3);
        circButtons[3] = findViewById(R.id.circ4);
        circButtons[4] = findViewById(R.id.circ5);
        circButtons[5] = findViewById(R.id.circ6);
        circButtons[6] = findViewById(R.id.circ7);
        circButtons[7] = findViewById(R.id.circ8);
        circButtons[8] = findViewById(R.id.circ9);
        circButtons[9] = findViewById(R.id.circ10);
        circButtons[10] = findViewById(R.id.circ11);
        circButtons[11] = findViewById(R.id.circ12);
    }

    public void sortCircleButtons() {
        int [] randomListNum = new int [12];
        randomListNum = fillArrayWithNum(randomListNum);
        for(int i = 0; i < circButtons.length; i++)
            circButtons[i].setText(text[randomListNum[i]]);
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