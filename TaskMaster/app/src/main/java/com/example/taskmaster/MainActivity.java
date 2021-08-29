package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTask = MainActivity.this.findViewById(R.id.addtask);
        Button allTasks = MainActivity.this.findViewById(R.id.alltask);

        addTask.setOnClickListener(view -> {
            Intent goToAddTask = new Intent(MainActivity.this,AddTask.class);
            startActivity(goToAddTask);
        });

        allTasks.setOnClickListener(view -> {
            Intent goToAllTasks = new Intent(MainActivity.this,AllTasks.class);
            startActivity(goToAllTasks);
        });

    }
}