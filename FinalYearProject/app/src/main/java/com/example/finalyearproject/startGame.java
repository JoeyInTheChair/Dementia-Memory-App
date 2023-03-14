package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartGame extends AppCompatActivity {
    private String firstName, lastName, gender, dateOfBirth, desc;
    private Button startQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);

        startQuiz = findViewById(R.id.start);

        retrieveBundle();

        startQuiz.setOnClickListener(v -> openQuestionOne());

    }

    private void retrieveBundle() {
        Bundle patientName = getIntent().getExtras();
        if(patientName != null) {
            firstName = patientName.getString("firstName");
            lastName = patientName.getString("lastName");
            dateOfBirth = patientName.getString("DoB");
            gender = patientName.getString("gender");
            desc = patientName.getString("description");
        }
    }

    private void openQuestionOne() {
        Intent intent = new Intent(this, QuestionOne.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("DoB", dateOfBirth);
        intent.putExtra("gender", gender);
        intent.putExtra("description", desc);
        startActivity(intent);
    }
}