package com.hfda.LunchApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        Log.d("Loggg", "loginActivity done");






    // Login button Click Event
    btnLogin.setOnClickListener(new View.OnClickListener() {

        public void onClick(View view) {

            //Add inputcheck!!!


            //Go to next intent
            loginClick();
        }
    });



    }//OnCreate done





    public void loginClick(){
        Log.d("Loggg", "LoginClicked");
        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(loginIntent);
        //finish(); Should we use this???
    }


}




