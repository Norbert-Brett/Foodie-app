package com.example.foodie_app;

public abstract class Eatery {
    private String name, url, desc, user_id, location;
    private boolean veg, non_veg;
    private float rating;
    private  int timesRated;




    public Eatery(String name, String url, String desc, String user_id, String location, boolean veg, boolean non_veg, float rating, int timesRated) {
        this.name = name;
        this.url = url;
        this.desc = desc;
        this.user_id = user_id;
        this.location = location;
        this.veg = veg;
        this.non_veg = non_veg;
        this.rating = rating;
        this.timesRated = timesRated;
    }

    public Eatery(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }

    public boolean isNon_veg() {
        return non_veg;
    }

    public void setNon_veg(boolean non_veg) {
        this.non_veg = non_veg;
    }

    public float getRating() {return rating;}

    public void setRating(float rating) {this.rating = rating;}

    public int getTimesRated() {return timesRated;}

    public void setTimesRated(int timesRated) {this.timesRated = timesRated;}
}
