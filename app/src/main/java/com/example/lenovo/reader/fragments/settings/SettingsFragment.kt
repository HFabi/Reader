package com.example.lenovo.reader.fragments.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.lenovo.reader.R
import com.example.lenovo.reader.R.string
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.controllers.DayNightControllerImpl.Companion.DAYNIGHT_AUTO
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import kotlinx.android.synthetic.main.fragment_settings.settings_item_theme_constraintlayout
import kotlinx.android.synthetic.main.fragment_settings.settings_theme_description_textview
import kotlinx.android.synthetic.main.fragment_settings.settings_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_settings)
class SettingsFragment : BaseFragment(), SettingsView {

  @Inject
  lateinit var presenter: SettingsPresenter

  var themeStrings: Array<String> = arrayOf()
  var currentDayNightMode = DAYNIGHT_AUTO

  override fun providePresenter(): BasePresenter = presenter

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(settings_toolbar, false)
    init()
  }

  override fun onResume() {
    super.onResume()
    (activity as MainActivity).currentFragment = this
    (activity as MainActivity).setBottomNavigationEnabled(true)
  }

  fun init() {
    themeStrings = resources.getStringArray(R.array.array_themes)
    settings_item_theme_constraintlayout.setOnClickListener { showDayNightSelectionDialog() }
  }

  override fun updateDayNightMode(selectedIndex: Int) {
    settings_theme_description_textview.text = themeStrings[selectedIndex]
    currentDayNightMode = selectedIndex
  }

  override fun showDayNightSelectionDialog() {
    var tempSelectedThemeIndex = currentDayNightMode
    AlertDialog.Builder(context!!)
      .setTitle(getString(string.settings_theme_dialog_title))
      .setSingleChoiceItems(themeStrings, currentDayNightMode, { dialogInterface, selection ->
        tempSelectedThemeIndex = selection
      })
      .setPositiveButton(getString(R.string.ok), { dialog, which ->
        updateDayNightMode(tempSelectedThemeIndex)
        presenter.updateDayNightMode(tempSelectedThemeIndex)
      })
      .setNegativeButton(getString(R.string.cancel), null)
      .show()
  }

}