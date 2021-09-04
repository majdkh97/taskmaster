package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        EditText titleET = findViewById(R.id.editText1);
        EditText bodyET = findViewById(R.id.editText2);


        Button addTask = findViewById(R.id.button);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskTitle=titleET.getText().toString();
                String taskBody =bodyET.getText().toString();

                TaskDatabase taskDB = Room.databaseBuilder(getApplicationContext(),TaskDatabase.class,"TaskDatabase").allowMainThreadQueries().build();
                Task task = new Task(taskTitle,taskBody,"new");
                taskDB.taskDao().insertTask(task);
                Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddTask.this,MainActivity.class);
                startActivity(i);
            }
        });
//        addTask.setOnClickListener(view -> Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show());
    }
}