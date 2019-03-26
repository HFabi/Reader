package com.example.lenovo.reader.fragments.about

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.lenovo.reader.R
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.navigation.Router
import kotlinx.android.synthetic.main.fragment_about.about_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_about)
class AboutFragment : BaseFragment() {

  @Inject
  lateinit var router: Router

  override fun providePresenter(): BasePresenter? = null

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(about_toolbar, true)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> router.goBack()
    }
    return super.onOptionsItemSelected(item)
  }
}