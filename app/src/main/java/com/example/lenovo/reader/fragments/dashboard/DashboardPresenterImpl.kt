package com.example.lenovo.reader.fragments.dashboard

import android.util.Log
import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.lenovo.reader.fragments.dashboard.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.fragments.dashboard.interactors.GetFavoriteArticlesInteractor
import com.example.lenovo.reader.fragments.dashboard.interactors.GetLastAddedArticlesInteractor
import com.example.model.bind
import com.example.model.schedule
import javax.inject.Inject

class DashboardPresenterImpl @Inject constructor() : BasePresenterImpl(), DashboardPresenter {

  @Inject
  lateinit var view: DashboardView
  @Inject
  lateinit var getLastAddedArticlesInteractor: GetLastAddedArticlesInteractor
  @Inject
  lateinit var getFavoriteArticlesInteractor: GetFavoriteArticlesInteractor
  @Inject
  lateinit var getCategoriesInteractor: GetCategoriesInteractor

  override fun onCreate() {
    super<BasePresenterImpl>.onCreate()
    Log.d("LOG", "DashboardPresenter onCreate")
    initializeView()
  }

  fun initializeView() {
    getLastAddedArticlesInteractor.execute()
        .schedule()
        .bind(compositeDisposable)
        .subscribe { articles ->
          view.updateLastAddedArticles(articles)
        }
    getFavoriteArticlesInteractor.execute()
        .schedule()
        .bind(compositeDisposable)
        .subscribe { favorites ->
          view.updateFavoriteArticles(favorites)
        }
    getCategoriesInteractor.execute()
        .schedule()
        .bind(compositeDisposable)
        .subscribe { categories ->
          view.updateCategories(categories)
        }
  }
}
