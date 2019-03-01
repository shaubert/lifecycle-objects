package com.shaubert.lifecycle.objects.dispatchers.support;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.shaubert.lifecycle.objects.LifecycleDelegate;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;
import com.shaubert.lifecycle.objects.dispatchers.LifecycleCoreDelegate;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;


public abstract class LifecycleDispatcherFragmentActivityV21 extends FragmentActivity implements LifecycleDelegate {

    private boolean ignoreCreate;
    private boolean ignoreSaveInstanceState;
    private LifecycleCoreDelegate lifecycleCoreDelegate = new LifecycleCoreDelegate(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ignoreCreate) {
            lifecycleCoreDelegate.dispatchOnCreate(savedInstanceState, null);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        ignoreCreate = true;
        super.onCreate(savedInstanceState, persistentState);
        ignoreCreate = false;
        lifecycleCoreDelegate.dispatchOnCreate(savedInstanceState, persistentState);
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
    protected void onResumeFragments() {
        super.onResumeFragments();
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
        if (!ignoreSaveInstanceState) {
            lifecycleCoreDelegate.dispatchOnSaveInstanceState(outState, null);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        ignoreSaveInstanceState = true;
        super.onSaveInstanceState(outState, outPersistentState);
        ignoreSaveInstanceState = false;
        lifecycleCoreDelegate.dispatchOnSaveInstanceState(outState, outPersistentState);
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