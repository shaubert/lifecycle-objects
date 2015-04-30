package com.shaubert.lifecycle.objects.dispatchers.support;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.shaubert.lifecycle.objects.LifecycleDelegate;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;
import com.shaubert.lifecycle.objects.LifecycleObjectsGroup;


public abstract class LifecycleDispatcherActionBarActivity extends ActionBarActivity implements LifecycleDelegate {

    private LifecycleObjectsGroup lifecycleObjectsGroup = new LifecycleObjectsGroup();

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        lifecycleObjectsGroup.dispatchOnAttach();
        lifecycleObjectsGroup.dispatchOnCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lifecycleObjectsGroup.dispatchOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        lifecycleObjectsGroup.dispatchOnResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleObjectsGroup.dispatchOnPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
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