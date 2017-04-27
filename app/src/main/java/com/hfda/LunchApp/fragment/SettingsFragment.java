package com.hfda.LunchApp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfda.LunchApp.R;
import com.hfda.LunchApp.activity.MainActivity;
import com.hfda.LunchApp.app.AppConfig;
import com.hfda.LunchApp.app.AppController;
import com.hfda.LunchApp.helper.LunchDBhelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.hfda.LunchApp.R.string.cred;
import static com.hfda.LunchApp.R.string.settingsAction;
import static com.hfda.LunchApp.R.string.toastPW;
import static com.hfda.LunchApp.R.string.toastPWError;
import static com.hfda.LunchApp.R.string.toastPriceMode;
import static com.hfda.LunchApp.R.string.toastPriceModeError;

public class SettingsFragment extends Fragment {

    private LunchDBhelper db;
    private EditText etCurrentPassword;
    private EditText etNewPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(settingsAction);

        db = new LunchDBhelper(getActivity().getApplicationContext());

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Laupet", "On View Created");

        CheckBox cbStudent = (CheckBox) view.findViewById(R.id.cbStudent);
        etCurrentPassword = (EditText) view.findViewById(R.id.etCurrentPassword);
        etNewPassword = (EditText) view.findViewById(R.id.etNewPassword);

        //setting checkbox state based on SqlLite data
        boolean isStudent = db.getStudent();
        cbStudent.setChecked(isStudent);

        //getting uuid for the logged in user.
        final String uuid = db.getUuid();


        //onclick listener for checkbox
        cbStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 int studentStatus;
                 if (isChecked) {
                     studentStatus = 1;
                 } else {
                     studentStatus = 0;
                 }


                 //change status in mySQL and SQlLite
                 setStudentStatus(uuid, studentStatus);

             }
         }
        );


        //Clickevent for save password
        final Button btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Save
                String currentPassword = etCurrentPassword.getText().toString();
                String newPassword = etNewPassword.getText().toString();

                // Check for empty data in the form
                if (!currentPassword.isEmpty() && !newPassword.isEmpty()) {

                    changePassword(uuid, currentPassword, newPassword);

                } else {
                    //Show error too user
                    Toast.makeText(getActivity().getApplicationContext(), cred, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //setting studentStatus in mySQL and then sqlLite
    private void setStudentStatus(final String uuid, final int studentStatus) {
        // Tag used to cancel the request
        String tag_string_req = "req_setStudent";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_SET_STUDENT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Laupet", "Set studentStatus: " + response);

                try {
                    JSONObject obj = new JSONObject(response);


                    boolean error = obj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        //setting status is okay
                        int studentStatus = obj.getInt("studentStatus");

                        //set changes to SqlLite
                        db.setStudent(studentStatus);
                        Toast.makeText(getActivity().getApplicationContext(), toastPriceMode, Toast.LENGTH_LONG).show();


                    } else {
                        // Error when setting student. Get the error message
                        String errorMsg = obj.getString("error_msg");
                        Toast.makeText(getActivity().getApplicationContext(), toastPriceModeError, Toast.LENGTH_LONG).show();
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
                Log.e("Laupet", "setStudent Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("uuid", uuid);
                params.put("studentStatus", Integer.toString(studentStatus));
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }










    //setting studentStatus in mySQL and then sqlLite
    private void changePassword(final String uuid, final String currentPassword, final String newPassword) {
        // Tag used to cancel the request
        String tag_string_req = "req_changePassword";


        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_CHANGE_PASSWORD, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Laupet", "change password response: " + response);

                try {
                    JSONObject obj = new JSONObject(response);


                    boolean error = obj.getBoolean("error");



                    // Check for error node in json
                    if (!error) {
                        //password is changed. clear input fields. show toast

                        etCurrentPassword.setText("");
                        etNewPassword.setText("");
                        Toast.makeText(getActivity().getApplicationContext(), toastPW, Toast.LENGTH_LONG).show();


                    } else {
                        // Error when setting student. Get the error message
                        String errorMsg = obj.getString("error_msg");
                        Toast.makeText(getActivity().getApplicationContext(), toastPWError, Toast.LENGTH_LONG).show();
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
                Log.e("Laupet", "change password Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("uuid", uuid);
                params.put("currentPassword", currentPassword);
                params.put("newPassword", newPassword);
                return params;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }






















}
