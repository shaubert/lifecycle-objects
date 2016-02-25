package com.shaubert.lifecycle.objects;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
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
    @SuppressLint("NewApi")
    public final void dispatchOnCreate(@Nullable Bundle savedInstanceState, @Nullable Object persistableBundle) {
        Bundle stateBundle = null;
        if (savedInstanceState != null) {
            stateBundle = savedInstanceState.getBundle(getBundleTag());
        }

        Object persistentStateBundle = null;
        if (Build.VERSION.SDK_INT >= 21) {
            if (persistableBundle instanceof PersistableBundle) {
                persistentStateBundle = ((PersistableBundle)persistableBundle).getPersistableBundle(getBundleTag());
            }
        }
        onCreate(stateBundle, persistentStateBundle);
        onCreate(stateBundle);

        if (persistentStateBundle != null) {
            onRestorePersistentState(persistentStateBundle);
        }

        if (stateBundle != null) {
            onRestoreInstanceState(stateBundle);
        }
    }

    protected void onRestorePersistentState(@NonNull Object persistableBundle) {
    }

    protected void onRestoreInstanceState(@NonNull Bundle state) {
    }

    protected void onCreate(@Nullable Bundle state) {
    }

    protected void onCreate(@Nullable Bundle state, @Nullable Object persistableBundle) {
    }

    @Override
    public final void dispatchOnStart() {
        onStart();
    }

    protected void onStart() {

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
    public final void dispatchOnRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    @Override
    public final void dispatchOnPause(boolean isFinishing) {
        if (!paused) {
            paused = true;
            onPause(isFinishing);
        }
    }

    @Override
    public void dispatchOnSaveInstanceState(@NonNull Bundle outState) {
        Bundle bundle = new Bundle();
        onSaveInstanceState(bundle);
        outState.putBundle(getBundleTag(), bundle);
    }

    @Override
    @SuppressLint("NewApi")
    public void dispatchOnSavePersistentState(@NonNull Object outPersistableBundle) {
        PersistableBundle persistableBundle = new PersistableBundle();
        onSavePersistentState(persistableBundle);
        ((PersistableBundle)outPersistableBundle).putPersistableBundle(getBundleTag(), persistableBundle);
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
    }

    protected void onSavePersistentState(@NonNull Object outPersistableBundle) {
    }

    protected void onPause(boolean isFinishing) {
    }

    @Override
    public final void dispatchOnStop(boolean isFinishing) {
        onStop(isFinishing);
    }

    protected void onStop(boolean isFinishing) {
    }

    @Override
    public final void dispatchOnDestroy() {
        onDestroy();
    }

    protected void onDestroy() {
    }


    protected String getBundleTag() {
        return getClass().getSimpleName();
    }

}