package com.example.lenovo.reader.navigation

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment

interface Router {

  fun goBack()

  fun goToDashboard(currentActivity: Activity)

  fun goToDashboard(currentFragment: Fragment)

  fun goToArticleList(currentFragment: Fragment)

  fun goToSettings(currentFragment: Fragment)

  fun goToArticle(currentFragment: Fragment, id: Long)

  fun goToArticle(currentFragment: Fragment, id: Long, view: View)

  fun goToAddArticle(currentFragment: Fragment)

  fun goToSearch(currentFragment: Fragment)

  fun goToAbout(currentFragment: Fragment)

  fun showBottomNavigation()
}
