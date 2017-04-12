package com.hfda.LunchApp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.hfda.LunchApp.fragment.CoffeeFragment;
import com.hfda.LunchApp.fragment.HomeFragment;
import com.hfda.LunchApp.fragment.MenuFragment;
import com.hfda.LunchApp.fragment.SpecialFragment;
import com.hfda.LunchApp.helper.LunchDBhelper;
import com.hfda.LunchApp.helper.SessionManager;

import com.hfda.LunchApp.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    //Må være i første acitivity
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    ListView lv;
    Fragment fragment;

    DrawerLayout drawerLayout;
    private ActionBarDrawerToggle abDrawerToggle;



    private SessionManager session;
    private LunchDBhelper db;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.drawerleft);
        lv.setOnItemClickListener(this);

        // find the drawer layout from view

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        abDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                supportInvalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(abDrawerToggle);

        checkRequestPermission();

        session = new SessionManager(getApplicationContext());

        //Creates database handler
        db = new LunchDBhelper(getApplicationContext());

        Fragment fragment;
        fragment = new HomeFragment();
        fragmentTransaction(0, fragment);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Fragment fragment;
        switch(position){
            case 1:
                fragment = new MenuFragment();
                fragmentTransaction(position, fragment);
                break;

            case 2:

                fragment = new SpecialFragment();
                fragmentTransaction(position, fragment);
                break;

            case 3:
                fragment = new CoffeeFragment();
                fragmentTransaction(position, fragment);
                break;

            case 4:

                logoutUser();
                break;

            default:
                fragment = new HomeFragment();
                fragmentTransaction(position, fragment);


        }



    }

    public void fragmentTransaction(int position, Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_content,fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        getSupportActionBar().setTitle(getResources().getStringArray(R.array.meny)[position]);

        selectItem(position);
    }

    public void selectItem( int position){
        drawerLayout.closeDrawer(Gravity.START);
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




    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}