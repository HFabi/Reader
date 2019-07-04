package com.example.lenovo.reader.fragments.dashboard

import com.example.lenovo.reader.annotations.FragmentScope
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
  abstract fun provideGetLastAddedArticlesInteractor(getLastAddedArticlesInteractorImpl: GetLastAddedArticlesInteractorImpl): GetLastAddedArticlesInteractor


}
