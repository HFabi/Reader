package com.example.lenovo.reader.fragments.settings

import com.example.lenovo.reader.fragments.base.BasePresenter


interface SettingsView {
  fun updateDayNightMode(selectedIndex: Int)
  fun showDayNightSelectionDialog()
}

interface SettingsPresenter : BasePresenter {
  fun loadDayNightMode()
  fun updateDayNightMode(selectedIndex: Int)
}