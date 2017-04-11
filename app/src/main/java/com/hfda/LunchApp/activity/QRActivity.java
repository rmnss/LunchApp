package com.hfda.LunchApp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hfda.LunchApp.R;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRActivity extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private String scanType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

        Intent intent = getIntent();
        scanType = intent.getStringExtra("scanType");
    }


    //When QR is scannet it will return the qr string to coffeeActivity
    public void handleResult(Result rawResult) {
        String stringResultat = rawResult.getText();
        Intent intent = new Intent();
        intent.putExtra("keyName", stringResultat);
        intent.putExtra("scanType", scanType);
        setResult(RESULT_OK, intent);
        finish();

    }


    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.startCamera();
    }



}
