package com.hfda.LunchApp.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfda.LunchApp.R;
import com.hfda.LunchApp.activity.MainActivity;
import com.hfda.LunchApp.activity.QRActivity;
import com.hfda.LunchApp.app.AppConfig;
import com.hfda.LunchApp.app.AppController;
import com.hfda.LunchApp.helper.LunchDBhelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.hfda.LunchApp.activity.MainActivity.MY_PERMISSIONS_REQUEST_CAMERA;



public class CoffeeFragment extends Fragment {

    private LunchDBhelper db;
    private String qrString;
    private EditText etPin;
    private TextView coffeNr;
    private ImageView imgTest;

    //Used by QR scacnner
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coffee, container, false);

        //getActivity().getActionBar().setTitle("Coffee Card");
       // ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Coffee Card");
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Coffee Card");

        coffeNr = (TextView) view.findViewById(R.id.txtCoffeeNr);
        etPin = (EditText) view.findViewById(R.id.etPin);
        imgTest = (ImageView) view.findViewById(R.id.imgTest);


        //Creates database handler
        db = new LunchDBhelper(getActivity().getApplicationContext());

        //Checks permissions on camera
        checkRequestPermission();

        return view;
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Setting number of coffee to the screen
        int coffee = db.getCoffee();
        coffeNr.setText("x " + Integer.toString(coffee));

        // listner for pin EditText. This to automatically submit when 4 numbers have been written
        etPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                enableSubmitIfReady();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        //Clickevent for button refill
        final ImageButton btnBuy = (ImageButton) view.findViewById(R.id.btnQR);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickBuyCoffe();
            }
        });

        //Clickevent for button refill
        final ImageButton btnRefill = (ImageButton) view.findViewById(R.id.btnRefill);
        btnRefill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickRefillPunch();
            }
        });
    }


    //function for pin edit text field. Automaticly submit when 4 numbers have been entered
    public void enableSubmitIfReady() {

        boolean isReady = etPin.getText().toString().length() > 3;
        if (isReady) {

            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etPin.getWindowToken(), 0);

            //Gets the users uuid from sqlLite
            String uuid = db.getUuid();

            etPin.setVisibility(View.INVISIBLE);

            //getting pin from input
            String pin = etPin.getText().toString();
            etPin.setText("");

            //starting refill coffee card process
            refillCoffee(uuid, qrString, pin);
        }


    }


    //checking camera permissions. Asking for permission if necessary
    private boolean checkRequestPermission() {
        int camera = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MY_PERMISSIONS_REQUEST_CAMERA);
            return false;
        }
        return true;
    }


    //Onclickevent for qr buy coffee
    public void onClickBuyCoffe() {

        if (checkRequestPermission()) {
            Intent intent = new Intent(getActivity(), QRActivity.class);
            intent.putExtra("scanType", "buy");
            startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
        } else
            Toast.makeText(getActivity(), "Godkjenn kamera-appen og trykk igjen", Toast.LENGTH_LONG).show();
    }

    //Onclickevent for qr refill coffee card
    public void onClickRefillPunch() {

        if (checkRequestPermission()) {
            Intent intent = new Intent(getActivity(), QRActivity.class);
            intent.putExtra("scanType", "refill");

            startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);
        } else
            Toast.makeText(getActivity(), "Godkjenn kamera-appen og trykk igjen", Toast.LENGTH_LONG).show();
    }


    //QRscanner returns code
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {

                //Getting values from qr activity
                qrString = data.getStringExtra("keyName");
                String scanType = data.getStringExtra("scanType");

                //checking if the user want to buy a coffee or refill the coffee punch card
                if (scanType.equals("buy")) {

                    //Gets the users uuid from sqlLite
                    String uuid = db.getUuid();

                    //Starting the process for buying a coffee
                    buyCoffee(uuid, qrString);
                    etPin.setVisibility(View.INVISIBLE);

                } else if (scanType.equals("refill")) {

                    //Makes pin edit text visible and forces keyboard to open
                    etPin.setVisibility(View.VISIBLE);
                    etPin.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
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
                TextView coffeNr = (TextView) getView().findViewById(R.id.txtCoffeeNr);


                try {
                    JSONObject obj = new JSONObject(response);

                    boolean error = obj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        //Buying coffee is OK
                        int coffee = obj.getInt("coffee");
                        db.setCoffee(coffee);

                        coffeNr.setText("x" + Integer.toString(coffee));

                        Toast.makeText(getActivity().getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();

                        //showing feedback icon
                        giveFeedback(true);

                    } else {
                        // Error when buying coffee. Get the error message
                        String errorMsg = obj.getString("error_msg");
                        Toast.makeText(getActivity().getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();

                        //showing feedback icon
                        giveFeedback(false);
                    }

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


    //Sending request to backend for refilling coffe punch card
    private void refillCoffee(final String uuid, final String qr, final String pin) {
        // Tag used to cancel the request
        String tag_string_req = "refill_coffee";
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REFILL_COFFEE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                TextView coffeNr = (TextView) getView().findViewById(R.id.txtCoffeeNr);

                Log.d("Laupet", "refill Coffee punch card resonse: " + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    boolean error = obj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        //Buying coffee is OK
                        int coffee = obj.getInt("coffee");
                        db.setCoffee(coffee);

                        coffeNr.setText("x " + Integer.toString(coffee));

                        Toast.makeText(getActivity().getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();

                        //showing feedback icon
                        giveFeedback(true);

                    } else {
                        // Error when refilling coffee card Get the error message
                        String errorMsg = obj.getString("error_msg");
                        Toast.makeText(getActivity().getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();

                        //showing feedback icon
                        giveFeedback(false);

                    }

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
                Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("uuid", uuid);
                params.put("qrString", qr);
                params.put("pin", pin);
                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    //Showing feedback icon and then fading out
    private void giveFeedback(boolean success) {

        if (success) {
            imgTest.setImageResource(R.drawable.succsess);
        } else {
            imgTest.setImageResource(R.drawable.error);
        }

        imgTest.setVisibility(View.VISIBLE);

        //Code for fading out
        Animation fadeOut = new AlphaAnimation(1, 0);  // the 1, 0 here notifies that we want the opacity to go from opaque (1) to transparent (0)
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(1400); // Start fading out after 1400 milli seconds
        fadeOut.setDuration(700); // Fadeout duration should be 700 milli seconds

        imgTest.setAnimation(fadeOut);
        imgTest.setVisibility(View.INVISIBLE);

    }
}