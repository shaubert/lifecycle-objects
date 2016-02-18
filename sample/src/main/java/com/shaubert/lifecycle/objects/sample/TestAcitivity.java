package com.shaubert.lifecycle.objects.sample;

import android.support.v7.app.AppCompatActivity;

public class TestAcitivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setResult(RESULT_OK);
        finish();
    }

}
