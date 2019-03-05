package com.example.lenovo.reader.fragments.search

import com.example.lenovo.reader.R
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.LifecycleObserverPresenter
import javax.inject.Inject

@Layout(R.layout.fragment_search)
class SearchFragment: BaseFragment(), SearchView {

  @Inject
  lateinit var searchPresenter: SearchPresenter

  override fun providePresenter(): LifecycleObserverPresenter = searchPresenter
}