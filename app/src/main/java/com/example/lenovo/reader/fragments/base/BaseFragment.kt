package com.example.lenovo.reader.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lenovo.reader.annotations.Layout
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

  private val layoutResId: Int
    get() {
      val annotation = javaClass.getAnnotation(Layout::class.java)
      return if (annotation == null) {
        throw IllegalStateException("You must provide a layout annotation for " + javaClass.name)
      } else {
        (annotation as Layout).value
      }
    }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(layoutResId, container, false)
    return view
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    providePresenter()?.let { lifecycle.addObserver(it) }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    providePresenter()?.let { lifecycle.removeObserver(it) }
  }

  abstract fun providePresenter(): BasePresenter?

}
