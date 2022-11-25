package com.example.foodie_app;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodie_app.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity implements View.OnClickListener {


    EditText password, confirmPassword, email, fName, lName;
    AutoCompleteTextView autoCompleteTextView;
    Button register;
    ProgressBar progressBar;
    TextView banner;

    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);

        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        email = findViewById(R.id.email);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        register = findViewById(R.id.btnRegister);
        register.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        String[] roles = new String[]{"User", "Admin", "Food Critic"};

        //Create an adapter to store the data and display it in the drop-down
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roles);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);

        // Set an item click listener for the AutoCompleteTextView
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from the AutoCompleteTextView
                String selectedItem = (String) parent.getItemAtPosition(position);
                // Display the selected item text on TextView
                Toast.makeText(getApplicationContext(), "Selected : " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, SignIn.class));
                break;

            case R.id.btnRegister:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        String confirmPassword = this.confirmPassword.getText().toString().trim();
        String fName = this.fName.getText().toString().trim();
        String lName = this.lName.getText().toString().trim();
        String role = autoCompleteTextView.getText().toString().trim();

        if (email.isEmpty()) {
            this.email.setError("Email is required");
            this.email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.email.setError("Please provide valid email");
            this.email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            this.password.setError("Password is required");
            this.password.requestFocus();
            return;
        }

        if (password.length() < 6) {
            this.password.setError("Min password length should be 6 characters");
            this.password.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            this.confirmPassword.setError("Confirm password is required");
            this.confirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            this.confirmPassword.setError("Password does not match");
            this.confirmPassword.requestFocus();
            return;
        }

        if (fName.isEmpty()) {
            this.fName.setError("First name is required");
            this.fName.requestFocus();
            return;
        }

        if (lName.isEmpty()) {
            this.lName.setError("Last name is required");
            this.lName.requestFocus();
            return;
        }

        if (role.isEmpty()) {
            autoCompleteTextView.setError("Role is required");
            autoCompleteTextView.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(fName,lName,email,password,role);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Toast.makeText(Register.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                            else{
                                                Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });

                        }
                        else{
                            Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }
    }
