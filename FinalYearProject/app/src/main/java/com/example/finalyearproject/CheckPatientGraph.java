package com.example.finalyearproject;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//comment later
public class CheckPatientGraph extends AppCompatActivity {
    private String firstName, lastName, dateOfBirth, gender, desc, id;
    private TextView patientName;
    private Button returnToProfilePage;
    private GraphView graphForQ1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Integer> qOne = new ArrayList<>();
    private List<Integer> qTwo = new ArrayList<>();
    private List<Integer> qThree = new ArrayList<>();
    private List<Integer> qFour = new ArrayList<>();
    private List<Integer> qFive = new ArrayList<>();
    private List<PatientResult> list = new ArrayList<>();
    LineGraphSeries<DataPoint> questionOneResults = new LineGraphSeries<>(new DataPoint[0]);
    public PatientResult patientResult = new PatientResult();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_patient_graph);

        retrieveBundleInformation();
        sortIds();
        getUserResults(id, patientResult);
        for(PatientResult p : list)
            p.getResult();

        returnToProfilePage.setOnClickListener(v -> returnHome());
    }

        private void storePatientResults(Map<String, Object> doc, String id, PatientResult patientResult) {
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

    private void setResultsForQuestionOne(List<Integer> question, GraphView graphView) {
        System.out.println("[INFO] LINE 94" + question);
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
            resultPoints.appendData(dp, true, 10);
        }
        graphView.addSeries(resultPoints);
    }

    private void getUserResults(String id, PatientResult patientResult) {
        db.collection("patientResults")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                storePatientResults(document.getData(), id, patientResult);
                            }
                            setResultsForQuestionOne(qTwo, graphForQ1);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

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

    private void sortIds() {
        patientName = findViewById(R.id.patient_name);
        returnToProfilePage = findViewById(R.id.return_home);
        String name = firstName + " " + lastName;
        patientName.setText(name);
        graphForQ1 = findViewById(R.id.questionOne);
    }
}