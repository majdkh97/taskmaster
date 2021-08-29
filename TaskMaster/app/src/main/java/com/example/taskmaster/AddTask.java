package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addTask = findViewById(R.id.button);
        addTask.setOnClickListener(view -> Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show());
    }
}