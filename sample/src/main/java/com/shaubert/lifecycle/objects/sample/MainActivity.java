package com.shaubert.lifecycle.objects.sample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.shaubert.lifecycle.objects.LifecycleBasedObject;
import com.shaubert.lifecycle.objects.dispatchers.support.LifecycleDispatcherAppCompatActivity;

public class MainActivity extends LifecycleDispatcherAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.ab_toolbar));

        attachToLifecycle(new LoggingObject((TextView) findViewById(R.id.text_view)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.request_permission).setVisible(Build.VERSION.SDK_INT >= 23);
        return true;
    }

    @Override
    @SuppressLint("NewApi")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.start_for_result:
                startActivityForResult(new Intent(this, TestAcitivity.class), 1);
                return true;
            case R.id.request_permission:
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static class LoggingObject extends LifecycleBasedObject {
        private TextView textView;

        public LoggingObject(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onAttached() {
            textView.append("\nonAttached");
        }

        @Override
        public void onDetached() {
            textView.append("\nonDetached()");
        }

        @Override
        protected void onRestorePersistentState(@NonNull PersistableBundle persistentState) {
            textView.append("\nonRestorePersistentState()");
        }

        @Override
        protected void onRestoreInstanceState(@NonNull Bundle state) {
            textView.append("\nonRestoreInstanceState()");
        }

        @Override
        protected void onCreate(@Nullable Bundle state) {
            textView.append("\nonCreate()");
        }

        @Override
        @SuppressLint("NewApi")
        protected void onCreate(@Nullable Bundle state, @Nullable PersistableBundle persistentState) {
            String oldState = null;
            if (state != null) {
                oldState = state.getString("state");
            } else if (persistentState != null) {
                oldState = persistentState.getString("state");
            }
            if (oldState != null) {
                textView.append("\n--- restored state ----");
                textView.append(oldState);
                textView.append("\n--- end restored state ----");
            }
            textView.append("\nonCreate()");
        }

        @Override
        protected void onResume() {
            textView.append("\nonResume()");
        }

        @Override
        protected void onStart() {
            textView.append("\nonStart()");
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            textView.append("\nonActivityResult()");
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            textView.append("\nonRequestPermissionsResult()");
        }

        @Override
        protected void onSaveInstanceState(@NonNull Bundle outState) {
            textView.append("\nonSaveInstanceState()");
            outState.putString("state", textView.getText().toString());
        }

        @Override
        @SuppressLint("NewApi")
        protected void onSavePersistentState(@NonNull PersistableBundle outPersistentState) {
            textView.append("\nonSavePersistentState()");
            outPersistentState.putString("state", textView.getText().toString());
        }

        @Override
        protected void onPause(boolean isFinishing) {
            textView.append("\nonPause()");
        }

        @Override
        protected void onStop(boolean isFinishing) {
            textView.append("\nonStop()");
        }

        @Override
        protected void onDestroy() {
            textView.append("\nonDestroy()");
        }
    }

}
