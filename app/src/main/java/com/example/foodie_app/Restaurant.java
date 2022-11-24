package com.example.foodie_app;

public class Restaurant extends Eatery {
    public Restaurant(String name, String url, String desc, String user_id, String location, boolean veg, boolean non_veg, float rating, int timesRated){
        super(name, url, desc, user_id, location, veg, non_veg, rating, timesRated);
    }
    public Restaurant(){

    }
}
