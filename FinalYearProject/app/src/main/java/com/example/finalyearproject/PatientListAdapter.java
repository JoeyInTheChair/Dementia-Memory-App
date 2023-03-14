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

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.profilePic);
            name = itemView.findViewById(R.id.patientName);
            dateOfBirth = itemView.findViewById(R.id.dateOfBirth);
            gender = itemView.findViewById(R.id.gender);
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
