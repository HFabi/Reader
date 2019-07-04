package com.example.lenovo.reader.fragments.search

import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.lenovo.reader.fragments.search.interatctors.SearchExcerptArticleInteractor
import com.example.model.bind
import com.example.model.schedule
import timber.log.Timber
import javax.inject.Inject

class SearchPresenterImpl @Inject constructor() : BasePresenterImpl(), SearchPresenter {

  @Inject
  lateinit var view: SearchView

  @Inject
  lateinit var excerptArticleInteractor: SearchExcerptArticleInteractor

  var page = 0


  override fun onResume() {
    super<BasePresenterImpl>.onResume()
  }

  override fun searchExcerptArticles(searchString: String) {
    excerptArticleInteractor.execute(page, searchString)
      .bind(compositeDisposable)
      .schedule()
      .subscribe(
        (view::updateExcerptArticles),
        {error -> Timber.d(error)}
      )
  }



}