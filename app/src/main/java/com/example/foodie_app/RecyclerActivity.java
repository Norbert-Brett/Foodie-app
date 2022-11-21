package com.example.foodie_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodie_app.Adapters.EateryAdaptor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RecyclerActivity extends AppCompatActivity implements EateryAdaptor.EateryHolder.EateryInterface {

    RecyclerView rv;
    ArrayList<Eatery> list = new ArrayList<>();
    DatabaseReference dbref;
    EateryAdaptor adaptor;
    String fb_node;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rv = findViewById(R.id.rv_eateries);
        rv.setLayoutManager( new LinearLayoutManager(RecyclerActivity.this));
        fb_node = getIntent().getStringExtra("Restaurant");
        dbref = FirebaseDatabase.getInstance().getReference("Restaurant");
        dbref.addListenerForSingleValueEvent(listener);
    }
    //dashboard must be adjusted to put extras, else hardcode the names (Restaurant, Catering, StreetFood)
    ValueEventListener listener = new ValueEventListener(){
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot){
            for(DataSnapshot dss: snapshot.getChildren()){

                if(fb_node.equals("Restaurant"))
                {
                    list.add(dss.getValue(Restaurant.class));
                }
                else if (fb_node.equals("Catering"))
                {
                    list.add(dss.getValue(Catering.class));
                }
                else if (fb_node.equals("Street"))
                {
                    list.add(dss.getValue(StreetFood.class));
                }
            }
            //Sorting the array list in alphabetical order before passing it to the adaptor
            Collections.sort(list, new Comparator<Eatery>() {
                @Override
                public int compare(Eatery eatery, Eatery t1) {
                    return eatery.getName().compareToIgnoreCase(t1.getName());
                }
            });

            adaptor = new EateryAdaptor(list, RecyclerActivity.this);
            rv.setAdapter(adaptor);

        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };

    @Override
    public void onEateryClick(int i) {
        Intent intent = new Intent(RecyclerActivity.this, EateryDetails.class);
        intent.putExtra("name", list.get(i).getName());
        intent.putExtra("desc", list.get(i).getDesc());
        intent.putExtra("loc", list.get(i).getLocation());
        intent.putExtra("url", list.get(i).getUrl());
        intent.putExtra("userid", list.get(i).getUser_id());
        intent.putExtra("nonveg", list.get(i).isNon_veg());
        intent.putExtra("veg", list.get(i).isVeg());
        intent.putExtra("rating", list.get(i).getRating());
        intent.putExtra( "timesRated", list.get(i).getTimesRated());
        startActivity(intent);
    }

}
