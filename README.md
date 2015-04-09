#Lifecycle Objects

Create objects with callbacks from activity lifecycle.

##Gradle

    repositories {
        maven{url "https://github.com/shaubert/maven-repo/raw/master/releases"}
    }
    dependencies {
        compile 'com.shaubert.lifecycle.objects:library:1.0.0'
    }

##How to use

If you want to receive activity/fragment callbacks in your class you have several options:
 1. Extend from `LifecycleBasedObject`;
 2. Extend from `LifecycleObjectsGroup`, if your class will contain child lifecycle listeners;
 3. Implement `LifecycleDispatcher`.
 
After than you have to register your class instance in `LifecycleDelegate` with `attachToLifecycle` method. You can use one of implemented versions of Activity or Fragment (for example `LifecycleDispatcherFragmentActivity` or `LifecycleDispatcherSupportFragment`). Or you can copy implementation into your activity. It's really simple: 

    public abstract class LifecycleDispatcherFragmentActivity extends FragmentActivity implements LifecycleDelegate {
  
      private LifecycleObjectsGroup lifecycleObjectsGroup = new LifecycleObjectsGroup();
  
      @Override
      protected void onActivityResult(int requestCode, int resultCode, Intent data) {
          super.onActivityResult(requestCode, resultCode, data);
          lifecycleObjectsGroup.dispatchOnActivityResult(requestCode, resultCode, data);
      }
  
      @Override
      protected void onResumeFragments() {
          super.onResumeFragments();
          lifecycleObjectsGroup.dispatchOnResume();
      }
  
      @Override
      protected void onPause() {
          super.onPause();
          lifecycleObjectsGroup.dispatchOnPause();
      }
  
      @Override
      protected void onSaveInstanceState(Bundle outState) {
          super.onSaveInstanceState(outState);
          lifecycleObjectsGroup.dispatchOnSaveInstanceState(outState);
      }
  
  
      @Override
      public void attachToLifecycle(LifecycleDispatcher object) {
          lifecycleObjectsGroup.attachToLifecycle(object);
      }
  
      @Override
      public void detachFromLifecycle(LifecycleDispatcher object) {
          lifecycleObjectsGroup.detachFromLifecycle(object);
      }
  
      @Override
      public boolean isAttached(LifecycleDispatcher object) {
          return lifecycleObjectsGroup.isAttached(object);
      }
    }

Now your class can handle this methods from activity/fragment

    void onResume();
    void onPause();
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onCreate(Bundle savedInstanceState);
    void onSaveInstanceState(Bundle outState);

