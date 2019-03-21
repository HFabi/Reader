package com.example.lenovo.reader.di

import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.activities.mainactivity.MainModule
import com.example.lenovo.reader.annotations.ActivityScope
import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.addarticle.AddArticleFragment
import com.example.lenovo.reader.fragments.addarticle.AddArticleModule
import com.example.lenovo.reader.fragments.article.ArticleFragment
import com.example.lenovo.reader.fragments.article.ArticleModule
import com.example.lenovo.reader.fragments.dashboard.DashboardFragment
import com.example.lenovo.reader.fragments.dashboard.DashboardModule
import com.example.lenovo.reader.fragments.about.AboutFragment
import com.example.lenovo.reader.fragments.about.AboutModule
import com.example.lenovo.reader.fragments.bottomnavigation.BottomNavigationFragment
import com.example.lenovo.reader.fragments.search.SearchFragment
import com.example.lenovo.reader.fragments.search.SearchModule
import com.example.lenovo.reader.fragments.settings.SettingsFragment
import com.example.lenovo.reader.fragments.settings.SettingsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @ActivityScope
  @ContributesAndroidInjector(modules = [MainModule::class])
  abstract fun mainInjector(): MainActivity

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
  @ContributesAndroidInjector(modules = [AddArticleModule::class])
  abstract fun addArticleInjector(): AddArticleFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [SearchModule::class])
  abstract fun searchViewInjector() : SearchFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [AboutModule::class])
  abstract fun aboutViewInjector() : AboutFragment

  @FragmentScope
  @ContributesAndroidInjector(modules = [])
  abstract fun bottomNavigationFragmentInjector() : BottomNavigationFragment

}