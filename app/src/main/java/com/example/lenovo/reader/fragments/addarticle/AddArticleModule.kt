package com.example.lenovo.reader.fragments.addarticle

import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.addarticle.interactors.AddArticleInteractor
import com.example.lenovo.reader.fragments.addarticle.interactors.AddArticleInteractorImpl
import com.example.lenovo.reader.fragments.addarticle.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.fragments.addarticle.interactors.GetCategoriesInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AddArticleModule {

  @Binds
  @FragmentScope
  abstract fun provideSettingsPresenter(addArticlePresenterImpl: AddArticlePresenterImpl): AddArticlePresenter

  @Binds
  @FragmentScope
  abstract fun provideAddArticleView(addArticleFragment: AddArticleFragment): AddArticleView

  @Binds
  @FragmentScope
  abstract fun provideAddArticleInteractor(addArticleInteractorImpl: AddArticleInteractorImpl): AddArticleInteractor

  @Binds
  @FragmentScope
  abstract fun provideGetCategoriesInteractor(getCategoriesInteractorImpl: GetCategoriesInteractorImpl) : GetCategoriesInteractor

}