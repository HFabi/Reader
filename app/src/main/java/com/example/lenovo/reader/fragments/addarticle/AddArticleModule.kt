package com.example.lenovo.reader.fragments.addarticle

import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.addarticle.interactors.AddArticleInteractor
import com.example.lenovo.reader.fragments.addarticle.interactors.AddArticleInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AddArticleModule {

  @Binds
  @FragmentScope
  abstract fun provideSettingsPresenter(addArticlePresenterImpl: AddArticlePresenterImpl): AddArticlePresenter

  @Binds
  @FragmentScope
  abstract fun provideAddArticleInteractor(addArticleInteractorImpl: AddArticleInteractorImpl): AddArticleInteractor

}