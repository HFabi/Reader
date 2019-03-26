package com.example.lenovo.reader.fragments.search

import com.example.lenovo.reader.annotations.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class SearchModule {

  @Binds
  @FragmentScope
  abstract fun provideSearchPresenter(searchPresenterImpl: SearchPresenterImpl): SearchPresenter

  @Binds
  @FragmentScope
  abstract fun provideSearchView(searchFragment: SearchFragment): SearchView

}