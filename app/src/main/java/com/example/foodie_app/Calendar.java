package com.example.foodie_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

public class Calendar extends AppCompatActivity {
    CalendarView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        cv = findViewById(R.id.cv);



        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i1 = i1+1;
                String link = "https://www.opentable.com/s/?covers=2&dateTime="+i+"-"+i1+"-"+i2+"%"+i+
                        "%3A00&metroId=72&regionIds=5316&pinnedRids%5B0%5D=87967&enableSimpleCuisines" +
                        "=true&includeTicketedAvailability=true&pageType=0";
                Context context = getApplicationContext();

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, "Navigating to Opentable website", duration);
                toast.show();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
    }
}





//https://www.opentable.com/s/?covers=2&dateTime=   2019-02-25%2019%3A00&metroId=72&regionIds=5316&pinnedRids%5B0%5D=87967&enableSimpleCuisines=true&includeTicketedAvailability=true&pageType=0