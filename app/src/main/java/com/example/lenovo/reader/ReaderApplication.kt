package com.example.lenovo.reader

import com.example.lenovo.reader.controllers.DayNightController
import com.example.lenovo.reader.di.DaggerApplicationComponent
import com.example.model.bind
import com.example.model.schedule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class ReaderApplication : DaggerApplication() {

  @Inject
  lateinit var dayNightController: DayNightController

  var compositeDisposable: CompositeDisposable = CompositeDisposable()

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      plantTimber()
    }
    dayNightController.applyPreferredDayNightMode()
      .schedule()
      .bind(compositeDisposable)
      .subscribe()
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
    DaggerApplicationComponent.builder().create(this)

  private fun plantTimber() {
    Timber.plant(object : Timber.DebugTree() {
      override fun createStackElementTag(element: StackTraceElement): String? =
        "${super.createStackElementTag(element)}, ${element.methodName}:: ${element.lineNumber}"
    })
  }

  override fun onTerminate() {
    super.onTerminate()
    compositeDisposable.dispose()
  }

}
