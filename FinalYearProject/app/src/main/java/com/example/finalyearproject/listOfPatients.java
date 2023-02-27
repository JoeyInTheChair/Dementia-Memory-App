package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class listOfPatients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_patients);

        Button button = findViewById(R.id.add_patient);
        button.setOnClickListener(v -> openAddPatient());
    }

    public void openAddPatient() {
        Intent intent = new Intent(this, addPatient.class);
        startActivity(intent);
    }
}