package com.example.finalyearproject;

import static android.content.ContentValues.TAG;
import java.lang.Object;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReminderPage extends AppCompatActivity {

    private Button returnToProfile, addReminder;
    private TextView patientName;
    private RecyclerView rv;
    private String firstName, lastName, gender, dob, desc, id;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final Map<String, Object> task = new HashMap<>();
    private final List<TaskModel> taskModelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_page);

        retrieveBundleInformation();
        sortIds();

        String fullName = firstName + " " + lastName;
        patientName.setText(fullName);
        getReminderList();
        returnToProfile.setOnClickListener(v -> returnHome());
        addReminder.setOnClickListener(v -> addNewReminder());
    }
    public void getReminderList() {
        db.collection("patientReminder")
                .whereEqualTo("id", id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot ds : snapshots)
                            storePatientTask(ds.getData());

                        TaskViewAdapter adapter = new TaskViewAdapter(ReminderPage.this, taskModelList);
                        rv.setAdapter(adapter);
                        rv.setLayoutManager(new LinearLayoutManager(ReminderPage.this));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error getting documents.", e);
                    }
                });
    }

    private void storePatientTask(Map<String, Object> data) {
        TaskModel taskModel = new TaskModel();
        for(String i : data.keySet()) {
            switch(i){
                case "title":
                    String title = data.get(i).toString();
                    taskModel.setTaskTitle(title);
                    break;
                case "description":
                    String desc = data.get(i).toString();
                    taskModel.setTaskDescription(desc);
                    break;
                case "date":
                    String date = data.get(i).toString();
                    taskModel.setTaskDate(date);
                    break;
                case "time":
                    String time = data.get(i).toString();
                    taskModel.setTaskTime(time);
                    break;
                default:
                    break;
            }
        }
        taskModelList.add(taskModel);
    }

    private void addNewReminder() {
        //pop-up create reminder page
        final DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(new ViewHolder(R.layout.create_reminder))
                .setExpanded(true, 1610)
                .create();
        dialogPlus.show();

        //sort out ids in new xml file when pop up
        Button addNewTask = findViewById(R.id.addTaskToDB);
        Button cancel = findViewById(R.id.cancel);
        Button selectTime = findViewById(R.id.select_time);
        TextView calender = findViewById(R.id.task_date);
        TextView taskTime = findViewById(R.id.task_time);
        EditText taskTitle = findViewById(R.id.task_title);
        EditText taskDescription = findViewById(R.id.task_description);

        calender.setOnClickListener(view -> getCalenderViewer());

        dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = day + "/" + month + "/" + year;
            calender.setText(date);
        };

        selectTime.setOnClickListener(view -> getTimeViewer(taskTime));

        addNewTask.setOnClickListener(view -> {
            storeTask(calender, taskTime, taskTitle, taskDescription);
            db.collection("patientReminder")
                    .add(task)
                    .addOnSuccessListener(documentReference -> {
                        //alert reminder whether adding new reminder has been completed successfully or failed to be completed
                        Toast.makeText(ReminderPage.this, "New Reminder Has Been Added Successfully", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> Toast.makeText(ReminderPage.this, "Reminder Failed to be Added", Toast.LENGTH_SHORT).show());
            dialogPlus.dismiss();
            refreshPage();
        });
        cancel.setOnClickListener(view -> dialogPlus.dismiss());
    }

    private void refreshPage() {
        Intent intent = new Intent(this, ReminderPage.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("gender", gender);
        intent.putExtra("DoB", dob);
        intent.putExtra("description", desc);
        intent.putExtra("id", id);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    //storing new task in firebase
    private void storeTask(TextView calender, TextView taskTime, EditText taskTitle, EditText taskDescription) {
        String title = taskTitle.getText().toString();
        String description = taskDescription.getText().toString();
        String date = calender.getText().toString();
        String time = taskTime.getText().toString();
        task.put("id", id);
        task.put("title", title);
        task.put("description", description);
        task.put("date", date);
        task.put("time", time);
    }

    //create "select time" pop up
    private void getTimeViewer(TextView taskTime) {
        TimePickerDialog dialog = new TimePickerDialog(ReminderPage.this, (timePicker, hours, minutes) -> {
            String hour, min;
            if(hours == 0) hour = "00";
            else hour = Integer.toString(hours);

            if(minutes == 0) min = "00";
            else min = Integer.toString(minutes);

            String time = hour + ":" + min;
            taskTime.setText(time);
        }, 0, 0, true);
        dialog.show();
    }

    //create a calender pop up
    private void getCalenderViewer() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                ReminderPage.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //sorting our ids in xml file
    private void sortIds() {
        patientName = findViewById(R.id.patient_name);
        returnToProfile = findViewById(R.id.return_to_list);
        addReminder = findViewById(R.id.add_reminder);
        rv = findViewById(R.id.reminder_list);
    }

    //retrieve all previous information from last page
    private void retrieveBundleInformation() {
        Bundle patientInfo = getIntent().getExtras();
        if(patientInfo != null) {
            firstName = patientInfo.getString("firstName");
            lastName = patientInfo.getString("lastName");
            dob = patientInfo.getString("DoB");
            gender = patientInfo.getString("gender");
            desc = patientInfo.getString("description");
            id = patientInfo.getString("id");
        }
    }

    //return to main profile page
    private void returnHome() {
        Intent intent = new Intent(getApplicationContext(), PatientProfile.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        intent.putExtra("gender", gender);
        intent.putExtra("DoB", dob);
        intent.putExtra("description", desc);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}