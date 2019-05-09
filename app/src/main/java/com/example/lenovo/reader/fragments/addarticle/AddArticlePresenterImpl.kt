package com.example.lenovo.reader.fragments.addarticle

import android.util.Log
import com.example.lenovo.reader.fragments.addarticle.interactors.AddArticleInteractor
import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.model.bind
import com.example.model.schedule
import javax.inject.Inject

class AddArticlePresenterImpl @Inject constructor() : BasePresenterImpl(), AddArticlePresenter {

  @Inject
  lateinit var addArticleInteractor: AddArticleInteractor

  fun inputIsValid(url: String, category: String): Boolean = !url.isEmpty()

  override fun onSubmitClicked(url: String) {
    addArticleInteractor.execute(url)
      .bind(compositeDisposable)
      .schedule()
      .subscribe(
        { Log.d("TAGs", "Added in Presenter successfully") }, { error -> error.printStackTrace() }
      )
  }

}