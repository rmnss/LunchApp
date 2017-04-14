package com.hfda.LunchApp.helper;


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

public class TodaySpecialAdapter extends RecyclerView.Adapter<TodaySpecialAdapter.TodaysSpecialViewHolder>{

    private List<TodaysSpecial> todaySpecial;

    public TodaySpecialAdapter(List<TodaysSpecial> tSpecial){
        this.todaySpecial = tSpecial;
    }


    static class TodaysSpecialViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView servingDay;
        TextView dishName;
        TextView serveTime;
        TextView price;
        ImageView dishPhoto;

        TodaysSpecialViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv1);
            servingDay = (TextView)itemView.findViewById(R.id.tvDay1);
            dishName = (TextView)itemView.findViewById(R.id.tvDish1);
            serveTime = (TextView)itemView.findViewById(R.id.tvTime1);
            price = (TextView)itemView.findViewById(R.id.tvPrice1);
            dishPhoto = (ImageView)itemView.findViewById(R.id.ivMat1);
        }
    }


    @Override
    public TodaysSpecialViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todays_special, viewGroup, false);
        return new TodaysSpecialViewHolder(v);
    }


    @Override
    public void onBindViewHolder(TodaysSpecialViewHolder todaysSpecialViewHolder, int i) {
        todaysSpecialViewHolder.servingDay.setText(todaySpecial.get(i).getServeDay());
        todaysSpecialViewHolder.dishName.setText(todaySpecial.get(i).getName());
        todaysSpecialViewHolder.serveTime.setText(todaySpecial.get(i).getServingTime());
        todaysSpecialViewHolder.price.setText("Kroner " + todaySpecial.get(i).getPrice() + ",-");
        todaysSpecialViewHolder.dishPhoto.setImageResource(R.drawable.spag);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        return todaySpecial.size();
    }







}