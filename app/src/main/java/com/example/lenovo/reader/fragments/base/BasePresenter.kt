package com.example.lenovo.reader.fragments.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter {

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

}