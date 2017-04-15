package com.hfda.LunchApp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hfda.LunchApp.R;
import com.hfda.LunchApp.activity.MainActivity;

import static com.hfda.LunchApp.R.id.imageView;
import static com.hfda.LunchApp.R.string.homeAction;
import static com.hfda.LunchApp.R.string.settingsAction;


public class SettingsFragment extends Fragment {

    public SettingsFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(settingsAction);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Laupet", "On View Created");




    }

}
