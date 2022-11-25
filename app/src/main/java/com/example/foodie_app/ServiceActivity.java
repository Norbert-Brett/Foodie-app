package com.example.assigment_1foodieapp;

import static com.example.assigment_1foodieapp.R.drawable.service_1;
import static com.example.assigment_1foodieapp.R.drawable.service_10;
import static com.example.assigment_1foodieapp.R.drawable.service_2;
import static com.example.assigment_1foodieapp.R.drawable.service_3;
import static com.example.assigment_1foodieapp.R.drawable.service_4;
import static com.example.assigment_1foodieapp.R.drawable.service_5;
import static com.example.assigment_1foodieapp.R.drawable.service_6;
import static com.example.assigment_1foodieapp.R.drawable.service_7;
import static com.example.assigment_1foodieapp.R.drawable.service_8;
import static com.example.assigment_1foodieapp.R.drawable.service_9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Feature_service> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        recyclerView = findViewById(R.id.recycleViewService);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        arrayList.add(new Feature_service((service_1), "The Social Pantry"));
        arrayList.add(new Feature_service((service_2), "Ampersand Events"));
        arrayList.add(new Feature_service((service_3), "Rose & Food "));
        arrayList.add(new Feature_service((service_4), "Chilli Bees"));
        arrayList.add(new Feature_service((service_5), "Bartlett Mitchell"));
        arrayList.add(new Feature_service((service_6), "Rocket Food"));
        arrayList.add(new Feature_service((service_7), "Après Food")) ;
        arrayList.add(new Feature_service((service_8), "Sabel Food’"));
        arrayList.add(new Feature_service((service_9), "Penni Black"));
        arrayList.add(new Feature_service((service_10), "Eden Caterers"));

        Feature_Recycleview feature_recycleview = new Feature_Recycleview(this, arrayList );
        recyclerView.setAdapter(feature_recycleview);




    }
}