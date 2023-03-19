package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionFive extends AppCompatActivity {

    private TextView x, y;
    private Button continueButton;
    private EditText answer;
    private int score = 0, input = 0, questionOne = 0, questionTwo = 0, questionThree = 0, questionFour = 0;
    private String firstName, lastName, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_five);

        retrieveBundleInformation();
        sortIds();

        //make random values for x and y value in xml file
        String xVal = Integer.toString((int)(Math.random() * 49) + 1);
        String yVal = Integer.toString((int)(Math.random() * Integer.parseInt(xVal) - 1) + 1);

        x.setText(xVal);
        y.setText(yVal);

        continueButton.setOnClickListener(view -> {
            if(checkValidation()) {
                input = Integer.parseInt(answer.getText().toString());
                checkAnswer(input);
                continueToEvaluation();
            }
            else {
                alert();
            }
        });
    }

    //move to next page, and store new result
    public void continueToEvaluation() {
        Intent intent = new Intent(this, EvaluatePage.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("id", id);
        intent.putExtra("questionOne", questionOne);
        intent.putExtra("questionTwo", questionTwo);
        intent.putExtra("questionThree", questionThree);
        intent.putExtra("questionFour", questionFour);
        intent.putExtra("questionFive", score);
        startActivity(intent);
    }

    //check if user input is the correct answer
    private void checkAnswer(int input) {
        int xValue = Integer.parseInt(x.getText().toString());
        int yValue = Integer.parseInt(y.getText().toString());
        int resultValue = xValue - yValue;
        if(input == resultValue)
            score = 1;
        else
            score = 0;
    }

    public boolean checkValidation() {
        return !TextUtils.isEmpty(answer.getText().toString());
    }

    //alert user if they did not type in an appropriate answer
    public void alert() {
        if (TextUtils.isEmpty(answer.getText().toString())) answer.setError("Input Needed");
    }

    //sort out ids from xml file
    private void sortIds() {
        x = findViewById(R.id.xValue);
        y = findViewById(R.id.yValue);
        answer = findViewById(R.id.answerInput);
        continueButton = findViewById(R.id.continueToEndPage);
    }

    //retrieve previous information from last page and store in appropriately named variables
    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null){
            firstName = patientInfo.getString("firstName");
            lastName = patientInfo.getString("lastName");
            id = patientInfo.getString("id");
            questionOne = patientInfo.getInt("questionOne");
            questionTwo = patientInfo.getInt("questionTwo");
            questionThree = patientInfo.getInt("questionThree");
            questionFour = patientInfo.getInt("questionFour");
        }
    }
}