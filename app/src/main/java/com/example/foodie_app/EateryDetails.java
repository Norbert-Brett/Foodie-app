package com.example.foodie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EateryDetails extends AppCompatActivity {

    ImageView iv_details;
    TextView tv_name_details;
    TextView tv_desc;
    TextView tv_address_details;
    TextView tv_veg;
    TextView tv_nonveg;
    Button btn_view_reviews;
    Button btn_book;
    Button btn_add_reviews;
    RatingBar rb;
    RatingBar rb_set_rating;
    float _rating;
    int _timesRated;
    DatabaseReference dbref;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatery_details);
        iv_details = findViewById(R.id.iv_details);
        tv_name_details = findViewById(R.id.tv_name_details);
        tv_desc = findViewById(R.id.tv_desc);
        tv_address_details = findViewById(R.id.tv_address_details);
        tv_veg = findViewById(R.id.tv_veg);
        tv_nonveg = findViewById(R.id.tv_nonveg);
        btn_view_reviews = findViewById(R.id.btn_view_reviews);
        btn_book = findViewById(R.id.btn_book);
        btn_add_reviews = findViewById(R.id.btn_add_reviews);
        rb = findViewById(R.id.rb);
        rb.setIsIndicator(true);
        rb_set_rating = findViewById(R.id.rb_set_rating);

        //Fetching data from clicked object on recycle view
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String desc = i.getStringExtra("desc");
        String loc = i.getStringExtra("loc");
        String url = i.getStringExtra("url");
        //String userid = i.getStringExtra("userid");
        boolean nonveg = i.getExtras().getBoolean("nonveg");
        boolean veg = i.getExtras().getBoolean("veg");
        int timesRated = i.getExtras().getInt("timesRated");
        float rating = i.getExtras().getFloat("rating");
        _timesRated = timesRated;
        _rating = rating;

        //Assigning values to views
        tv_name_details.setText(name);
        tv_desc.setText(desc);
        tv_address_details.setText(loc);
        Picasso.get().load(url).fit().into(iv_details);
        tv_desc.setText(desc);
        float average = _rating/_timesRated;
        rb.setRating(average);
        if(veg){
            tv_veg.setText("Yes");
        } else{
            tv_veg.setText("No");
        }

        if(nonveg){
            tv_nonveg.setText("Yes");
        } else{
            tv_nonveg.setText("No");
        }

        //Book button starts calendar activity
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EateryDetails.this, Calendar.class);
                startActivity(intent);
            }
        });
        //button to add reviews, not working yet
        btn_add_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EateryDetails.this, AddReview.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
        //setting rating for Eatery
        rb_set_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rb_set_rating.setIsIndicator(true);
                float rait = rb_set_rating.getRating();


                //below data must update firebase records on rating and times rated
                _timesRated = _timesRated++;
                _rating = _rating + rait;
                dbref = FirebaseDatabase.getInstance().getReference();




            }
        });
    }

}