package com.example.lenovo.reader.activities.mainactivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.fragments.articlelist.ArticleListFragment
import com.example.lenovo.reader.fragments.dashboard.DashboardFragment
import com.example.lenovo.reader.fragments.settings.SettingsFragment
import com.example.lenovo.reader.navigation.Router
import kotlinx.android.synthetic.main.activity_main.main_bottom_navigation
import javax.inject.Inject

//@ContentView(R.layout.activity_main)
class MainActivity : BaseActivity(R.layout.activity_main) {

  @Inject lateinit var router: Router

  var currentFragment: Fragment? = null

  override fun provideContainerId(): Int = R.id.nav_host_fragment //TODO: this is not correct

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    main_bottom_navigation.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.action_bottom_dashboard -> {
          Log.d("CLICK", "NAV:: toDashboard")
          currentFragment?.let {
            if (it !is DashboardFragment) {
              router.goToDashboard(it)
            }
          }
        }
        R.id.action_bottom_article_list -> {
//        Log.d("CLICK", "onOptionsItemSelected:: Navi")
          currentFragment?.let {
            if (it !is ArticleListFragment) {
              router.goToArticleList(it)
            }
          }
        }
        R.id.action_bottom_settings -> {
          Log.d("CLICK", "NAV:: toSettings")
          currentFragment?.let {
            if (it !is SettingsFragment) {
              router.goToSettings(it)
            }
          }
        }
      }
      true
    }

  }

  fun setBottomNavigationEnabled(isEnabled: Boolean) {
    main_bottom_navigation.visibility = if (isEnabled) View.VISIBLE else View.GONE
  }

}
