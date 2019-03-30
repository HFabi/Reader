package com.example.lenovo.reader

import com.example.lenovo.reader.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class ReaderApplication : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      plantTimber()
    }
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
    DaggerApplicationComponent.builder().create(this)

  private fun plantTimber() {
    Timber.plant(object : Timber.DebugTree() {
      override fun createStackElementTag(element: StackTraceElement): String? =
        "${super.createStackElementTag(element)}, ${element.methodName}:: ${element.lineNumber}"
    })
  }
}
