
package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

import java.io.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.List;

import com.amplifyframework.datastore.generated.model.Task;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView allTasksRecyclerView = findViewById(R.id.taskListRecyclerView);
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                allTasksRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });



        Amplify.Auth.fetchUserAttributes(
                attributes -> Log.i("AuthDemo", "User attributes = " + attributes.get(attributes.size()-1).getValue()),
                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
        );


        List<Task> allTasksData = new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    for (Task task : response.getData()) {
                        Log.i("MyAmplifyApp", task.getTitle());
                        allTasksData.add(task);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );



//        TaskDatabase taskDB = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, "TaskDatabase").allowMainThreadQueries().build();
//        TaskDao taskDao = taskDB.taskDao();
//        List<Task> allTasksData = taskDao.getTaskList();
        //get the recycler view Lab28
//        RecyclerView allTasksRecyclerView = findViewById(R.id.taskListRecyclerView);
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

        String userName= Amplify.Auth.getCurrentUser().getUsername();
        TextView userNameView = findViewById(R.id.userNameTextViewMainActivity);
        String welcomeMessage = "Welcome " + userName;
        userNameView.setText(welcomeMessage);

        Button taskDetailPageActivityBtn1 = MainActivity.this.findViewById(R.id.angrypotato);
        Button taskDetailPageActivityBtn2 = MainActivity.this.findViewById(R.id.sadpotato);
        Button taskDetailPageActivityBtn3 = MainActivity.this.findViewById(R.id.potatoking);

        String btn1Text = taskDetailPageActivityBtn1.getText().toString();
        String btn2Text = taskDetailPageActivityBtn2.getText().toString();
        String btn3Text = taskDetailPageActivityBtn3.getText().toString();


        taskDetailPageActivityBtn1.setOnClickListener(view -> {
            Intent goToTaskDetailPageActivity = new Intent(MainActivity.this,TaskDetailPageActivity.class);
            goToTaskDetailPageActivity.putExtra("taskName",btn1Text);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.angrypotato);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            goToTaskDetailPageActivity.putExtra("picture",b);

            startActivity(goToTaskDetailPageActivity);
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

        Button signOutBtn = findViewById(R.id.SignOutBtn);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amplify.Auth.signOut(
                        () -> {
                            Log.i("AuthQuickstart", "Signed out successfully");
                            Intent goBackToSignInActivity = new Intent(MainActivity.this,SignIn.class);
                            startActivity(goBackToSignInActivity);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//        String userName = sharedPreferences.getString("userName","username");
//
//        TextView userNameView = findViewById(R.id.userNameTextViewMainActivity);
//        String welcomeMessage = "Welcome " + userName;
//        userNameView.setText(welcomeMessage);
    }
}