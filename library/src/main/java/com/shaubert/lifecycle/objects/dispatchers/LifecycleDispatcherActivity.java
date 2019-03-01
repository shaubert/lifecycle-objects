package com.shaubert.lifecycle.objects.dispatchers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.shaubert.lifecycle.objects.LifecycleDelegate;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;


public abstract class LifecycleDispatcherActivity extends Activity implements LifecycleDelegate {

    private LifecycleCoreDelegate lifecycleCoreDelegate = new LifecycleCoreDelegate(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleCoreDelegate.dispatchOnCreate(savedInstanceState, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lifecycleCoreDelegate.dispatchOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        lifecycleCoreDelegate.dispatchOnRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleCoreDelegate.dispatchOnStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleCoreDelegate.dispatchOnResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleCoreDelegate.dispatchOnPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycleCoreDelegate.dispatchOnStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleCoreDelegate.dispatchOnDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        lifecycleCoreDelegate.dispatchOnSaveInstanceState(outState, null);
    }

    @Override
    public void attachToLifecycle(LifecycleDispatcher object) {
        lifecycleCoreDelegate.attachToLifecycle(object);
    }

    @Override
    public void detachFromLifecycle(LifecycleDispatcher object) {
        lifecycleCoreDelegate.detachFromLifecycle(object);
    }

    @Override
    public boolean isAttached(LifecycleDispatcher object) {
        return lifecycleCoreDelegate.isAttached(object);
    }

}