package com.example.lenovo.reader.fragments.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenterImpl : BasePresenter {

  var compositeDisposable: CompositeDisposable = CompositeDisposable()

  override fun onDestroy() {
    super.onDestroy()
    compositeDisposable.dispose()
  }
}