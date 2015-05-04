package com.shaubert.lifecycle.objects;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface LifecycleDispatcher {

    boolean isAttached();

    void dispatchOnAttach();

    void dispatchOnDetach();

    void dispatchOnResume();

    @Deprecated
    void dispatchOnPause();

    void dispatchOnPause(boolean isFinishing);

    boolean isPaused();

    void dispatchOnActivityResult(int requestCode, int resultCode, Intent data);

    void dispatchOnCreate(@Nullable Bundle savedInstanceState);

    void dispatchOnSaveInstanceState(@NonNull Bundle outState);

}
