package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PatientProfile extends AppCompatActivity {

    TextView patientName, patientDescription;
    String name, description;
    private String firstName, lastName, dateOfBirth, gender, desc;
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
            firstName = patientInfo.getString("firstName");
            lastName = patientInfo.getString("lastName");
            dateOfBirth = patientInfo.getString("DoB");
            gender = patientInfo.getString("gender");
            desc = patientInfo.getString("Description");
            name = firstName + " " + lastName;
            description = gender + "\n" + dateOfBirth + "\n" + desc;
        }
        patientName.setText(name);
        patientDescription.setText(description);

        returnToList.setOnClickListener(v -> openListOfPatients());
        start.setOnClickListener(v -> openStartGame());
    }

    private void openStartGame() {
        Intent intent = new Intent(this, StartGame.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("DoB", dateOfBirth);
        intent.putExtra("gender", gender);
        intent.putExtra("description", desc);
        startActivity(intent);
    }

    public void openListOfPatients() {
        Intent intent = new Intent(this, ListOfPatients.class);
        startActivity(intent);
    }
}