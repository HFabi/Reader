package com.example.lenovo.reader.fragments.dashboard

import android.util.Log

class DashboardBuilder {

    fun build(): DashboardFragment {
        Log.d("TAGL","Dashboard builder 1")
        val dashboardFragment = DashboardFragment()
        //dashboardFragment.dashboardPresenter
        //TODO
        //val dashboardPresenter = DashboardPresenterImpl()
        Log.d("TAGL","Dashboard builder 2")
        return dashboardFragment
    }

    //mitgeben, von wo aus aufgerufen wurde um zurück zu navigieren
    //parameter mit übergeben

}