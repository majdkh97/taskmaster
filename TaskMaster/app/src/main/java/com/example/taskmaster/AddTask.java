package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.HashMap;

public class AddTask extends AppCompatActivity {
    HashMap<String,Team> hashTeam = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        EditText titleET = findViewById(R.id.editText1);
        EditText bodyET = findViewById(R.id.editText2);

//        Team team = Team.builder()
//                .content("team3")
//                .build();
//
//        Amplify.API.mutate(ModelMutation.create(team),
//                response -> Log.i("MyAmplifyApp", "Todo with id: " + response.getData().getId()),
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );
//

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team teamo : response.getData()) {
                        Log.i("MyAmplifyApp", teamo.getId());
                        hashTeam.put(teamo.getContent(),teamo);
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Button addTask = findViewById(R.id.button);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioGroup group = findViewById(R.id.radioGroupteam);
                int id = group.getCheckedRadioButtonId();
                RadioButton team = findViewById(id);
                String radioButton =team.getText().toString();
                System.out.println(hashTeam.get(radioButton));

                String taskTitle=titleET.getText().toString();
                String taskBody =bodyET.getText().toString();

                Task todo = Task.builder()
                        .title(taskTitle)
                        .description(taskBody)
                        .status("new")
                        .team(hashTeam.get(radioButton))
                        .build();

                Amplify.API.mutate(ModelMutation.create(todo),
                        response -> Log.i("MyAmplifyApp", "Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );

//                TaskDatabase taskDB = Room.databaseBuilder(getApplicationContext(),TaskDatabase.class,"TaskDatabase").allowMainThreadQueries().build();
//                Task task = new Task(taskTitle,taskBody,"new");
//                taskDB.taskDao().insertTask(task);
                Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show();
                Intent i = new Intent(AddTask.this,MainActivity.class);
                startActivity(i);
            }
        });


//        addTask.setOnClickListener(view -> Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show());
    }
}