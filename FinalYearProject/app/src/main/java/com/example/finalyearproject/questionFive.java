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
    private String firstName, lastName, gender, dateOfBirth, desc;
    private final int val = (int) (Math.random() * 50) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_five);

        retrieveBundleInformation();
        sortIds();

        setValue(x, val);
        setValue(y, val-5);

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

    public void continueToEvaluation() {
        Intent intent = new Intent(this, EvaluatePage.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("DoB", dateOfBirth);
        intent.putExtra("gender", gender);
        intent.putExtra("description", desc);
        intent.putExtra("questionOne", questionOne);
        intent.putExtra("questionTwo", questionTwo);
        intent.putExtra("questionThree", questionThree);
        intent.putExtra("questionFour", questionFour);
        intent.putExtra("questionFive", score);
        startActivity(intent);
    }

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

    public void alert() {
        if (TextUtils.isEmpty(answer.getText().toString())) answer.setError("Input Needed");
    }

    public void setValue(TextView text, int x) {
        int val = (int) (Math.random() * x) + 1;
        String value = Integer.toString(val);
        text.setText(value);
    }

    private void sortIds() {
        x = findViewById(R.id.xValue);
        y = findViewById(R.id.yValue);
        answer = findViewById(R.id.answerInput);
        continueButton = findViewById(R.id.continueToEndPage);
    }

    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null){
            firstName = patientInfo.getString("firstName");
            lastName = patientInfo.getString("lastName");
            dateOfBirth = patientInfo.getString("DoB");
            gender = patientInfo.getString("gender");
            desc = patientInfo.getString("description");
            questionOne = patientInfo.getInt("questionOne");
            questionTwo = patientInfo.getInt("questionTwo");
            questionThree = patientInfo.getInt("questionThree");
            questionFour = patientInfo.getInt("questionFour");
        }
    }
}