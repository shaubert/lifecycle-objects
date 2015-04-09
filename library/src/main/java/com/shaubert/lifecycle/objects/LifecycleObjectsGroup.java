package com.shaubert.lifecycle.objects;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LifecycleObjectsGroup extends LifecycleBasedObject implements LifecycleDelegate {

    private Set<LifecycleDispatcher> children = new HashSet<LifecycleDispatcher>();
    private Bundle savedState = new Bundle();

    @Override
    public void attachToLifecycle(LifecycleDispatcher object) {
        if (object != null) {
            if (children.add(object)) {
                if (!object.isPaused()) {
                    throw new IllegalStateException("attaching not detached object");
                }

                object.dispatchOnCreate(savedState);
                if (!isPaused()) {
                    object.dispatchOnResume();
                }
            }
        }
    }

    @Override
    public void detachFromLifecycle(LifecycleDispatcher object) {
        if (children.remove(object)) {
            if (!object.isPaused()) {
                object.dispatchOnPause();
            }
            object.dispatchOnSaveInstanceState(savedState);
        }
    }

    @Override
    public boolean isAttached(LifecycleDispatcher object) {
        return children.contains(object);
    }

    @Override
    protected void onResume() {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<LifecycleDispatcher>(children);
        for (LifecycleDispatcher object : temp) {
            if (!isPaused() && children.contains(object)) {
                object.dispatchOnResume();
            }
        }
    }

    @Override
    protected void onPause() {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<LifecycleDispatcher>(children);
        for (LifecycleDispatcher object : temp) {
            if (isPaused() && children.contains(object)) {
                object.dispatchOnPause();
            }
        }

        if (isPaused()) {
            throwIfHaveNotPausedChilds();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<LifecycleDispatcher>(children);
        for (LifecycleDispatcher object : temp) {
            if (children.contains(object)) {
                object.dispatchOnActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void throwIfHaveNotPausedChilds() {
        for (LifecycleDispatcher child : children) {
            if (!child.isPaused()) {
                throw new IllegalStateException("invalid state of child " + child + "; should be paused");
            }
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle stateBundle) {
        this.savedState = stateBundle;
        ArrayList<LifecycleDispatcher> temp = new ArrayList<LifecycleDispatcher>(children);
        for (LifecycleDispatcher object : temp) {
            if (children.contains(object)) {
                object.dispatchOnCreate(stateBundle);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ArrayList<LifecycleDispatcher> temp = new ArrayList<LifecycleDispatcher>(children);
        for (LifecycleDispatcher object : temp) {
            if (children.contains(object)) {
                object.dispatchOnSaveInstanceState(outState);
            }
        }
    }

}
