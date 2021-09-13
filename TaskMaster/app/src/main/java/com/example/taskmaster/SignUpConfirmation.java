package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class SignUpConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_confirmation);

        Intent intent = getIntent();
        String username = intent.getExtras().getString("username");
        TextView userNameTextView = findViewById(R.id.UserNameTextView);
        userNameTextView.setText(username);

//        try {
//            // Add these lines to add the AWSApiPlugin plugins
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("MyAmplifyApp", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
//        }

        EditText confirmationCodeEditText = findViewById(R.id.confirmationCodeEditText);

        Button confirmButton = findViewById(R.id.ConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String confirmationCode = confirmationCodeEditText.getText().toString();
                Amplify.Auth.confirmSignUp(
                        username,
                        confirmationCode,
                        result -> {Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                        Intent goToSignInActivity = new Intent(SignUpConfirmation.this,SignIn.class);
                        startActivity(goToSignInActivity);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );

            }
        });
    }
}