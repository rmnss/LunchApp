package com.hfda.LunchApp.helper;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {

    SharedPreferences sharedPref;
    Context _context;
    SharedPreferences.Editor editor;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LunchApp";

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        sharedPref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);

        editor.commit();

        Log.d("Laupet", "User login session modified: " + isLoggedIn);
    }

    public boolean isLoggedIn() {
        Log.d("Laupet", "Key: " + sharedPref.getBoolean(KEY_IS_LOGGED_IN, false));
        return sharedPref.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
