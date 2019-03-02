package com.example.lenovo.reader.fragments.dashboard

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.navigation.Router
import kotlinx.android.synthetic.main.fragment_dashboard.dashboard_bottomappbar
import kotlinx.android.synthetic.main.fragment_dashboard.dashboard_floatingactionbutton
import javax.inject.Inject

@Layout(R.layout.fragment_dashboard)
class DashboardFragment : BaseFragment(), DashboardView {

  @Inject
  lateinit var dashboardPresenter: DashboardPresenter
  @Inject
  lateinit var router: Router
  @Inject
  lateinit var bottomNavigationFragment: BottomNavigationFragment

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    Log.d("LOGT", "Dashboardfragment onViewCreated")
    (activity as BaseActivity).setSupportActionBar(dashboard_bottomappbar)
    setHasOptionsMenu(true)
    setUp()
  }

  private fun setUp() {
    dashboard_floatingactionbutton.setOnClickListener {
      router.goToAddArticle()
    }
  }

  override fun providePresenter(): BasePresenter = dashboardPresenter

  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    inflater.inflate(R.menu.menu_dashboard, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_main_search -> {
        Log.d("CLICK", "onOptionsItemSelected:: Search")
        router.goToSearch()
      }
      android.R.id.home -> {
        Log.d("CLICK", "onOptionsItemSelected:: Navi")
        bottomNavigationFragment.show(
            (activity as BaseActivity).supportFragmentManager,
            bottomNavigationFragment.javaClass.simpleName
        )
      }
    }
    return super.onOptionsItemSelected(item)
  }

}
