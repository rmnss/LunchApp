package com.hfda.LunchApp.helper;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hfda.LunchApp.R;
import com.hfda.LunchApp.objectClass.TodaysSpecial;
import com.squareup.picasso.Picasso;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TodaySpecialAdapter extends RecyclerView.Adapter<TodaySpecialAdapter.TodaysSpecialViewHolder> {

    private List<TodaysSpecial> todaySpecial;
    private Context context;

    public TodaySpecialAdapter(List<TodaysSpecial> tSpecial, Context context) {
        this.todaySpecial = tSpecial;
        this.context = context;
    }

    static class TodaysSpecialViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView servingDay;
        TextView dishName;
        TextView serveTime;
        TextView price;
        TextView allergies;
        ImageView dishPhoto;
        RelativeLayout rl1;

        TodaysSpecialViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv1);
            servingDay = (TextView) itemView.findViewById(R.id.tvDay1);
            dishName = (TextView) itemView.findViewById(R.id.tvDish1);
            serveTime = (TextView) itemView.findViewById(R.id.tvTime1);
            price = (TextView) itemView.findViewById(R.id.tvPrice1);
            allergies = (TextView) itemView.findViewById(R.id.tvAllergy);
            dishPhoto = (ImageView) itemView.findViewById(R.id.ivMat1);
            rl1 = (RelativeLayout) itemView.findViewById(R.id.rl1);
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

        //building allergy string and printing to screen
        String allergies = "Allergier:";
        ArrayList<String> allergiesList = todaySpecial.get(i).getAllergies();
        int teller = 1;
        for (String allergy : allergiesList) {
            allergies += " " + allergy;

            if (allergiesList.size() > teller) {
                allergies += ", ";
                teller++;
            }
        }
        todaysSpecialViewHolder.allergies.setText(allergies);

        //setting picture
        Picasso.with(context)
                .load(todaySpecial.get(i).getPicture())
                .into(todaysSpecialViewHolder.dishPhoto);




        //getting todays weekday and higligtning todays special card view
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.US);
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        if(todaySpecial.get(i).getServeDay().equals(dayOfTheWeek)){
            todaysSpecialViewHolder.rl1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTodays));
        }
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