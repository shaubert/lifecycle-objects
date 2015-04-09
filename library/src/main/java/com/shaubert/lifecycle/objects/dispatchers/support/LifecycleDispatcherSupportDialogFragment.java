package com.shaubert.lifecycle.objects.dispatchers.support;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import com.shaubert.lifecycle.objects.LifecycleDelegate;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;
import com.shaubert.lifecycle.objects.LifecycleObjectsGroup;

public abstract class LifecycleDispatcherSupportDialogFragment extends DialogFragment implements LifecycleDelegate {

    private LifecycleObjectsGroup objectsGroup = new LifecycleObjectsGroup();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        objectsGroup.dispatchOnCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        objectsGroup.dispatchOnResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        objectsGroup.dispatchOnPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        objectsGroup.dispatchOnSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        objectsGroup.dispatchOnActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void attachToLifecycle(LifecycleDispatcher object) {
        objectsGroup.attachToLifecycle(object);
    }

    @Override
    public void detachFromLifecycle(LifecycleDispatcher object) {
        objectsGroup.detachFromLifecycle(object);
    }

    @Override
    public boolean isAttached(LifecycleDispatcher object) {
        return objectsGroup.isAttached(object);
    }

}