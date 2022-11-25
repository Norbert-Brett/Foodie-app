package com.example.foodie_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    EditText email, password;
    Button signIn, signUp , forgotPassword;

    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        signIn = findViewById(R.id.btnSignIn);
        signUp = findViewById(R.id.tvSignUp);

        // Sign in with email and password and check if the user exists else send an error message

        signIn.setOnClickListener(v -> {
            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            // Check if the user exists in the database

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                email.setError("Please enter your email");
                password.setError("Please enter your password");
            // check email format and password length
            } else if (!emailText.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                email.setError("Please enter a valid email");
            } else if (passwordText.length() < 6) {
                password.setError("Password must be at least 6 characters");
            } else {
                // Sign in with email and password
                mAuth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(SignIn.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                email.setError("Email or password is incorrect");
                            }
                        });
            }
        });
        //launch sign up activity
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(signUp.getContext(), Register.class);
            startActivity(intent);
            finish();
        });


    }
}