package com.example.finalyearproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientListAdapter extends FirebaseRecyclerAdapter<patientModel, PatientListAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PatientListAdapter(@NonNull FirebaseRecyclerOptions<patientModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull patientModel model) {
        holder.name.setText(model.getFirstName() + " " + model.getLastName());
        holder.dateOfBirth.setText(model.getDateOfBirth());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_bar, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name, dateOfBirth;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.profilePic);
            name = itemView.findViewById(R.id.patientName);
            dateOfBirth = itemView.findViewById(R.id.dateOfBirth);
        }
    }
}
