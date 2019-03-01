package com.shaubert.lifecycle.objects;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface LifecycleDispatcher {

    boolean isAttached();

    void dispatchOnAttach();

    void dispatchOnDetach();

    void dispatchOnStart();

    void dispatchOnResume();

    void dispatchOnPause(boolean isFinishing);

    void dispatchOnStop(boolean isFinishing);

    void dispatchOnDestroy();

    boolean isPaused();

    void dispatchOnActivityResult(int requestCode, int resultCode, Intent data);

    void dispatchOnRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

    void dispatchOnCreate(@Nullable Bundle savedInstanceState, @Nullable Object persistableBundle);

    void dispatchOnSaveInstanceState(@NonNull Bundle outState);

    void dispatchOnSavePersistentState(@NonNull Object outPersistableBundle);

}
