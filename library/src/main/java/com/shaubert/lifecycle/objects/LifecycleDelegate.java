package com.shaubert.lifecycle.objects;

public interface LifecycleDelegate {

    void attachToLifecycle(LifecycleDispatcher object);

    void detachFromLifecycle(LifecycleDispatcher object);

    boolean isAttached(LifecycleDispatcher object);

}
