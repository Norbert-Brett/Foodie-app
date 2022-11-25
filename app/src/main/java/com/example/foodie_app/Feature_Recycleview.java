package com.example.foodie_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Feature_Recycleview extends RecyclerView.Adapter<Feature_Recycleview.ViewHolder> {

    Context context;
    ArrayList<Feature_service> arrayList = new ArrayList<>();
    private int position;


    public Feature_Recycleview(Context context, ArrayList<Feature_service>arrayList) {

        this.context = context;
        this.arrayList = arrayList;

    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.id.d1, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.imageView.setImageResource(arrayList.get(position).getImg());
        holder.textView1.setText(arrayList.get(position).getT1());
        holder.textView2.setText(arrayList.get(position).getD1());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Service"+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, Serv_view.class );
                intent.putExtra("pic", arrayList.get(position).getImg());
                intent.putExtra("title", arrayList.get(position).getT1());
                intent.putExtra("details", arrayList.get(position).getD1());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1, textView2;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            textView1 = itemView.findViewById(R.id.t1);
            textView2 = itemView.findViewById(R.id.d1);

            cardView = itemView.findViewById(R.id.single_service);
        }
    }
}
