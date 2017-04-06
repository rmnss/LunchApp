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

import com.hfda.LunchApp.R;
import com.hfda.LunchApp.helper.LunchDBhelper;

import java.util.ArrayList;
import java.util.List;

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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {

                String returnString = data.getStringExtra("keyName");

                if (returnString.equals(klipp)) {
                    klippNR += 1;
                    TextView textView = (TextView) findViewById(R.id.txtCoffeeNr);
                    textView.setText(klippNR.toString());
                } else if (returnString.equals(nyttKort)) {
                    kortNr += 1;
                    //TextView textView2 = (TextView) findViewById(R.id.textView2);
                    //textView2.setText((kortNr.toString()));
                } else Toast.makeText(this, "Feil kode!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
