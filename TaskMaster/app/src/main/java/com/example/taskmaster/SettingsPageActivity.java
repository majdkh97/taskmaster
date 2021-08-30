package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

public class SettingsPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        Button submitUserName = findViewById(R.id.SaveButtonSettingsPage);

        submitUserName.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsPageActivity.this);
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

            EditText userNameField = findViewById(R.id.editTextUserName);
            String userName = userNameField.getText().toString();

            sharedPreferencesEditor.putString("userName",userName);
            sharedPreferencesEditor.apply();
        });
    }
}