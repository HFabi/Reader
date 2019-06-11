package com.example.lenovo.reader.fragments.articlelist

import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import javax.inject.Inject

class ArticleListPresenterImpl @Inject constructor(): BasePresenterImpl(), ArticleListPresenter {

  @Inject
  lateinit var view: ArticleListView
}