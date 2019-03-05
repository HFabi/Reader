package com.example.lenovo.reader.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.fragments.about.AboutFragment
import com.example.lenovo.reader.fragments.addarticle.AddArticleFragment
import com.example.lenovo.reader.fragments.article.ArticleFragment
import com.example.lenovo.reader.fragments.dashboard.DashboardBuilder
import com.example.lenovo.reader.fragments.search.SearchFragment
import com.example.lenovo.reader.fragments.settings.SettingsFragment
import javax.inject.Inject

class RouterImpl @Inject constructor(var baseActivity: BaseActivity) : Router {

  override fun goBack() {
    baseActivity.onBackPressed()
  }

  override fun goToDashboard() {
    transit(DashboardBuilder().build())
  }

  override fun goToSettings() {
    transit(SettingsFragment())
  }

  override fun goToArticle() {
    transit(ArticleFragment())
  }

  override fun goToAddArticle() {
    transit(AddArticleFragment())
  }

  override fun goToSearch() {
    transit(SearchFragment())
  }

  override fun goToAbout() {
    transit(AboutFragment())
  }

  private fun transit(
    fragment: Fragment,
    addToBackstack: Boolean = true
  ) {
    if (addToBackstack) {
      baseActivity.supportFragmentManager
          .beginTransaction()
          .replace(baseActivity.provideContainerId(), fragment)
          .addToBackStack(fragment.javaClass.simpleName)
          .commit()
    } else {
      baseActivity.supportFragmentManager
          .beginTransaction()
          .replace(baseActivity.provideContainerId(), fragment)
          .commit()
    }
  }

  fun transit(
    fragment: Fragment,
    enterAnimation: Int,
    exitAnimation: Int,
    addToBackstack: Boolean = true
  ) {
    var transaction: FragmentTransaction = baseActivity.supportFragmentManager.beginTransaction();

    if (enterAnimation != null && exitAnimation != null) {
      transaction.setCustomAnimations(enterAnimation, exitAnimation)
    }
    transaction.replace(baseActivity.provideContainerId(), fragment)
    if (addToBackstack) {
      transaction.addToBackStack(fragment.javaClass.simpleName);
    }
    transaction.commitAllowingStateLoss();
  }

}
