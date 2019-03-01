package com.shaubert.lifecycle.objects.dispatchers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.shaubert.lifecycle.objects.LifecycleDelegate;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;
import com.shaubert.lifecycle.objects.LifecycleObjectsGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LifecycleCoreDelegate implements LifecycleDelegate {

    private LifecycleObjectsGroup lifecycleObjectsGroup = new LifecycleObjectsGroup();

    private Activity activity;
    private Fragment supportFragment;

    public LifecycleCoreDelegate(Object activityOrFragment) {
        if (activityOrFragment == null) throw new NullPointerException();
        if (activityOrFragment instanceof Activity) {
            this.activity = (Activity) activityOrFragment;
        } else if (activityOrFragment instanceof Fragment) {
            this.supportFragment = (Fragment) activityOrFragment;
        } else {
            throw new IllegalArgumentException("Must be Activity or Fragment but: " + activityOrFragment);
        }

        lifecycleObjectsGroup.dispatchOnAttach();
    }

    public void dispatchOnCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        lifecycleObjectsGroup.dispatchOnCreate(savedInstanceState, persistentState);
    }

    public void dispatchOnActivityCreated(Bundle savedInstanceState) {
        lifecycleObjectsGroup.dispatchOnAttach();
        lifecycleObjectsGroup.dispatchOnCreate(savedInstanceState, null);
    }

    public void dispatchOnActivityResult(int requestCode, int resultCode, Intent data) {
        lifecycleObjectsGroup.dispatchOnActivityResult(requestCode, resultCode, data);
    }

    public void dispatchOnRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        lifecycleObjectsGroup.dispatchOnRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void dispatchOnStart() {
        lifecycleObjectsGroup.dispatchOnStart();
    }

    public void dispatchOnResume() {
        lifecycleObjectsGroup.dispatchOnResume();
    }

    public void dispatchOnPause() {
        lifecycleObjectsGroup.dispatchOnPause(isFinishing());
    }

    public void dispatchOnStop() {
        lifecycleObjectsGroup.dispatchOnStop(isFinishing());
    }

    public void dispatchOnDestroy() {
        lifecycleObjectsGroup.dispatchOnDestroy();
    }

    @SuppressLint("NewApi")
    private boolean isFinishing() {
        if (activity != null) {
            return activity.isFinishing();
        } else if (supportFragment != null) {
            return supportFragment.isRemoving()
                    || (supportFragment.getActivity() != null && supportFragment.getActivity().isFinishing());
        }
        return false;
    }

    public void dispatchOnSaveInstanceState(@NonNull Bundle outState, @Nullable PersistableBundle outPersistentState) {
        lifecycleObjectsGroup.dispatchOnSaveInstanceState(outState);
        if (outPersistentState != null) {
            lifecycleObjectsGroup.dispatchOnSavePersistentState(outPersistentState);
        }
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
