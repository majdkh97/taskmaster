package com.example.taskmaster;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{


    List<Task> allTasks = new ArrayList<>();

    public TaskAdapter(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public Task task;
        View itemView;
        public TaskViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            itemView.findViewById(R.id.GoToDetailFragment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToDetailPageActivity = new Intent(view.getContext(),TaskDetailPageActivity.class);
                    goToDetailPageActivity.putExtra("taskName",task.title);
                    view.getContext().startActivity(goToDetailPageActivity);
                }
            });

            itemView.findViewById(R.id.TaskFragment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToDetailPageActivity = new Intent(view.getContext(),TaskDetailPageActivity.class);
                    goToDetailPageActivity.putExtra("taskName",task.title);
                    view.getContext().startActivity(goToDetailPageActivity);
                }
            });

        }
    }

    @NonNull
    @NotNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.task=allTasks.get(position);

        TextView taskTitle = holder.itemView.findViewById(R.id.taskTitleFragment);
        TextView taskBody = holder.itemView.findViewById(R.id.taskBodyFragment);
        TextView taskState = holder.itemView.findViewById(R.id.taskStateFragment);

        taskTitle.setText(holder.task.title);
        taskBody.setText(holder.task.body);
        taskState.setText(holder.task.state);

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }
}
