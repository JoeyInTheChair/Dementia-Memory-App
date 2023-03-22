package com.example.finalyearproject;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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
        date.set(Integer.parseInt(taskDate[2]), Integer.parseInt(taskDate[1]), Integer.parseInt(taskDate[0]));
        String dayOfWeek = dayNames[date.get(Calendar.DAY_OF_WEEK)];
        String month = date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.UK);
        holder.taskName.setText(modelList.get(position).getTaskTitle());
        holder.taskDesc.setText(modelList.get(position).getTaskDescription());
        holder.time.setText(modelList.get(position).getTaskTime());
        holder.day.setText(dayOfWeek.substring(0,3));
        holder.dateNum.setText(taskDate[1]);
        holder.month.setText(month.substring(0,3));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //creating views
        TextView day, dateNum, month, taskName, taskDesc, time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.day);
            dateNum = itemView.findViewById(R.id.date);
            month = itemView.findViewById(R.id.month);
            taskName = itemView.findViewById(R.id.taskName);
            taskDesc = itemView.findViewById(R.id.taskDescription);
            time = itemView.findViewById(R.id.taskTime);
        }
    }
}
