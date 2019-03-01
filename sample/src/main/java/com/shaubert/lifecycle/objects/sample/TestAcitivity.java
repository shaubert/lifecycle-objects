package com.shaubert.lifecycle.objects.sample;

import androidx.appcompat.app.AppCompatActivity;

public class TestAcitivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setResult(RESULT_OK);
        finish();
    }

}
