package com.example.finalyearproject;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientListAdapter extends FirebaseRecyclerAdapter<PatientModel, PatientListAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public String fullName, dateOfBirth, gender, description;
    RecyclerViewClickListener listener;

    public PatientListAdapter(@NonNull FirebaseRecyclerOptions<PatientModel> options, RecyclerViewClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull PatientModel model) {
        fullName = model.getFirstName() + " " + model.getLastName();
        dateOfBirth = model.getDateOfBirth();
        gender = model.getGender();
        description = model.getDescription();
        holder.name.setText(fullName);
        holder.dateOfBirth.setText(dateOfBirth);
        holder.gender.setText(gender);

        holder.deleteButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
            builder.setTitle("Are you sure?");
            builder.setMessage("Deleted data can't be undone");

            builder.setPositiveButton("Delete", (dialogInterface, i) -> {
                FirebaseDatabase.getInstance().getReference().child("patient")
                        .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
                Toast.makeText(holder.name.getContext(), "Patient Data Deleted", Toast.LENGTH_SHORT).show();
            });

            builder.setNegativeButton("Cancel", (dialogInterface, i) -> Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show());
            builder.show();
        });

        holder.updateButton.setOnClickListener(view -> {


            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                    .setContentHolder(new ViewHolder(R.layout.update_patient))
                    .setExpanded(true, 2170)
                    .create();


            View v = dialogPlus.getHolderView();
            EditText firstName = v.findViewById(R.id.patientFirstName);
            EditText lastName = v.findViewById(R.id.patientLastName);
            EditText desc = v.findViewById(R.id.patientDescription);
            TextView dob = v.findViewById(R.id.patient_date_of_birth);
            AutoCompleteTextView gender = v.findViewById(R.id.gender);
            Button update = v.findViewById(R.id.updatePatientToDB);
            Button cancel = v.findViewById(R.id.cancel);

            firstName.setText(model.getFirstName());
            lastName.setText(model.getLastName());
            desc.setText(model.getDescription());
            dob.setText(model.getDateOfBirth());
            gender.setText(model.getGender());

            dialogPlus.show();

            update.setOnClickListener(view12 -> {
                Map<String, Object> map = new HashMap<>();
                map.put("firstName", firstName.getText().toString());
                map.put("lastName", lastName.getText().toString());
                map.put("gender", gender.getText().toString());
                map.put("dateOfBirth", dob.getText().toString());
                map.put("description", desc.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("patient")
                        .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(holder.name.getContext(), "Patient's Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            dialogPlus.dismiss();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(holder.name.getContext(), "Fail to Update Patient's Data", Toast.LENGTH_SHORT).show();
                            dialogPlus.dismiss();
                        });
            });
            cancel.setOnClickListener(view1 -> dialogPlus.dismiss());
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_bar, parent, false);
        return new myViewHolder(view);
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView img;
        TextView name, dateOfBirth, gender;
        Button updateButton, deleteButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.profilePic);
            name = itemView.findViewById(R.id.patientName);
            dateOfBirth = itemView.findViewById(R.id.dateOfBirth);
            gender = itemView.findViewById(R.id.gender);
            updateButton = itemView.findViewById(R.id.updatePatient);
            deleteButton = itemView.findViewById(R.id.deletePatient);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAbsoluteAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int pos);
    }
}
