package com.example.lenovo.reader.fragments.dashboard

import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.dashboard.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.fragments.dashboard.interactors.GetCategoriesInteractorImpl
import com.example.lenovo.reader.fragments.dashboard.interactors.GetFavoriteArticlesInteractor
import com.example.lenovo.reader.fragments.dashboard.interactors.GetFavoriteArticlesInteractorImpl
import com.example.lenovo.reader.fragments.dashboard.interactors.GetLastAddedArticlesInteractor
import com.example.lenovo.reader.fragments.dashboard.interactors.GetLastAddedArticlesInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DashboardModule {

  @Binds
  @FragmentScope
  abstract fun provideDashboardView(dashboardFragment: DashboardFragment): DashboardView

  @Binds
  @FragmentScope
  abstract fun provideDashboardPresenter(dashboardPresenterImpl: DashboardPresenterImpl): DashboardPresenter

  @Binds
  @FragmentScope
  abstract fun provideGetCategoriesInteractor(getCategoriesInteractorImpl: GetCategoriesInteractorImpl): GetCategoriesInteractor

  @Binds
  @FragmentScope
  abstract fun provideGetFavoriteArticlesInteractor(getFavoriteArticlesInteractorImpl: GetFavoriteArticlesInteractorImpl): GetFavoriteArticlesInteractor

  @Binds
  @FragmentScope
  abstract fun provideGetLastAddedArticlesInteractor(getLastAddedArticlesInteractorImpl: GetLastAddedArticlesInteractorImpl): GetLastAddedArticlesInteractor

//  @JvmStatic
//  @Provides
//  @FragmentScope
//  internal fun provideDashboardPresenter(dashboardPresenterImpl: DashboardPresenterImpl): DashboardPresenter =
//    dashboardPresenterImpl
}
