package com.example.lenovo.reader.activities.addarticle

import com.example.lenovo.reader.activities.addarticle.interactors.AddArticleInteractor
import com.example.lenovo.reader.activities.addarticle.interactors.AddArticleInteractorImpl
import com.example.lenovo.reader.activities.addarticle.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.activities.addarticle.interactors.GetCategoriesInteractorImpl
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.annotations.ActivityScope
import com.example.lenovo.reader.navigation.Router
import com.example.lenovo.reader.navigation.RouterImpl
import dagger.Binds
import dagger.Module

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
@Module
abstract class AddArticleModule {

  @Binds
  @ActivityScope
  abstract fun provideActivityContext(addArticleActivity: AddArticleActivity): BaseActivity

  @Binds
  @ActivityScope
  abstract fun provideAddArticleView(addArticleActivity: AddArticleActivity): AddArticleView

  @Binds
  @ActivityScope
  abstract fun provideAddArticlePresenter(addArticlePresenterImpl: AddArticlePresenterImpl): AddArticlePresenter

  @Binds
  @ActivityScope
  abstract fun provideRouter(router: RouterImpl): Router

  @Binds
  @ActivityScope
  abstract fun provideAddArticleInteractor(addArticleInteractorImpl: AddArticleInteractorImpl): AddArticleInteractor

  @Binds
  @ActivityScope
  abstract fun provideGetCategoriesInteractor(getCategoriesInteractorImpl: GetCategoriesInteractorImpl): GetCategoriesInteractor

}