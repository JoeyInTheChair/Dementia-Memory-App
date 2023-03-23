package com.example.finalyearproject;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

class TaskViewAdapter extends RecyclerView.Adapter<TaskViewAdapter.MyViewHolder> {

    Context context;
    List<TaskModel> modelList;

    public TaskViewAdapter(Context context, List<TaskModel> taskModelList) {
        this.context = context;
        this.modelList = taskModelList;
    }

    //giving layout to each row
    @NonNull
    @Override
    public TaskViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.task_bar, parent, false);
        return new TaskViewAdapter.MyViewHolder(view);
    }

    //assigning values
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull TaskViewAdapter.MyViewHolder holder, int position) {
        String [] taskDate = modelList.get(position).getTaskDate().split("/");
        String [] dayNames = new DateFormatSymbols().getWeekdays();
        Calendar date = Calendar.getInstance();
        date.set(Integer.parseInt(taskDate[2]), Integer.parseInt(taskDate[1])-1, Integer.parseInt(taskDate[0]));
        String dayOfWeek = dayNames[date.get(Calendar.DAY_OF_WEEK)];
        System.out.println("[INFO]: DAY " + dayOfWeek);
        String month = date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.UK);
        System.out.println("[INFO]: MONTH " + month);
        holder.taskName.setText(modelList.get(position).getTaskTitle());
        holder.taskDesc.setText(modelList.get(position).getTaskDescription());
        holder.time.setText(modelList.get(position).getTaskTime());
        holder.day.setText(dayOfWeek.substring(0,3));
        holder.dateNum.setText(taskDate[0]);
        holder.month.setText(Objects.requireNonNull(month).substring(0,3));

        holder.complete.setOnClickListener(v -> reminderTaskCompleted(holder, position));
    }

    //delete task from db and notify user task has been competed
    private void reminderTaskCompleted(MyViewHolder holder, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(holder.taskName.getContext());
        builder.setTitle("Task Completed!");
        builder.setMessage("You have finished your task: " + holder.taskDesc.getText());

        builder.setPositiveButton("Complete", (dialogInterface, i) -> FirebaseFirestore.getInstance().collection("patientReminder")
                .document(modelList.get(position).getId())
                .delete()
                .addOnSuccessListener(unused -> Toast.makeText(holder.taskName.getContext(), "Task Completed", Toast.LENGTH_SHORT).show()));

        builder.show();
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //creating views
        TextView day, dateNum, month, taskName, taskDesc, time;
        Button complete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.day);
            dateNum = itemView.findViewById(R.id.date);
            month = itemView.findViewById(R.id.month);
            taskName = itemView.findViewById(R.id.taskName);
            taskDesc = itemView.findViewById(R.id.taskDescription);
            time = itemView.findViewById(R.id.taskTime);
            complete = itemView.findViewById(R.id.completeTask);

        }
    }
}
