package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class addPatient extends AppCompatActivity {

    EditText etDate;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);

        etDate = findViewById(R.id.patient_date_of_birth);
        calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        addPatient.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Button button1 = findViewById(R.id.add_patient_to_db);
        button1.setOnClickListener(v -> returnToListOfPatients());

        Button button2 = findViewById(R.id.cancel);
        button2.setOnClickListener(v -> returnToListOfPatients());
    }

    public void returnToListOfPatients() {
        Intent intent = new Intent(this, listOfPatients.class);
        startActivity(intent);
    }
}