package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TaskDetailPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail_page);

        ImageView img =findViewById(R.id.TaskDetailImageView);

        Intent intent = getIntent();
        String taskName = intent.getExtras().getString("taskName");
        TextView taskNameView = findViewById(R.id.TaskDetailName);
        taskNameView.setText(taskName);

        Bundle extras = getIntent().getExtras();
        byte[] b = extras.getByteArray("picture");
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        img.setImageBitmap(bmp);

    }
}