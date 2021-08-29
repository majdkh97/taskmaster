package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AllTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        Button backButton = findViewById(R.id.button2);
        backButton.setOnClickListener(view -> {
            Intent backToMainActivity = new Intent(AllTasks.this,MainActivity.class);
            startActivity(backToMainActivity);
        });
    }
}