package com.example.lenovo.reader.di

import com.example.lenovo.reader.activities.addarticle.AddArticleActivity
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.activities.mainactivity.MainModule
import com.example.lenovo.reader.annotations.ActivityScope
import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.about.AboutFragment
import com.example.lenovo.reader.fragments.about.AboutModule
import com.example.lenovo.reader.fragments.addarticle.AddArticleFragment
import com.example.lenovo.reader.fragments.addarticle.AddArticleModule
import com.example.lenovo.reader.fragments.article.ArticleFragment
import com.example.lenovo.reader.fragments.article.ArticleModule
import com.example.lenovo.reader.fragments.articlelist.ArticleListFragment
import com.example.lenovo.reader.fragments.articlelist.ArticleListModule
import com.example.lenovo.reader.fragments.bottomnavigation.BottomMenuFragment
import com.example.lenovo.reader.fragments.dashboard.DashboardFragment
import com.example.lenovo.reader.fragments.dashboard.DashboardModule
import com.example.lenovo.reader.fragments.search.SearchFragment
import com.example.lenovo.reader.fragments.search.SearchModule
import com.example.lenovo.reader.fragments.settings.SettingsFragment
import com.example.lenovo.reader.fragments.settings.SettingsModule
import com.example.lenovo.reader.services.DownloadArticleService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @ActivityScope
  @ContributesAndroidInjector(
      modules = [MainModule::class, ControllerModule::class, FragmentBuilder::class]
  )
  abstract fun mainInjector(): MainActivity

  @ActivityScope
  @ContributesAndroidInjector(
    modules = [com.example.lenovo.reader.activities.addarticle.AddArticleModule::class, ControllerModule::class]
  )
  abstract fun addArticleInjector(): AddArticleActivity
}

@Module
abstract class FragmentBuilder {

  @FragmentScope
  @ContributesAndroidInjector(modules = [DashboardModule::class])
  abstract fun dashboardInjector(): DashboardFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [SettingsModule::class])
  abstract fun settingsInjector(): SettingsFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [ArticleModule::class])
  abstract fun articleInjector(): ArticleFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [ArticleListModule::class])
  abstract fun articleListInjector(): ArticleListFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [AddArticleModule::class])
  abstract fun addArticleInjector(): AddArticleFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [SearchModule::class])
  abstract fun searchViewInjector(): SearchFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [AboutModule::class])
  abstract fun aboutViewInjector(): AboutFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [])
  abstract fun bottomNavigationFragmentInjector(): BottomMenuFragment

}

@Module
abstract class ServiceBuilder {

  @ContributesAndroidInjector(modules = [])
  abstract fun downloadArticleServiceInjector(): DownloadArticleService

}