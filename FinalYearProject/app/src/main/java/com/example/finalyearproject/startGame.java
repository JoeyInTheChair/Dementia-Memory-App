package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class startGame extends AppCompatActivity {
    String name;
    Button startQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);

        startQuiz = findViewById(R.id.start);

        Bundle patientName = getIntent().getExtras();
        if(patientName != null)
            name = patientName.getString("name");

        startQuiz.setOnClickListener(v -> openQuestionOne());

    }

    private void openQuestionOne() {
        Intent intent = new Intent(this, QuestionOne.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}