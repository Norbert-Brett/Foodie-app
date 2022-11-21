package com.example.foodie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddReview extends AppCompatActivity {
    EditText review;
    Button btn_add_review;
    DatabaseReference dbref;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        review = findViewById(R.id.mlt_review_add_review);
        btn_add_review = findViewById(R.id.btn_add_review);
        dbref = FirebaseDatabase.getInstance().getReference("Restaurant/"+name);


        btn_add_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = dbref.push().getKey();
                Review review = new Review("UserID", "date", "time", "review text");
                dbref.child(id).setValue(review);
                //Intent intent = new Intent(AddReview.this, ViewReview.class)
                btn_add_review.setEnabled(false);
            }
        });



    }
}