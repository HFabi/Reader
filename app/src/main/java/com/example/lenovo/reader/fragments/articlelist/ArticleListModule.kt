package com.example.lenovo.reader.fragments.articlelist

import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.articlelist.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.fragments.articlelist.interactors.GetCategoriesInteractorImpl
import com.example.lenovo.reader.fragments.articlelist.interactors.GetExcerptArticlesInteractor
import com.example.lenovo.reader.fragments.articlelist.interactors.GetExcerptArticlesInteractorImpl
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

  @Binds
  @FragmentScope
  abstract fun provideGetExcerptArticlesInteractor(getExcerptArticlesInteractorImpl: GetExcerptArticlesInteractorImpl): GetExcerptArticlesInteractor

  @Binds
  @FragmentScope
  abstract fun provideGetCategoriesInteractor(getCategoriesInteractorImpl: GetCategoriesInteractorImpl): GetCategoriesInteractor

}