package com.example.lenovo.reader.fragments.article

import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.article.interactors.GetArticleInteractor
import com.example.lenovo.reader.fragments.article.interactors.GetArticleInteractorImpl
import com.example.lenovo.reader.fragments.article.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.fragments.article.interactors.GetCategoriesInteractorImpl
import com.example.lenovo.reader.fragments.article.interactors.GetFontSizeIndexInteractor
import com.example.lenovo.reader.fragments.article.interactors.GetFontSizeIndexInteractorImpl
import com.example.lenovo.reader.fragments.article.interactors.SetFontSizeIndexInteractor
import com.example.lenovo.reader.fragments.article.interactors.SetFontSizeIndexInteractorImpl
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

  @Binds
  @FragmentScope
  abstract fun provideGetFontSizeIndexInteractor(getFontSizeIndexInteractorImpl: GetFontSizeIndexInteractorImpl): GetFontSizeIndexInteractor

  @Binds
  @FragmentScope
  abstract fun provideSetFontSizeIndexInteractor(setFontSizeIndexInteractorImpl: SetFontSizeIndexInteractorImpl): SetFontSizeIndexInteractor

  @Binds
  @FragmentScope
  abstract fun provideGetCategoriesInteractor(getCategoriesInteractorImpl: GetCategoriesInteractorImpl): GetCategoriesInteractor


}