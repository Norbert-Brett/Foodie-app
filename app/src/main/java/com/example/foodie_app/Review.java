package com.example.foodie_app;

public class Review {
    private   String userid, date, time, reviewText;

    public Review() {
    }

    public Review(String userid, String date, String time, String reviewText) {
        this.userid = userid;
        this.date = date;
        this.time = time;
        this.reviewText = reviewText;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}






