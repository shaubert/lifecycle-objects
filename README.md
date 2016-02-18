#Lifecycle Objects

Create objects with callbacks from activity lifecycle.

##Gradle

    repositories {
        maven{url "https://github.com/shaubert/maven-repo/raw/master/releases"}
    }
    dependencies {
        compile 'com.shaubert.lifecycle.objects:library:1.3'
    }


##How to use

If you want to receive activity/fragment callbacks in your class you have several options:
 1. Extend from `LifecycleBasedObject`;
 2. Extend from `LifecycleObjectsGroup`, if your class will contain child lifecycle listeners;
 3. Implement `LifecycleDispatcher`.
 
After than you have to register your class instance in `LifecycleDelegate` with `attachToLifecycle` method. You can use one of implemented versions of Activity or Fragment (for example `LifecycleDispatcherFragmentActivity` or `LifecycleDispatcherSupportFragment`). Or you can copy implementation into your activity. It's really simple.

Now your class can handle this methods from activity/fragment

    void onCreate(Bundle savedInstanceState, PersistableBundle outPersistentState) / onActivityCreated(Bundle savedInstanceState);
    void onStart();
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    void onResume();
    void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState);
    void onPause(boolean isFinishing);
    void onStop();
    void onDestroy();
    

