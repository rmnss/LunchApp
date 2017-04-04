package com.hfda.LunchApp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfda.LunchApp.app.AppConfig;
import com.hfda.LunchApp.app.AppController;
import com.hfda.LunchApp.helper.LunchDBhelper;
import com.hfda.LunchApp.helper.SessionManager;

import com.hfda.LunchApp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //Må være i første acitivity
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    //Må være før qr-scanneren
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;

    private SessionManager session;
    private LunchDBhelper db;
    private Button btnLogout;

    private String klipp = "cOFD87zuFbrYdRfKwz5m";
    private Integer klippNR = 0;
    private String nyttKort = "4YzY3vyBEl0gPoxuvCAB";
    private Integer kortNr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkRequestPermission();

        btnLogout = (Button) findViewById(R.id.btnLogOut);
        session = new SessionManager(getApplicationContext());

        //Creates database handler
        db = new LunchDBhelper(getApplicationContext());



        getMenu();


        // Logout button Click Event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                logoutUser();
            }
        });
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

    public void onClickQR(View v) {
        if (checkRequestPermission()) {
            //Intent i = new Intent(getApplicationContext(), QRActivity.class);
            //startActivity(i);
            Intent intent = new Intent(this, QRActivity.class);
            startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
        } else
            Toast.makeText(this, "Godkjenn kamera-appen og trykk igjen", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {

                String returnString = data.getStringExtra("keyName");

                if (returnString.equals(klipp)) {
                    klippNR += 1;
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(klippNR.toString());
                } else if (returnString.equals(nyttKort)) {
                    kortNr += 1;
                    TextView textView2 = (TextView) findViewById(R.id.textView2);
                    textView2.setText((kortNr.toString()));
                } else Toast.makeText(this, "Feil kode!", Toast.LENGTH_LONG).show();
            }
        }
    }


















    //Get menu from mySQL
    private void getMenu() {
        // Tag used to cancel the request
        String tag_string_req = "req_menu";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_MENU, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d("Laupet", "Login Response: " + response.toString());

                try {
                    JSONArray jObj = new JSONArray(response);


                    for (int i = 0; i < jObj.length(); i++) {
                        JSONObject row = jObj.getJSONObject(i);
                        Log.d("Laupet", "id: " + row.getInt("idMenu"));
                        Log.d("Laupet", "merke: " + row.getString("merke"));
                        Log.d("Laupet", "type: " + row.getString("type"));
                        Log.d("Laupet", "studentPris: " + row.getString("studentPris"));
                        Log.d("Laupet", "ansattPris: " + row.getString("ansattPris"));
                        Log.d("Laupet", "ALERGIER - GETALLERGI FRA DATABASEN");
                        Log.d("Laupet", "-----------");
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
                Log.e("Laupet", "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", "test");
                params.put("password", "test");

                return params;
            }
        };

        // Adding request to request queue
        Log.d("Laupet", "getInstance:" + strReq + " - " +  tag_string_req);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }






    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
