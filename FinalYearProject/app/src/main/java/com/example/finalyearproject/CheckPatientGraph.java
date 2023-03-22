package com.example.finalyearproject;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CheckPatientGraph extends AppCompatActivity {
    private String firstName, lastName, dateOfBirth, gender, desc, id;
    private Button returnToProfilePage;
    private GraphView graphForQ1, graphForQ2, graphForQ3, graphForQ4, graphForQ5;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final List<Integer> qOne = new ArrayList<>();
    private final List<Integer> qTwo = new ArrayList<>();
    private final List<Integer> qThree = new ArrayList<>();
    private final List<Integer> qFour = new ArrayList<>();
    private final List<Integer> qFive = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_patient_graph);

        retrieveBundleInformation();
        sortIds();
        getUserResults(id);

        returnToProfilePage.setOnClickListener(v -> returnHome());
    }

    //run through map and find each answer for each question significant to that answer
        private void storePatientResults(Map<String, Object> doc, String id) {
            for(String i : doc.keySet()) {
                if(i.equals(id)){
                    for(Object o : doc.values()) {
                        Map<String, Object> temp = (Map<String, Object>) o;
                        long val;
                        for(String key :  temp.keySet()) {
                            switch (key) {
                                case "questionOne":
                                    val = (long)temp.get(key);
                                    qOne.add(Long.valueOf(val).intValue());
                                    break;
                                case "questionTwo":
                                    val = (long)temp.get(key);
                                    qTwo.add(Long.valueOf(val).intValue());
                                    break;
                                case "questionThree":
                                    val = (long)temp.get(key);
                                    qThree.add(Long.valueOf(val).intValue());
                                    break;
                                case "questionFour":
                                    val = (long)temp.get(key);
                                    qFour.add(Long.valueOf(val).intValue());
                                    break;
                                default:
                                    val = (long)temp.get(key);
                                    qFive.add(Long.valueOf(val).intValue());
                                    break;
                            }
                        }
                    }
                }
        }
    }

    //display results by getting points on a line graph
    private void setResultsOnGraph(List<Integer> question, GraphView graphView) {
        List<DataPoint> dataPoints = new ArrayList<>();
        int xCoordinate = 0;
        DataPoint point;
        for(Integer i : question){
            point = new DataPoint(xCoordinate, i);
            xCoordinate++;
            dataPoints.add(point);
        }
        LineGraphSeries<DataPoint> resultPoints = new LineGraphSeries<>();
        for(DataPoint dp : dataPoints) {
            resultPoints.appendData(dp, false, question.size());
        }
        graphView.addSeries(resultPoints);
    }

    //get user results from database i.e. firestore
    //then display results to screen
    private void getUserResults(String id) {
        db.collection("patientResults")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            storePatientResults(document.getData(), id);
                        }
                        setResultsOnGraph(qOne, graphForQ1);
                        setResultsOnGraph(qTwo, graphForQ2);
                        setResultsOnGraph(qThree, graphForQ3);
                        setResultsOnGraph(qFour, graphForQ4);
                        setResultsOnGraph(qFive, graphForQ5);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }

    //move from current page to profile page
    private void returnHome() {
        Intent intent = new Intent(getApplicationContext(), PatientProfile.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("gender", gender);
        intent.putExtra("DoB", dateOfBirth);
        intent.putExtra("description", desc);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    //retrieve previous information from previous page and assign to local variables
    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null) {
            firstName = patientInfo.getString("firstName");
            lastName = patientInfo.getString("lastName");
            dateOfBirth = patientInfo.getString("DoB");
            gender = patientInfo.getString("gender");
            desc = patientInfo.getString("description");
            id = patientInfo.getString("id");
        }
    }

    //sort out id's from xml file, and assign to appropriately named variables
    private void sortIds() {
        TextView patientName = findViewById(R.id.patient_name);
        returnToProfilePage = findViewById(R.id.return_home);
        String name = firstName + " " + lastName;
        patientName.setText(name);
        graphForQ1 = findViewById(R.id.questionOne);
        graphForQ2 = findViewById(R.id.questionTwo);
        graphForQ3 = findViewById(R.id.questionThree);
        graphForQ4 = findViewById(R.id.questionFour);
        graphForQ5 = findViewById(R.id.questionFive);
    }
}