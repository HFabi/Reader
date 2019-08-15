package com.example.lenovo.reader.fragments.settings

import com.example.lenovo.reader.controllers.DayNightController
import com.example.lenovo.reader.controllers.DayNightControllerImpl.Companion.DAYNIGHT_AUTO
import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.lenovo.reader.fragments.settings.interactors.GetStorageInfoInteractor
import com.example.lenovo.reader.navigation.Router
import com.example.model.bind
import com.example.model.schedule
import javax.inject.Inject

class SettingsPresenterImpl @Inject constructor() : BasePresenterImpl(), SettingsPresenter {

  @Inject
  lateinit var view: SettingsView

  @Inject
  lateinit var router: Router
  @Inject
  lateinit var dayNightController: DayNightController
  @Inject
  lateinit var getStorageInfoInteractor: GetStorageInfoInteractor

  override fun onResume() {
    loadDayNightMode()
    getStorageInfoInteractor.execute()
      .bind(compositeDisposable)
      .schedule()
      .subscribe()
  }

  override fun loadDayNightMode() {
    dayNightController.loadPreferredDayNightMode()
      .bind(compositeDisposable)
      .schedule()
      .subscribe({ index ->
        view.updateDayNightMode(index)
      }, { error -> error.printStackTrace() })
  }

  override fun updateDayNightMode(selectedIndex: Int) {
    dayNightController.applyDayNightMode(selectedIndex)
      .bind(compositeDisposable)
      .schedule()
      .subscribe({}, { error -> error.printStackTrace() })
  }
}