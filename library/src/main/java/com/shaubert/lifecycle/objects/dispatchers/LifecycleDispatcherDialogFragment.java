package com.shaubert.lifecycle.objects.dispatchers;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.shaubert.lifecycle.objects.LifecycleDelegate;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;
import com.shaubert.lifecycle.objects.LifecycleObjectsGroup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class LifecycleDispatcherDialogFragment extends DialogFragment implements LifecycleDelegate {

    private LifecycleObjectsGroup objectsGroup = new LifecycleObjectsGroup();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        objectsGroup.dispatchOnAttach();
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
        objectsGroup.dispatchOnPause(isRemoving() || getActivity().isFinishing());
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