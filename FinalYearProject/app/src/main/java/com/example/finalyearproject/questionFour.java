package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class QuestionFour extends AppCompatActivity {

    private Button continueBtn;
    private final EditText[] clock = new EditText[12];
    private final List<Integer> inputValues = new ArrayList<>();
    private String firstName, lastName, id;
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

    //move to next page, and store newest result
    public void continueToQuestionFive() {
        Intent intent = new Intent(this, QuestionFive.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("id", id);
        intent.putExtra("questionOne", questionOne);
        intent.putExtra("questionTwo", questionTwo);
        intent.putExtra("questionThree", questionThree);
        intent.putExtra("questionFour", score);
        startActivity(intent);
    }

    //check if the inputted answers are correct, and return a score value
    public void checkAnswers() {
        final int [] result = {12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        for(int i = 0; i < result.length; i++) {
            if(inputValues.get(i) == result[i])
                score++;
        }
    }

    //retrieve all info from previous page and store in variables
    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null){
            firstName = patientInfo.getString("firstName");
            lastName = patientInfo.getString("lastName");
            id = patientInfo.getString("id");
            questionOne = patientInfo.getInt("questionOne");
            questionTwo = patientInfo.getInt("questionTwo");
            questionThree = patientInfo.getInt("questionThree");
        }
    }

    //get all inputted values made by user and store in list
    private void retrieveClockValues() {
        for (EditText editText : clock) {
            int input = Integer.parseInt(editText.getText().toString());
            inputValues.add(input);
        }
    }

    //make sure all input boxes are filled up with an appropriate answer
    private boolean checkValidation() {
        return !TextUtils.isEmpty(clock[0].getText().toString()) && !TextUtils.isEmpty(clock[1].getText().toString())
                && !TextUtils.isEmpty(clock[2].getText().toString()) && !TextUtils.isEmpty(clock[3].getText().toString())
                && !TextUtils.isEmpty(clock[4].getText().toString()) && !TextUtils.isEmpty(clock[5].getText().toString())
                && !TextUtils.isEmpty(clock[6].getText().toString()) && !TextUtils.isEmpty(clock[7].getText().toString())
                && !TextUtils.isEmpty(clock[8].getText().toString()) && !TextUtils.isEmpty(clock[9].getText().toString())
                && !TextUtils.isEmpty(clock[10].getText().toString()) && !TextUtils.isEmpty(clock[11].getText().toString());
    }

    //alert the user if they have not inputted an answer
    private void alert() {
        for (EditText editText : clock) {
            if (TextUtils.isEmpty(clock[0].getText().toString()))
                editText.setError("Input Needed");
        }
    }

    //sort ids in xml file
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