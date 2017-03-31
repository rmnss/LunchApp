package com.hfda.LunchApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnNewUser);

        //:TODO add checks if the user is already logged in

        session = new SessionManager(getApplicationContext());


        //setting loggedin info
        session.setLogin(true);

        if (session.isLoggedIn()){
            Log.d("Laupet", "User is logged in");

            //:TODO Send user to the correct activity

        }else{
            Log.d("Laupet", "User is not logged in");
        }



        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //:TODO Add input checks
                //:TODO Add loginfunction

                //Go to next intent
                loginClick();
            }
        });



        // Register button Click Event
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //Go to next intent
                registerClick();
            }
        });



    }//OnCreate done





    public void loginClick(){
        Log.d("Laupet", "LoginClicked");
        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(loginIntent);
        //finish(); Should we use this???
    }

    public void registerClick(){
        Log.d("Laupet", "RegisterClicked");
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
        //finish(); Should we use this??
    }


}




