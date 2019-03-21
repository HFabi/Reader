package com.example.lenovo.reader.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.fragments.bottomnavigation.BottomNavigationFragment
import javax.inject.Inject

class RouterImpl @Inject constructor(var baseActivity: BaseActivity) : Router {

//  @Inject
//  lateinit var bottomNavigationFragment: BottomNavigationFragment

  override fun goBack() {
    //todo: adapt
    baseActivity.onBackPressed()
  }

  override fun goToDashboard(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment).navigate(R.id.dashboardFragment)

  }

  override fun goToSettings(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment).navigate(R.id.settingsFragment)
  }

  override fun goToArticle(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment).navigate(R.id.articleFragment)
  }

  override fun goToAddArticle(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment).navigate(R.id.addArticleFragment)

  }

  override fun goToSearch(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment).navigate(R.id.searchFragment)
  }

  override fun goToAbout(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment).navigate(R.id.aboutFragment)
  }

  override fun showBottomNavigation() {
//    bottomNavigationFragment.show(baseActivity.supportFragmentManager, bottomNavigationFragment.javaClass.simpleName)
    with(BottomNavigationFragment()) {
      show(baseActivity.supportFragmentManager, javaClass.simpleName)
    }
  }


//  private fun transit(
//    fragment: Fragment,
//    addToBackstack: Boolean = true
//  ) {
//    if (addToBackstack) {
//      baseActivity.supportFragmentManager
//          .beginTransaction()
//          .replace(baseActivity.provideContainerId(), fragment)
//          .addToBackStack(fragment.javaClass.simpleName)
//          .commit()
//    } else {
//      baseActivity.supportFragmentManager
//          .beginTransaction()
//          .replace(baseActivity.provideContainerId(), fragment)
//          .commit()
//    }
//  }
//
//  fun transit(
//    fragment: Fragment,
//    enterAnimation: Int,
//    exitAnimation: Int,
//    addToBackstack: Boolean = true
//  ) {
//    var transaction: FragmentTransaction = baseActivity.supportFragmentManager.beginTransaction();
//
//    if (enterAnimation != null && exitAnimation != null) {
//      transaction.setCustomAnimations(enterAnimation, exitAnimation)
//    }
//    transaction.replace(baseActivity.provideContainerId(), fragment)
//    if (addToBackstack) {
//      transaction.addToBackStack(fragment.javaClass.simpleName);
//    }
//    transaction.commitAllowingStateLoss();
//  }

}
