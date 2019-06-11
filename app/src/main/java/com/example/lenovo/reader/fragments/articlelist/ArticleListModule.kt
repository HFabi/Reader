package com.example.lenovo.reader.fragments.articlelist

import com.example.lenovo.reader.annotations.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class ArticleListModule {

  @Binds
  @FragmentScope
  abstract fun provideArticleListView(articleFragment: ArticleListFragment): ArticleListView

  @Binds
  @FragmentScope
  abstract fun provideArticleListPresenter(articleListPresenterImpl: ArticleListPresenterImpl): ArticleListPresenter

}