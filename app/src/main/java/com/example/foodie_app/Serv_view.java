package com.example.foodie_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Serv_view extends AppCompatActivity {
    private Button book_btn;
    private Button review_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serv_view);
        getSupportActionBar().hide();


        ImageView img2;
        TextView tv2, dv2;

        review_btn = (Button) findViewById(R.id.review_btn);
        review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReviewActivity();
            }
        });

        book_btn = (Button) findViewById(R.id.book_btn);
        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendarActivity();

            }
        });
        img2 = findViewById(R.id.img2);
        tv2 = findViewById(R.id.tv2);
        dv2 = findViewById(R.id.dv2);


        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String details = intent.getExtras().getString("details");
        int pic = intent.getExtras().getInt("pic");

        tv2.setText(title);
        dv2.setText(details);
        img2.setImageResource(pic);

    }

    public void openReviewActivity() {
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
    }

    public void openCalendarActivity() {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
