package com.example.lenovo.reader.fragments.dashboard

import com.example.lenovo.reader.annotations.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DashboardModule {

  @Binds
  @FragmentScope
  abstract fun provideDashboardView(dashboardFragment: DashboardFragment): DashboardView

  @Binds
  @FragmentScope
  abstract fun provideDashboardPresenter(dashboardPresenterImpl: DashboardPresenterImpl): DashboardPresenter


//  @JvmStatic
//  @Provides
//  @FragmentScope
//  internal fun provideDashboardPresenter(dashboardPresenterImpl: DashboardPresenterImpl): DashboardPresenter =
//    dashboardPresenterImpl
}
