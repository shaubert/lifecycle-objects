package com.shaubert.lifecycle.objects;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LifecycleObjectsGroup extends LifecycleBasedObject implements LifecycleDelegate {

    private Set<LifecycleDispatcher> children = new HashSet<>();
    private Bundle savedState;
    private PersistableBundle persistedState;
    private boolean onCreateCalled;
    private boolean started;

    @Override
    public void attachToLifecycle(LifecycleDispatcher object) {
        if (object != null) {
            if (children.add(object)) {
                if (!object.isPaused() || object.isAttached()) {
                    throw new IllegalStateException("attaching not detached object");
                }

                if (isAttached()) {
                    object.dispatchOnAttach();
                }

                if (onCreateCalled) {
                    object.dispatchOnCreate(savedState, persistedState);
                }

                if (started) {
                    object.dispatchOnStart();
                }

                if (!isPaused()) {
                    object.dispatchOnResume();
                }
            }
        }
    }

    @Override
    public void onAttached() {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<LifecycleDispatcher>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnAttach();
        }
    }

    @Override
    public void detachFromLifecycle(LifecycleDispatcher object) {
        if (children.remove(object)) {
            if (!object.isPaused()) {
                object.dispatchOnPause(false);
            }
            object.dispatchOnDetach();
        }
    }

    @Override
    public void onDetached() {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<LifecycleDispatcher>(children);
        for (LifecycleDispatcher object : temp) {
            if (object.isAttached()) {
                object.dispatchOnDetach();
            }
        }
    }

    @Override
    public boolean isAttached(LifecycleDispatcher object) {
        return children.contains(object);
    }

    @Override
    protected void onCreate(@Nullable Bundle state, @Nullable PersistableBundle persistentState) {
        savedState = state;
        persistedState = persistentState;
        onCreateCalled = true;

        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnCreate(state, persistentState);
        }
    }

    @Override
    protected void onStart() {
        started = true;
        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnStart();
        }
    }

    @Override
    protected void onResume() {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnResume();
        }
    }

    @Override
    protected void onPause(boolean isFinishing) {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnPause(isFinishing);
        }

        throwIfHaveNotPausedChild();
    }

    @Override
    protected void onStop(boolean isFinishing) {
        started = false;
        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnStop(isFinishing);
        }
    }

    @Override
    protected void onDestroy() {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnDestroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void throwIfHaveNotPausedChild() {
        for (LifecycleDispatcher child : children) {
            if (!child.isPaused()) {
                throw new IllegalStateException("invalid state of child " + child + "; should be paused");
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnSaveInstanceState(outState);
        }
    }

    @Override
    protected void onSavePersistentState(@NonNull PersistableBundle outPersistentState) {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<>(children);
        for (LifecycleDispatcher object : temp) {
            object.dispatchOnSavePersistentState(outPersistentState);
        }
    }
}
