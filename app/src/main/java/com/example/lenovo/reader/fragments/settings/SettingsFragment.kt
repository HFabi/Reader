package com.example.lenovo.reader.fragments.settings

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.lenovo.reader.R
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.navigation.Router
import kotlinx.android.synthetic.main.fragment_settings.settings_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_settings)
class SettingsFragment : BaseFragment(), SettingsView {

  @Inject
  lateinit var presenter: SettingsPresenter
  @Inject
  lateinit var router: Router

  override fun providePresenter(): BasePresenter = presenter

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(settings_toolbar, true)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId) {
      android.R.id.home -> router.goBack()
    }
    return super.onOptionsItemSelected(item)
  }

}