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
    private LunchDBhelper db;
    private String scanType;





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








    //checking camera permissions. Asking for permission if necessary
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

    //Onclickevent for qr buy coffee
    public void onClickBuyCoffee(View v) {
        Log.d("Laupet", "ClickEvent for buyCoffee");
        if (checkRequestPermission()) {
            Intent intent = new Intent(this, QRActivity.class);
            intent.putExtra("scanType","buy");
            startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
        } else
            Toast.makeText(this, "Godkjenn kamera-appen og trykk igjen", Toast.LENGTH_LONG).show();


    }

    //Onclickevent for qr refill coffee card
    public void onClickRefillPunch(View v) {
        Log.d("Laupet", "ClickEvent for refillCoffee");

        if (checkRequestPermission()) {
            Intent intent = new Intent(this, QRActivity.class);
            intent.putExtra("scanType","refill");

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
                scanType = data.getStringExtra("scanType");

                //Gets the users uuid from sqlLite
                String uuid = db.getUuid();

                //checking if the user want to buy a coffee or refill the coffee punch card
                if (scanType.equals("buy")){

                    //Starting the process for buying a coffee
                    buyCoffee(uuid, returnString);

                }else if (scanType.equals("refill")){

                    //Starting the process for refill coffee
                    buyCoffee(uuid, returnString);
                }


                //Updates the sqlLite DB with correct coffeenumber
                Log.d("Laupet", "Kaffe i sqlite: " + db.getCoffee().toString());

            }
        }
    }



    //Sending request to backend for buying coffee
    private void buyCoffee(final String uuid, final String qr) {
        // Tag used to cancel the request
        String tag_string_req = "buy_coffee";
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_BUY_COFFEE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Laupet", "BUY Coffee Response: " + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    boolean error = obj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        //:TODO Give visual feedback if OK. Green screen? Big V?
                        //Buying coffee is OK
                        int coffee = obj.getInt("coffee");
                        db.takeCoffee(coffee);

                        coffeNr.setText(Integer.toString(coffee));

                    }else{
                        // Error when buying coffee. Get the error message
                        String errorMsg = obj.getString("error_msg");
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                    }

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
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }






















}
