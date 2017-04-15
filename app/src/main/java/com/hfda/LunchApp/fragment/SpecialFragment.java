package com.hfda.LunchApp.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfda.LunchApp.R;
import com.hfda.LunchApp.activity.MainActivity;
import com.hfda.LunchApp.app.AppConfig;
import com.hfda.LunchApp.app.AppController;
import com.hfda.LunchApp.helper.TodaySpecialAdapter;
import com.hfda.LunchApp.objectClass.TodaysSpecial;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.hfda.LunchApp.R.string.specialAction;

public class SpecialFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<TodaysSpecial> todaysSpecialList = new ArrayList<>();

    SwipeRefreshLayout swipeLayout;
    TodaySpecialAdapter adapter;
    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_special, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(specialAction);

        //getting data from mySQL to populate the cardViews
        getDrMenu();

        //creating refresh Swipe layout and connecting it to the recycler view
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container_special);
        swipeLayout.setOnRefreshListener(this);

        //getting todays weekday
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        Log.d("Laupet", dayOfTheWeek);

        return view;
    }


    @Override
    public void onRefresh() {
        //clears the list and tells the recycler view to update/empty
        todaysSpecialList.clear();
        adapter.notifyDataSetChanged();

        //getting updated data from mySQL
        getDrMenu();
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Setting up the recycleView with cardview
        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);
    }


    //Get menu from mySQL
    private void getDrMenu() {
        // Tag used to cancel the request
        String tag_string_req = "req_menu";

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_DR_MENU, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Laupet", "Menu Response: " + response);

                try {
                    JSONArray jObj = new JSONArray(response);


                    for (int i = 0; i < jObj.length(); i++) {
                        JSONObject row = jObj.getJSONObject(i);

                        //getting values from json
                        String id = row.getString("idDRmeny");
                        String name = row.getString("navn");
                        String servingTime = row.getString("serveringstid");
                        String price = row.getString("studentPris");
                        String serveDay = row.getString("dag");
                        String picture = row.getString("picture");

                        TodaysSpecial dish = new TodaysSpecial(id, name, servingTime, price, serveDay, picture);
                        todaysSpecialList.add(dish);
                    }
                    //getting allergies to the menues, and adding them as arraylist to the objects
                    getDrMenuAllergy();

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Log.d("Laupet", "JSONEXception" + e.getMessage());
                    Toast.makeText(getActivity().getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Laupet", "menu Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public void getDrMenuAllergy() {
        // Tag used to cancel the request
        String tag_string_req = "req_allergies";

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_DR_MENU_ALLERGI, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Laupet", "Allergy Response: " + response);

                try {
                    JSONArray jObjall = new JSONArray(response);

                    ArrayList<String> allergies2 = new ArrayList<>();

                    for (int i = 0; i < jObjall.length(); i++) {
                        JSONObject row = jObjall.getJSONObject(i);

                        String menuID = row.getString("menuID");
                        String allergi = row.getString("allergi");

                        //checking what Today's-menu the allergy belongs to
                        for (TodaysSpecial dish : todaysSpecialList) {
                            if (menuID.equals(dish.getId())) {
                                dish.addAllergy(allergi);
                            }
                        }
                    }

                    //giving the recycleview an adapter connected to the todays specials
                    adapter = new TodaySpecialAdapter(todaysSpecialList, getContext());
                    rv.setAdapter(adapter);

                    //Notifies the adapter to update the list
                    adapter.notifyDataSetChanged();

                    //Tells the swipelayout that the refresh is done
                    swipeLayout.setRefreshing(false);

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Log.d("Laupet", "JSONEXception" + e.getMessage());
                    Toast.makeText(getActivity().getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Laupet", "Allergy Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
