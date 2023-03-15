package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.Calendar;

public class PatientProfile extends AppCompatActivity {

    TextView patientName, patientDescription;
    String name, description;
    private String firstName, lastName, dateOfBirth, gender, desc;
    Button returnToList, start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_id);

        sortIds();
        retrieveBundleInformation();

        patientName.setText(name);
        patientDescription.setText(description);

        returnToList.setOnClickListener(v -> openListOfPatients());
        start.setOnClickListener(v -> openStartGame());
    }

    private void retrieveBundleInformation() {
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
    }

    private void sortIds() {
        patientName = findViewById(R.id.patient_name);
        patientDescription = findViewById(R.id.patient_desc);
        returnToList = findViewById(R.id.return_to_list);
        start = findViewById(R.id.go_quiz);
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