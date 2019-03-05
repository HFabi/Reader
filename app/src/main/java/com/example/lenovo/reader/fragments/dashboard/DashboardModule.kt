package com.example.lenovo.reader.fragments.dashboard

import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.dashboard.interactors.*
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
