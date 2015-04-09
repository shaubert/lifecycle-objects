package com.shaubert.lifecycle.objects;

import android.content.Intent;
import android.os.Bundle;

public class LifecycleBasedObject implements LifecycleDispatcher {

    private boolean paused = true;

    @Override
    public final void dispatchOnResume() {
        if (paused) {
            paused = false;

            onResume();
        }
    }

    protected void onResume() {
    }


    public boolean isPaused() {
        return paused;
    }

    @Override
    public final void dispatchOnActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResult(requestCode, resultCode, data);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public final void dispatchOnPause() {
        if (!paused) {
            paused = true;
            onPause();
        }
    }

    protected void onPause() {
    }

    @Override
    public final void dispatchOnCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Bundle stateBundle = savedInstanceState.getBundle(getBundleTag());
            if (stateBundle != null) {
                onRestoreInstanceState(stateBundle);
            }
        }
    }

    protected void onRestoreInstanceState(Bundle stateBundle) {
    }

    @Override
    public final void dispatchOnSaveInstanceState(Bundle outState) {
        Bundle bundle = new Bundle();
        onSaveInstanceState(bundle);
        outState.putBundle(getBundleTag(), bundle);
    }

    protected void onSaveInstanceState(Bundle outState) {
    }

    protected String getBundleTag() {
        return getClass().getSimpleName();
    }

}