package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TaskDatabase taskDB = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "TaskDatabase").allowMainThreadQueries().build();
        TaskDao taskDao = taskDB.taskDao();
        List<Task> allTasksData = taskDao.getTaskList();
        //get the recycler view Lab28
        RecyclerView allTasksRecyclerView = findViewById(R.id.taskListRecyclerView);
        //set layout manager for this view Lab28
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //set adapter for this recycler view Lab28
        allTasksRecyclerView.setAdapter(new TaskAdapter(allTasksData));


        Button addTask = MainActivity.this.findViewById(R.id.addtask);
        Button allTasks = MainActivity.this.findViewById(R.id.alltask);

        Button settingsActivityBtn = MainActivity.this.findViewById(R.id.SettingsActivityBtn);
        settingsActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingsPage = new Intent(MainActivity.this,SettingsPageActivity.class);
                startActivity(goToSettingsPage);
            }
        });


        Button taskDetailPageActivityBtn1 = MainActivity.this.findViewById(R.id.angrypotato);
        Button taskDetailPageActivityBtn2 = MainActivity.this.findViewById(R.id.sadpotato);
        Button taskDetailPageActivityBtn3 = MainActivity.this.findViewById(R.id.potatoking);

        String btn1Text = taskDetailPageActivityBtn1.getText().toString();
        String btn2Text = taskDetailPageActivityBtn2.getText().toString();
        String btn3Text = taskDetailPageActivityBtn3.getText().toString();


        taskDetailPageActivityBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTaskDetailPageActivity = new Intent(MainActivity.this,TaskDetailPageActivity.class);
                goToTaskDetailPageActivity.putExtra("taskName",btn1Text);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.angrypotato);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                goToTaskDetailPageActivity.putExtra("picture",b);

                startActivity(goToTaskDetailPageActivity);
            }
        });

        taskDetailPageActivityBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTaskDetailPageActivity = new Intent(MainActivity.this,TaskDetailPageActivity.class);
                goToTaskDetailPageActivity.putExtra("taskName",btn2Text);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sadpotato);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                goToTaskDetailPageActivity.putExtra("picture",b);

                startActivity(goToTaskDetailPageActivity);
            }
        });

        taskDetailPageActivityBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTaskDetailPageActivity = new Intent(MainActivity.this,TaskDetailPageActivity.class);
                goToTaskDetailPageActivity.putExtra("taskName",btn3Text);

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.potatoking);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                goToTaskDetailPageActivity.putExtra("picture",b);

                startActivity(goToTaskDetailPageActivity);
            }
        });

        addTask.setOnClickListener(view -> {
            Intent goToAddTask = new Intent(MainActivity.this,AddTask.class);
            startActivity(goToAddTask);
        });

        allTasks.setOnClickListener(view -> {
            Intent goToAllTasks = new Intent(MainActivity.this,AllTasks.class);
            startActivity(goToAllTasks);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName","username");

        TextView userNameView = findViewById(R.id.userNameTextViewMainActivity);
        String welcomeMessage = "Welcome " + userName;
        userNameView.setText(welcomeMessage);
    }
}