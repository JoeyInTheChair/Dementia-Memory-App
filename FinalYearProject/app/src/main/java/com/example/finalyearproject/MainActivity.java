package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        Button button = findViewById(R.id.continue_to_home);
        button.setOnClickListener(v -> openListOfPatients());

    }

    public void openListOfPatients() {
        Intent intent = new Intent(this, ListOfPatients.class);
        startActivity(intent);
    }
}