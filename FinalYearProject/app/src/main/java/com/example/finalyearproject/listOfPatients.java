package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class listOfPatients extends AppCompatActivity {

    RecyclerView recyclerView;
    PatientListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_patients);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<patientModel> options =
                new FirebaseRecyclerOptions.Builder<patientModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("patient"), patientModel.class)
                        .build();

        adapter = new PatientListAdapter(options);
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.add_patient);
        button.setOnClickListener(v -> openAddPatient());
    }

    public void openAddPatient() {
        Intent intent = new Intent(this, addPatient.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}