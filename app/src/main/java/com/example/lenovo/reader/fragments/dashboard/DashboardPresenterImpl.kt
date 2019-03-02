package com.example.lenovo.reader.fragments.dashboard

import android.util.Log
import javax.inject.Inject

class DashboardPresenterImpl @Inject constructor() : DashboardPresenter {

  @Inject
  lateinit var view: DashboardView

  override fun onCreate() {
    super.onCreate()
    Log.d("LOG", "DashboardPResenter onCreate")
  }

}
