package com.hfda.LunchApp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfda.LunchApp.R;
import com.hfda.LunchApp.app.AppConfig;
import com.hfda.LunchApp.app.AppController;
import com.hfda.LunchApp.helper.LunchDBhelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hfda.LunchApp.activity.MainActivity.MY_PERMISSIONS_REQUEST_CAMERA;

public class CoffeeActivity extends Activity {

    //Må være før qr-scanneren
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;

    private TextView coffeNr;
    //private String klipp = "cOFD87zuFbrYdRfKwz5m";
    //private String nyttKort = "4YzY3vyBEl0gPoxuvCAB";
    private String klipp;
    private String nyttKort;
    private LunchDBhelper db;

    private Integer klippNR = 0;
    private Integer kortNr = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        coffeNr = (TextView) findViewById(R.id.txtCoffeeNr);



        //Creates database handler
        db = new LunchDBhelper(getApplicationContext());


        //Checks permissions on camera
        checkRequestPermission();

        //gets number of coffee the user has
        int coffee = db.getCoffee();

        coffeNr.setText(Integer.toString(coffee));




    }














    //sjekker kameratillatelser, hvis kameraet ikke er tillat vil den spørre om det
    private boolean checkRequestPermission() {
        int camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MY_PERMISSIONS_REQUEST_CAMERA);
            return false;
        }
        return true;
    }




    //Onclickevent for qrscanner
    public void onClickQR(View v) {
        if (checkRequestPermission()) {
            Intent intent = new Intent(this, QRActivity.class);
            startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
        } else
            Toast.makeText(this, "Godkjenn kamera-appen og trykk igjen", Toast.LENGTH_LONG).show();
    }



    //QRscanner returns code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {

                String returnString = data.getStringExtra("keyName");



                //BUY COFFEE
                //cOFD87zuFbrYdRfKwz5m

                //Refill
                //4YzY3vyBEl0gPoxuvCAB
                //db.getUuid()
                Log.d("Laupet", "Nu kjører vi");

                String uuid = db.getUuid();
                Log.d("Laupet", "Nu kjører vi");

                buyCoffee(uuid, returnString);
                Log.d("Laupet", "Nu kjører vi");

                //db.takeCoffee(20);
                Log.d("Laupet", "Nu kjører vi");

                Integer nrCoffee = db.getCoffee();

                //coffeNr.setText(Integer.toString(nrCoffee));
                Log.d("Laupet", "Text updated");


/*
                if (returnString.equals(klipp)) {
                    klippNR += 1;
                    TextView textView = (TextView) findViewById(R.id.txtCoffeeNr);
                    textView.setText(klippNR.toString());
                } else if (returnString.equals(nyttKort)) {
                    kortNr += 1;
                    //TextView textView2 = (TextView) findViewById(R.id.textView2);
                    //textView2.setText((kortNr.toString()));
                }

                */
            }
        }
    }







    //Get menu from mySQL
    private void buyCoffee(final String uuid, final String qr) {
        // Tag used to cancel the request
        String tag_string_req = "req_menu";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_BUY_COFFEE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Laupet", "BUY Coffee Response: " + response.toString());
                String r = "";



                try {

                    //TextView menyListe = (TextView)findViewById(R.id.lstView);
                    //JSONArray jObj = new JSONArray(response);

                    JSONObject obj = new JSONObject(response);



                    coffeNr.setText(obj.getString("coffee"));


                    //JSONObject nrCoffee1 = obj.getString("coffee");

                    //Log.d("Laupet", nrCoffee1.getString("coffee"))


                        //Log.d("Laupet", "STRING: " + obj.getString("coffee"));

                   // for (int i = 0; i < jObj.length(); i++) {
                      //  JSONObject row = jObj.getJSONObject(i);






                        //Log.d("Laupet", "Coffenr: " + row.getString("coffee"));

                        //coffeNr.setText(row.getString("coffee"));

/*
                        r += "merke: " + row.getString("merke") + "\n " +
                                "type: " + row.getString("type") + "\n " +
                                "studentPris: " + row.getString("studentPris") + "\n " +
                                "Allergi: \n\n";

                        menyListe.setText(r);
                        */
                    //}

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
                params.put("uuid", uuid);
                params.put("qrString", qr);
                return params;
            }
        };

        // Adding request to request queue
        Log.d("Laupet", "getInstance:" + strReq + " - " +  tag_string_req);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }






















}
