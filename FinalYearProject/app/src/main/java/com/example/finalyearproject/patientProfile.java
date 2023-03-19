package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PatientProfile extends AppCompatActivity {

    private TextView patientName, patientDescription;
    private String name, description;
    private String firstName, lastName, dateOfBirth, gender, desc, id;
    private Button returnToList, start, checkGraph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_id);

        sortIds();
        retrieveBundleInformation();

        patientName.setText(name);
        patientDescription.setText(description);

        returnToList.setOnClickListener(v -> openListOfPatients());
        start.setOnClickListener(v -> openQuestionOne());
        checkGraph.setOnClickListener(v -> openCheckPatientResults());
    }

    //retrieve all user information from previous page and store in variables
    //then use variables to set texts based on user's information
    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null) {
            firstName = patientInfo.getString("firstName");
            lastName = patientInfo.getString("lastName");
            dateOfBirth = patientInfo.getString("DoB");
            gender = patientInfo.getString("gender");
            desc = patientInfo.getString("description");
            id = patientInfo.getString("id");
            name = firstName + " " + lastName;
            description = gender + "\n" + dateOfBirth + "\n" + desc;
        }
    }

    //sorting out ids from xml file and storing them appropriately named variables
    private void sortIds() {
        patientName = findViewById(R.id.patient_name);
        patientDescription = findViewById(R.id.patient_desc);
        returnToList = findViewById(R.id.return_to_list);
        start = findViewById(R.id.go_quiz);
        checkGraph = findViewById(R.id.check_graph);
    }

    //move to next page and include necessary data to move to next page
    private void openQuestionOne() {
        Intent intent = new Intent(this, QuestionOne.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void openCheckPatientResults() {
        Intent intent = new Intent(this, CheckPatientGraph.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("DoB", dateOfBirth);
        intent.putExtra("gender", gender);
        intent.putExtra("description", desc);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void openListOfPatients() {
        Intent intent = new Intent(this, ListOfPatients.class);
        startActivity(intent);
    }
}