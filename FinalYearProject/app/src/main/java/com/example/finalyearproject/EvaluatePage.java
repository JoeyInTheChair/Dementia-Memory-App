package com.example.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EvaluatePage extends AppCompatActivity {

    private Button returnHome;
    private final TextView [] results = new TextView[5];
    private TextView patientName;
    private String firstName, lastName, gender, dateOfBirth, desc;
    private final List<Integer> listOfResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_page);

        sortIds();
        retrieveBundleInformation();
        setResults();

        returnHome.setOnClickListener(view -> returnToPatientProfilePage());
    }

    private void returnToPatientProfilePage() {
        Intent intent = new Intent(this, PatientProfile.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("DoB", dateOfBirth);
        intent.putExtra("gender", gender);
        intent.putExtra("description", desc);
        startActivity(intent);
    }

    private void setResults() {
        String name = firstName + " " + lastName;
        patientName.setText(name);
        for(int i = 0; i < results.length; i++)
            results[i].setText(String.valueOf(listOfResults.get(i)));
    }

    private void sortIds() {
        returnHome = findViewById(R.id.continue_to_home);
        patientName = findViewById(R.id.patient_name);
        results[0] = findViewById(R.id.q1);
        results[1] = findViewById(R.id.q2);
        results[2] = findViewById(R.id.q3);
        results[3] = findViewById(R.id.q4);
        results[4] = findViewById(R.id.q5);
    }

    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null){
            firstName = patientInfo.getString("firstName");
            lastName = patientInfo.getString("lastName");
            dateOfBirth = patientInfo.getString("DoB");
            gender = patientInfo.getString("gender");
            desc = patientInfo.getString("description");
            listOfResults.add(patientInfo.getInt("questionOne"));
            listOfResults.add(patientInfo.getInt("questionTwo"));
            listOfResults.add(patientInfo.getInt("questionThree"));
            listOfResults.add(patientInfo.getInt("questionFour"));
            listOfResults.add(patientInfo.getInt("questionFive"));
        }
    }
}