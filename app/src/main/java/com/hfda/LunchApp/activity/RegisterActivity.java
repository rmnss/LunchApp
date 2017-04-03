package com.hfda.LunchApp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hfda.LunchApp.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        // Login button Click Event
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                //:TODO Add input checks
                //:TODO Add loginfunction

                //Go to next intent
                registerClick();
            }
        });
    }



    public void registerClick(){

        Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(registerIntent);
        //finish(); should we have this?
    }


}
