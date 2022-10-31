package com.example.foodie_app;


import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView coursesGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coursesGV = findViewById(R.id.idGVcourses);
        ArrayList<CourseModel> courseModelArrayList = new ArrayList<CourseModel>();

        courseModelArrayList.add(new CourseModel("Restaurant 1", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 2", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 3", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 4", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 5", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 6", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 7", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 8", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 9", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 10", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 11", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 12", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 13", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 14", R.drawable.pic));
        courseModelArrayList.add(new CourseModel("Restaurant 15", R.drawable.pic));
        CourseGVAdapter adapter = new CourseGVAdapter(this, courseModelArrayList);
        coursesGV.setAdapter(adapter);
    }
}