package com.shaubert.lifecycle.objects;

import android.content.Intent;
import android.os.Bundle;

public interface LifecycleDispatcher {

    void dispatchOnResume();

    void dispatchOnPause();

    boolean isPaused();

    void dispatchOnActivityResult(int requestCode, int resultCode, Intent data);

    void dispatchOnCreate(Bundle savedInstanceState);

    void dispatchOnSaveInstanceState(Bundle outState);

}
