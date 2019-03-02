package com.example.lenovo.reader.fragments.addarticle

import com.example.lenovo.reader.annotations.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class AddArticleModule {

  @Binds
  @FragmentScope
  abstract fun provideSettingsPresenter(addArticlePresenterImpl: AddArticlePresenterImpl): AddArticlePresenter

}