package com.shaubert.lifecycle.objects.dispatchers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.shaubert.lifecycle.objects.LifecycleDelegate;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;
import com.shaubert.lifecycle.objects.LifecycleObjectsGroup;


public abstract class LifecycleDispatcherActivity extends Activity implements LifecycleDelegate {

    private LifecycleObjectsGroup lifecycleObjectsGroup = new LifecycleObjectsGroup();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lifecycleObjectsGroup.dispatchOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleObjectsGroup.dispatchOnResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleObjectsGroup.dispatchOnPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        lifecycleObjectsGroup.dispatchOnSaveInstanceState(outState);
    }


    @Override
    public void attachToLifecycle(LifecycleDispatcher object) {
        lifecycleObjectsGroup.attachToLifecycle(object);
    }

    @Override
    public void detachFromLifecycle(LifecycleDispatcher object) {
        lifecycleObjectsGroup.detachFromLifecycle(object);
    }

    @Override
    public boolean isAttached(LifecycleDispatcher object) {
        return lifecycleObjectsGroup.isAttached(object);
    }

}