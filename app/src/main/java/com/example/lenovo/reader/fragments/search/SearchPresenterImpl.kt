package com.example.lenovo.reader.fragments.search

import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import javax.inject.Inject

class SearchPresenterImpl @Inject constructor() : BasePresenterImpl(), SearchPresenter {

  @Inject
  lateinit var searchView: SearchView

}