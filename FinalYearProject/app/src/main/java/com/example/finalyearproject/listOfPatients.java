package com.example.finalyearproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ListOfPatients extends AppCompatActivity {

    RecyclerView recyclerView;
    PatientListAdapter adapter;
    private PatientListAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_patients);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PatientModel> options =
                new FirebaseRecyclerOptions.Builder<PatientModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("patient"), PatientModel.class)
                        .build();
        setOnClickListener();
        adapter = new PatientListAdapter(options, listener);
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.add_patient);
        button.setOnClickListener(v -> openAddPatient());
    }

    private void setOnClickListener() {
        listener = (v, pos) -> {
            Intent intent = new Intent(getApplicationContext(), PatientProfile.class);
            String firstName = adapter.getItem(pos).getFirstName();
            String lastName = adapter.getItem(pos).getLastName();
            String gender = adapter.getItem(pos).getGender();
            String dateOfBirth = adapter.getItem(pos).getDateOfBirth();
            String desc = adapter.getItem(pos).getDescription();
            intent.putExtra("firstName", firstName);
            intent.putExtra("lastName", lastName);
            intent.putExtra("gender", gender);
            intent.putExtra("DoB", dateOfBirth);
            intent.putExtra("Description", desc);
            startActivity(intent);
        };
    }

    public void openAddPatient() {
        Intent intent = new Intent(this, AddPatient.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str) {
        FirebaseRecyclerOptions<PatientModel> options =
                new FirebaseRecyclerOptions.Builder<PatientModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("patient").orderByChild("firstName").startAt(str).endAt(str+"~"), PatientModel.class)
                        .build();
        adapter = new PatientListAdapter(options, listener);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}