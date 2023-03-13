package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class questionFour extends AppCompatActivity {

    private Button continueBtn;
    private final EditText[] clock = new EditText[12];
    private List<Integer> inputValues = new ArrayList<>();
    private String name = "";
    private int score = 0, questionOne = 0, questionTwo = 0, questionThree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_four);

        retrieveBundleInformation();
        sortIds();

        continueBtn.setOnClickListener(view -> {
            if(checkValidation()) {
                retrieveClockValues();
                checkAnswers();
                continueToQuestionFive();
            }
            else {
                alert();
            }
        });
    }

    public void continueToQuestionFive() {
        Intent intent = new Intent(this, questionFive.class);
        intent.putExtra("name", name);
        intent.putExtra("questionOne", questionOne);
        intent.putExtra("questionTwo", questionTwo);
        intent.putExtra("questionThree", questionThree);
        intent.putExtra("questionFour", score);
        System.out.println("[INFO] " + name + " scored a result of " + score + " in question 4");
        startActivity(intent);
    }

    public void checkAnswers() {
        final int [] result = {12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        for(int i = 0; i < result.length; i++) {
            if(inputValues.get(i) == result[i])
                score++;
        }
    }

    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null){
            name = patientInfo.getString("name");
            questionOne = patientInfo.getInt("questionOne");
            questionTwo = patientInfo.getInt("questionTwo");
            questionThree = patientInfo.getInt("questionThree");
        }
    }

    private void retrieveClockValues() {
        for (EditText editText : clock) {
            int input = Integer.parseInt(editText.getText().toString());
            inputValues.add(input);
        }
    }

    private boolean checkValidation() {
        return !TextUtils.isEmpty(clock[0].getText().toString()) && !TextUtils.isEmpty(clock[1].getText().toString())
                && !TextUtils.isEmpty(clock[2].getText().toString()) && !TextUtils.isEmpty(clock[3].getText().toString())
                && !TextUtils.isEmpty(clock[4].getText().toString()) && !TextUtils.isEmpty(clock[5].getText().toString())
                && !TextUtils.isEmpty(clock[6].getText().toString()) && !TextUtils.isEmpty(clock[7].getText().toString())
                && !TextUtils.isEmpty(clock[8].getText().toString()) && !TextUtils.isEmpty(clock[9].getText().toString())
                && !TextUtils.isEmpty(clock[10].getText().toString()) && !TextUtils.isEmpty(clock[11].getText().toString());
    }

    private void alert() {
        if (TextUtils.isEmpty(clock[0].getText().toString())) clock[0].setError("Input Needed");
        if (TextUtils.isEmpty(clock[1].getText().toString())) clock[1].setError("Input Needed");
        if (TextUtils.isEmpty(clock[2].getText().toString())) clock[2].setError("Input Needed");
        if (TextUtils.isEmpty(clock[3].getText().toString())) clock[3].setError("Input Needed");
        if (TextUtils.isEmpty(clock[4].getText().toString())) clock[4].setError("Input Needed");
        if (TextUtils.isEmpty(clock[5].getText().toString())) clock[5].setError("Input Needed");
        if (TextUtils.isEmpty(clock[6].getText().toString())) clock[6].setError("Input Needed");
        if (TextUtils.isEmpty(clock[7].getText().toString())) clock[7].setError("Input Needed");
        if (TextUtils.isEmpty(clock[8].getText().toString())) clock[8].setError("Input Needed");
        if (TextUtils.isEmpty(clock[9].getText().toString())) clock[9].setError("Input Needed");
        if (TextUtils.isEmpty(clock[10].getText().toString())) clock[10].setError("Input Needed");
        if (TextUtils.isEmpty(clock[11].getText().toString())) clock[11].setError("Input Needed");
    }

    private void sortIds() {
        clock[0] = findViewById(R.id.twelveOclock);
        clock[1] = findViewById(R.id.oneOclock);
        clock[2] = findViewById(R.id.twoOclock);
        clock[3] = findViewById(R.id.threeOclock);
        clock[4] = findViewById(R.id.fourOclock);
        clock[5] = findViewById(R.id.fiveOclock);
        clock[6] = findViewById(R.id.sixOclock);
        clock[7] = findViewById(R.id.sevenOclock);
        clock[8] = findViewById(R.id.eightOclock);
        clock[9] = findViewById(R.id.nineOclock);
        clock[10] = findViewById(R.id.tenOclock);
        clock[11] = findViewById(R.id.elevenOclock);
        continueBtn = findViewById(R.id.continueToQFive);
    }
}