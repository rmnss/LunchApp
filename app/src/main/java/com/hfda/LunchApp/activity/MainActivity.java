package com.hfda.LunchApp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hfda.LunchApp.helper.LunchDBhelper;
import com.hfda.LunchApp.helper.SessionManager;

import com.hfda.LunchApp.R;

import java.util.ArrayList;
import java.util.List;

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







    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }



}