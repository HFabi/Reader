package com.example.lenovo.reader.fragments.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

interface BasePresenter : LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  open fun onCreate() {

  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  open fun onResume() {

  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  open fun onPause() {

  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  open fun onDestroy() {

  }

}
