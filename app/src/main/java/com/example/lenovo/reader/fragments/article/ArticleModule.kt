package com.example.lenovo.reader.fragments.article

import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.article.interactors.GetArticleInteractor
import com.example.lenovo.reader.fragments.article.interactors.GetArticleInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ArticleModule {

  @Binds
  @FragmentScope
  abstract fun provideArticleView(articleFragment: ArticleFragment): ArticleView

  @Binds
  @FragmentScope
  abstract fun provideArticlePresenter(articlePresenterImpl: ArticlePresenterImpl): ArticlePresenter

  @Binds
  @FragmentScope
  abstract fun provideGetArticlesInteractor(articleInteractorImpl: GetArticleInteractorImpl): GetArticleInteractor

}