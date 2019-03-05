package com.example.lenovo.reader.fragments.search

import com.example.lenovo.reader.fragments.base.BasePresenter
import javax.inject.Inject

class SearchPresenterImpl @Inject constructor(): BasePresenter(), SearchPresenter {

  @Inject
  lateinit var searchView: SearchView


}