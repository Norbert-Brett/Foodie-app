package com.example.foodie_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    RestaurantFragment restaurantFragment = new RestaurantFragment();
    CateringFragment cateringFragment = new CateringFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    StreetFoodFragment streetFoodFragment = new StreetFoodFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, profileFragment).commit();
                    return true;
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, restaurantFragment).commit();
                    return true;
                case R.id.catering:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, cateringFragment).commit();
                    return true;
                case R.id.street_food:
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, streetFoodFragment).commit();
                    return true;
            }
            return false;
        });
    }
}