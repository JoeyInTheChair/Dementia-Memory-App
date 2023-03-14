package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PatientProfile extends AppCompatActivity {

    TextView patientName, patientDescription;
    String name, description;
    Button returnToList, start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_id);

        patientName = findViewById(R.id.patient_name);
        patientDescription = findViewById(R.id.patient_desc);
        returnToList = findViewById(R.id.return_to_list);
        start = findViewById(R.id.go_quiz);

        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null) {
            name = patientInfo.getString("firstName") + " " + patientInfo.getString("lastName");
            description = patientInfo.getString("gender") + "\n";
            description += patientInfo.getString("DoB") + "\n";
            description += patientInfo.getString("Description");
        }
        patientName.setText(name);
        patientDescription.setText(description);

        returnToList.setOnClickListener(v -> openListOfPatients());
        start.setOnClickListener(v -> openStartGame());
    }

    private void openStartGame() {
        Intent intent = new Intent(this, StartGame.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }

    public void openListOfPatients() {
        Intent intent = new Intent(this, ListOfPatients.class);
        startActivity(intent);
    }
}