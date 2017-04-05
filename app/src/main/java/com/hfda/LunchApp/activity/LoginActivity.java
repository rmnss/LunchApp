package com.hfda.LunchApp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hfda.LunchApp.app.AppConfig;
import com.hfda.LunchApp.app.AppController;
import com.hfda.LunchApp.R;
import com.hfda.LunchApp.helper.LunchDBhelper;
import com.hfda.LunchApp.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {


    private EditText inputEmail;
    private EditText inputPassword;
    private Button btnLogin;
    private Button btnRegister;
    private SessionManager session;
    private ProgressDialog pDialog;
    private LunchDBhelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.txtUsername);
        inputPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnNewUser);


        //Checks session for login data.
        session = new SessionManager(getApplicationContext());

        //Creates database handler
        db = new LunchDBhelper(getApplicationContext());

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        if (session.isLoggedIn()){
            Log.d("Laupet", "User is logged in");

            // User is already logged in. Send to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }else{
            Log.d("Laupet", "User is not logged in");
        }



        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();


                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {

                    //checkLogin(email, password);
                    checkLogin(email, password);

                } else {
                    //Show error too user
                    Toast.makeText(getApplicationContext(),"Please enter credentials!", Toast.LENGTH_LONG).show();
                }

            }
        });



        // Register button Click Event
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                registerClick();
            }
        });



    }//OnCreate done



    //Checks login against mySQL
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";



        pDialog.setMessage("Logging in ...");
        showDialog();
        Log.d("Laupet", AppConfig.URL_LOGIN);
        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Laupet", "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        //String name = user.getString("name");
                        String email = user.getString("email");
                        //String created_at = user.getString("created_at");

                        // Inserting row in users table
                        db.addUser(email, uid);

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Log.d("Laupet", "JSONEXception");
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Laupet", "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        // Adding request to request queue
        Log.d("Laupet", "getInstance:" + strReq + " - " +  tag_string_req);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
        Log.d("Laupet", "Den kræsja");
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }









    public void loginClick(){
        Log.d("Laupet", "LoginClicked");
        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(loginIntent);
        finish();
    }

    public void registerClick(){
        Log.d("Laupet", "RegisterClicked");
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }


}




