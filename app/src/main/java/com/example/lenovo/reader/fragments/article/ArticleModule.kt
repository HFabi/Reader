package com.example.lenovo.reader.fragments.article

import com.example.lenovo.reader.annotations.FragmentScope
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

}