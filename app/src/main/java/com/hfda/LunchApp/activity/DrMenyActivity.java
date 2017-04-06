package com.hfda.LunchApp.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfda.LunchApp.R;
import com.hfda.LunchApp.app.AppConfig;
import com.hfda.LunchApp.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DrMenyActivity extends Activity {


    String allergi;
    String menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_meny);
        getDrMenu();

    }


    //Get menu from mySQL
    private void getDrMenu() {
        // Tag used to cancel the request
        String tag_string_req = "req_menu";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_DR_MENU, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Laupet", "Menu Response: " + response);
                String r = "";



                try {

                    TextView menyListe = (TextView)findViewById(R.id.lstView);
                    JSONArray jObj = new JSONArray(response);

                    menu = "";

                    for (int i = 0; i < jObj.length(); i++) {
                        JSONObject row = jObj.getJSONObject(i);


                        menu += "Dagens rett: " + row.getString("navn") + "\n " +
                                "Serveres klokken: " + row.getString("serveringstid") + "\n " +
                                "Student pris: " + row.getString("studentPris") + "\n\n " +
                                "Allergi: \n";



                        getDrMenuAllergy();

                    }


                    Log.d("Laupet", "hei: " + allergi);
                    r+= allergi;

                    Log.d("Laupet", "hei: " + allergi);


                    menyListe.setText(r);











                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Log.d("Laupet", "JSONEXception" + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Laupet", "menu Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) ;

        // Adding request to request queue
        Log.d("Laupet", "getInstance:" + strReq + " - " +  tag_string_req);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }



    public void getDrMenuAllergy() {
        // Tag used to cancel the request
        String tag_string_req = "req_menu";


          StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_DR_MENU_ALLERGI, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Laupet", "Menu Response: " + response);
                String r = "";



                try {

                    TextView menyListe = (TextView)findViewById(R.id.lstView);
                    JSONArray jObjall = new JSONArray(response);


                    allergi = "";
                        for (int i = 0; i < jObjall .length(); i++) {
                            JSONObject row = jObjall .getJSONObject(i);


                            allergi += row.getString("allergi") + "\n ";
Log.d("Laupet", allergi);


                            //)
                    }

                    menyListe.setText(menu + allergi);
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Log.d("Laupet", "JSONEXception" + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Laupet", "menu Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("id", "3");
                return params;
            }
        };

        // Adding request to request queue
        Log.d("Laupet", "getInstance:" + strReq + " - " +  tag_string_req);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }







}
