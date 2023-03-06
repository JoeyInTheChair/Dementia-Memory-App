package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class questionFive extends AppCompatActivity {

    TextView x, y;
    Button continueBtn;
    EditText answer;
    private int input = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.question_five);
        super.onCreate(savedInstanceState);

        x = findViewById(R.id.xValue);
        y = findViewById(R.id.yValue);
        answer = findViewById(R.id.answerInput);
        continueBtn = findViewById(R.id.continueToEndPage);

        int val = (int) (Math.random() * 50) + 1;
        setValue(x, val);
        setValue(y, val-5);

        continueBtn.setOnClickListener(view -> {
            if(checkValidation()) {
                input = Integer.parseInt(answer.getText().toString());
                System.out.println("[FINAL ANSWER]: " + input);
            }
            else {
                alert();
            }
        });
    }
    public void setValue(TextView text, int x) {
        int val = (int) (Math.random() * x) + 1;
        text.setText(Integer.toString(val));
    }
    public boolean checkValidation() {
        return !TextUtils.isEmpty(answer.getText().toString());
    }
    public void alert() {
        if (TextUtils.isEmpty(answer.getText().toString())) answer.setError("Input Needed");
    }
}