package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        EditText signInEmail = findViewById(R.id.SignInEmail);
        EditText signInPass  = findViewById(R.id.SignInPassword);

        Button SignInBtn = findViewById(R.id.signInButton);
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signInEmail.getText().toString();
                String password = signInPass.getText().toString();
                Amplify.Auth.signIn(
                        email,
                        password,
                        result -> {
                            Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                            Intent goToMainActivity = new Intent(SignIn.this,MainActivity.class);
                            startActivity(goToMainActivity);
                            },
                        error -> Log.e("AuthQuickstart", error.toString())
                );

            }
        });

        Button RegisterBtn = findViewById(R.id.RegisterBtn);
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignUpActivity = new Intent(SignIn.this,SignUp.class);
                startActivity(goToSignUpActivity);
            }
        });
    }
}