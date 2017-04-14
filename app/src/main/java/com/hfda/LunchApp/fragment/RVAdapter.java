package com.hfda.LunchApp.fragment;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hfda.LunchApp.R;
import com.hfda.LunchApp.objectClass.TodaysSpecial;

import java.util.List;

class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    List<TodaysSpecial> persons;

    RVAdapter(List<TodaysSpecial> persons){
        this.persons = persons;
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView servingDay;
        TextView dishName;
        TextView serveTime;
        TextView price;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv1);
            servingDay = (TextView)itemView.findViewById(R.id.tvDay1);
            dishName = (TextView)itemView.findViewById(R.id.tvDish1);
            serveTime = (TextView)itemView.findViewById(R.id.tvTime1);
            price = (TextView)itemView.findViewById(R.id.tvPrice1);
            personPhoto = (ImageView)itemView.findViewById(R.id.ivMat1);
        }
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todays_special, viewGroup, false);
        return new PersonViewHolder(v);
    }



    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.servingDay.setText(persons.get(i).getServeDay());
        personViewHolder.dishName.setText(persons.get(i).getName());
        personViewHolder.serveTime.setText(persons.get(i).getServingTime());
        personViewHolder.price.setText("Kroner " + persons.get(i).getPrice() + ",-");
        personViewHolder.personPhoto.setImageResource(R.drawable.spag);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        return persons.size();
    }







}