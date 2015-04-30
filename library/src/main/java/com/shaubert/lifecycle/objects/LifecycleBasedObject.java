package com.shaubert.lifecycle.objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LifecycleBasedObject implements LifecycleDispatcher {

    private boolean paused = true;
    private boolean attached = false;

    @Override
    public boolean isAttached() {
        return attached;
    }

    @Override
    public void dispatchOnAttach() {
        attached = true;
        onAttached();
    }

    public void onAttached() {

    }

    @Override
    public void dispatchOnDetach() {
        attached = false;
        onDetached();
    }

    public void onDetached() {

    }

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
    public final void dispatchOnCreate(@Nullable Bundle savedInstanceState) {
        Bundle stateBundle = null;
        if (savedInstanceState != null) {
            stateBundle = savedInstanceState.getBundle(getBundleTag());
        }

        onCreate(stateBundle);
        if (stateBundle != null) {
            onRestoreInstanceState(stateBundle);
        }
    }

    protected void onRestoreInstanceState(@NonNull Bundle state) {
    }

    protected void onCreate(@Nullable Bundle state) {
    }

    @Override
    public final void dispatchOnSaveInstanceState(@NonNull Bundle outState) {
        Bundle bundle = new Bundle();
        onSaveInstanceState(bundle);
        outState.putBundle(getBundleTag(), bundle);
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
    }

    protected String getBundleTag() {
        return getClass().getSimpleName();
    }

}