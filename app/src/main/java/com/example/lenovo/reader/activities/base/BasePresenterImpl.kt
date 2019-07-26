package com.example.lenovo.reader.activities.base

import com.example.lenovo.reader.controllers.DayNightController
import com.example.model.bind
import com.example.model.schedule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-26
 */
open class BasePresenterImpl : BasePresenter {

  var compositeDisposable: CompositeDisposable = CompositeDisposable()
//
//  @Inject
//  lateinit var dayNightController: DayNightController
//
//  override fun onCreate() {
//    super.onCreate()
//    dayNightController.applyPreferredDayNightMode()
//      .schedule()
//      .bind(compositeDisposable)
//      .subscribe()
//  }
//
  override fun onDestroy() {
    super.onDestroy()
    compositeDisposable.dispose()
  }
}