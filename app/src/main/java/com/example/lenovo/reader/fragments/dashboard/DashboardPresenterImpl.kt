package com.example.lenovo.reader.fragments.dashboard

import android.util.Log
import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.lenovo.reader.fragments.dashboard.interactors.GetLastAddedArticlesInteractor
import com.example.model.bind
import com.example.model.schedule
import javax.inject.Inject

class DashboardPresenterImpl @Inject constructor() : BasePresenterImpl(), DashboardPresenter {

  @Inject
  lateinit var view: DashboardView
  @Inject
  lateinit var getLastAddedArticlesInteractor: GetLastAddedArticlesInteractor

  override fun onCreate() {
    super<BasePresenterImpl>.onCreate()
    Log.d("LOG", "DashboardPresenter onCreate")
    initializeView()
  }

  fun initializeView() {
    getLastAddedArticlesInteractor.execute(10)
        .schedule()
        .bind(compositeDisposable)
        .subscribe { articles ->
          view.updateLastAddedArticles(articles)
        }
  }

  override fun reloadLastAddedArticles() {
    getLastAddedArticlesInteractor.execute(10)
      .schedule()
      .bind(compositeDisposable)
      .subscribe { articles ->
        view.updateLastAddedArticles(articles)
      }
  }
}
