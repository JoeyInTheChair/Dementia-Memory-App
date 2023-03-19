package com.example.finalyearproject;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluatePage extends AppCompatActivity {

    private Button returnHome;
    private final TextView [] results = new TextView[5];
    private TextView patientName;
    private String firstName, lastName, id;
    private final List<Integer> listOfResults = new ArrayList<>();
    private final Map<String, Object> resultMap = new HashMap<>();
    private final Map<String, Object> map = new HashMap<>();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_page);

        sortIds();
        retrieveBundleInformation();
        setResults();

        returnHome.setOnClickListener(view -> returnToPatientProfilePage());
    }

    //goes from current page to patient profile page
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void returnToPatientProfilePage() {
        storeResults();
        Intent intent = new Intent(this, ListOfPatients.class);
        startActivity(intent);
    }

    //store user result map inside database, firestore
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void storeResults() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now).replace(' ', 'x').replace(':', '-').replace('/','_');
        db.collection("patientResults")
                .document(date)
                .set(map)
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }

    //sets name in page to user's full name
    //stores user results into map
    private void setResults() {
        String name = firstName + " " + lastName;
        patientName.setText(name);
        for(int i = 0; i < results.length; i++)
            results[i].setText(String.valueOf(listOfResults.get(i)));
        storeResultsInMap();
    }

    //storing all user result from quiz into a map
    private void storeResultsInMap() {
        resultMap.put("questionOne", listOfResults.get(0));
        resultMap.put("questionTwo", listOfResults.get(1));
        resultMap.put("questionThree", listOfResults.get(2));
        resultMap.put("questionFour", listOfResults.get(3));
        resultMap.put("questionFive", listOfResults.get(4));
        map.put(id, resultMap);
    }

    //sorting out id's from XML file
    private void sortIds() {
        returnHome = findViewById(R.id.continue_to_home);
        patientName = findViewById(R.id.patient_name);
        results[0] = findViewById(R.id.q1);
        results[1] = findViewById(R.id.q2);
        results[2] = findViewById(R.id.q3);
        results[3] = findViewById(R.id.q4);
        results[4] = findViewById(R.id.q5);
    }

    //retrieving past data from previous page
    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null){
            firstName = patientInfo.getString("firstName");
            lastName = patientInfo.getString("lastName");
            id = patientInfo.getString("id");
            listOfResults.add(patientInfo.getInt("questionOne"));
            listOfResults.add(patientInfo.getInt("questionTwo"));
            listOfResults.add(patientInfo.getInt("questionThree"));
            listOfResults.add(patientInfo.getInt("questionFour"));
            listOfResults.add(patientInfo.getInt("questionFive"));
        }
    }
}