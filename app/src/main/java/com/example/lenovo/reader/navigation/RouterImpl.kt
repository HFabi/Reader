package com.example.lenovo.reader.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.fragments.bottomnavigation.BottomNavigationFragment
import javax.inject.Inject

class RouterImpl @Inject constructor(var baseActivity: BaseActivity) : Router {

  override fun goBack() {
    //todo: adapt
    baseActivity.onBackPressed()
  }

  override fun goToDashboard(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
        .navigate(R.id.dashboardFragment)

  }

  override fun goToSettings(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
        .navigate(R.id.settingsFragment)
  }

  override fun goToArticle(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
        .navigate(R.id.articleFragment)
  }

  override fun goToAddArticle(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
        .navigate(R.id.addArticleFragment)
  }

  override fun goToSearch(currentFragment: Fragment) {
//    NavHostFragment.findNavController(currentFragment).navigate(R.id.searchFragment)
    NavHostFragment.findNavController(currentFragment)
        .navigate(R.id.action_dashboardFragment_to_searchFragment)
  }

  override fun goToAbout(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
        .navigate(R.id.aboutFragment)
  }

  override fun showBottomNavigation() {
    with(BottomNavigationFragment()) {
      show(baseActivity.supportFragmentManager, javaClass.simpleName)
    }
  }
}
