package com.example.lenovo.reader.navigation

import android.app.Activity
import android.view.View
import androidx.core.app.NavUtils.navigateUpFromSameTask
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.fragments.articlelist.ArticleListFragment
import com.example.lenovo.reader.fragments.articlelist.ArticleListFragmentDirections
import com.example.lenovo.reader.fragments.bottomnavigation.BottomMenuFragment
import com.example.lenovo.reader.fragments.dashboard.DashboardFragment
import com.example.lenovo.reader.fragments.dashboard.DashboardFragmentDirections
import com.example.lenovo.reader.fragments.search.SearchFragment
import com.example.lenovo.reader.fragments.search.SearchFragmentDirections
import javax.inject.Inject

class RouterImpl @Inject constructor(var baseActivity: BaseActivity) : Router {


  override fun goBack() {
    //todo: adapt
    baseActivity.onBackPressed()
  }

  fun goToParent() {
//    navigateUpFromSameTask()
  }

  override fun goToDashboard(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
      .navigate(R.id.dashboardFragment)

  }

  override fun goToDashboard(currentActivity: Activity) {
//    //addArticleActivity
//    NavHostFragment.findNavController(currentActivity)
//      .navigate(R.id.dashboardFragment)
    //TODO
  }

  override fun goToSettings(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
      .navigate(R.id.settingsFragment)
  }

  override fun goToArticle(currentFragment: Fragment, id: Long) {
    if (currentFragment is ArticleListFragment) {
      val action = ArticleListFragmentDirections.actionArticleListFragmentToArticleFragment(id)
      NavHostFragment.findNavController(currentFragment)
        .navigate(action)
    } else if (currentFragment is SearchFragment) {
      val action = SearchFragmentDirections.actionSearchFragmentToArticleFragment(id)
      NavHostFragment.findNavController(currentFragment)
        .navigate(action)
    }
  }

  override fun goToArticle(currentFragment: Fragment, id: Long, view: View) {

//    val extras = FragmentNavigatorExtras(
//      imageView to "header_image",
//      titleView to "header_title")

    val extras = FragmentNavigator.Extras.Builder()
      .addSharedElement(view, "secondTransitionName").build()
    val action = DashboardFragmentDirections.actionDashboardFragmentToArticleFragment(id)
    NavHostFragment.findNavController(currentFragment)
      .navigate(action, extras)
  }

//  override fun goToAddArticle(currentFragment: Fragment) {
//    NavHostFragment.findNavController(currentFragment)
//      .navigate(R.id.addArticleFragment)
//  }

  override fun goToAddArticle(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
      .navigate(R.id.addArticleActivity)
  }

  override fun goToArticleList(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
      .navigate(R.id.articleListFragment)
  }

  override fun goToSearch(currentFragment: Fragment) {
//    NavHostFragment.findNavController(currentFragment).navigate(R.id.searchFragment)
    if (currentFragment is ArticleListFragment) {
      NavHostFragment.findNavController(currentFragment)
        .navigate(R.id.action_articleListFragment_to_searchFragment)
    } else if (currentFragment is DashboardFragment) {
      NavHostFragment.findNavController(currentFragment)
        .navigate(R.id.action_dashboardFragment_to_searchFragment)
    }
  }

  override fun goToAbout(currentFragment: Fragment) {
    NavHostFragment.findNavController(currentFragment)
      .navigate(R.id.aboutFragment)
  }

  override fun showBottomNavigation() {
    with(BottomMenuFragment()) {
      show(baseActivity.supportFragmentManager, javaClass.simpleName)
    }
  }


}
