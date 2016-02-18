package com.shaubert.lifecycle.objects.dispatchers.support;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import com.shaubert.lifecycle.objects.LifecycleDelegate;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;
import com.shaubert.lifecycle.objects.dispatchers.LifecycleCoreDelegate;

public abstract class LifecycleDispatcherSupportDialogFragment extends DialogFragment implements LifecycleDelegate {

    private LifecycleCoreDelegate lifecycleCoreDelegate = new LifecycleCoreDelegate(this);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lifecycleCoreDelegate.dispatchOnActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lifecycleCoreDelegate.dispatchOnActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        lifecycleCoreDelegate.dispatchOnRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleCoreDelegate.dispatchOnStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleCoreDelegate.dispatchOnResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        lifecycleCoreDelegate.dispatchOnPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycleCoreDelegate.dispatchOnStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleCoreDelegate.dispatchOnDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
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