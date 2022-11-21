package com.example.foodie_app.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodie_app.Eatery;
import com.example.foodie_app.R;
import com.example.foodie_app.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EateryAdaptor extends RecyclerView.Adapter<EateryAdaptor.EateryHolder>{

    private ArrayList<Eatery> list;
    EateryHolder.EateryInterface listener;

    public EateryAdaptor(ArrayList<Eatery> list, EateryHolder.EateryInterface _listener) {
        this.list = list;
        listener = _listener;
    }

    @NonNull
    @Override
    public EateryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eaterycard, parent, false);
        return new EateryHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EateryHolder holder, int position) {

        holder.tveateryname.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getUrl()).fit().into(holder.ivcardimage);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class EateryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivcardimage;
        TextView tveateryname;
        EateryInterface listener;
        public EateryHolder(@NonNull View itemView, EateryInterface _listener) {
            super(itemView);
            ivcardimage = itemView.findViewById(R.id.iv_cardimage);
            tveateryname = itemView.findViewById(R.id.tv_cardname);
            listener = _listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onEateryClick(getAdapterPosition());

        }

        public interface EateryInterface{
            public void onEateryClick (int i);

        }
    }
}
