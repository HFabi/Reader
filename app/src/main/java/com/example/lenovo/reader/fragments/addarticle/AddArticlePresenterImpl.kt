package com.example.lenovo.reader.fragments.addarticle

import android.icu.lang.UCharacter.DecompositionType
import android.util.Log
import com.example.lenovo.reader.controllers.ImageController
import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.model.bind
import com.example.model.datastores.ArticlesDataStore
import com.example.model.schedule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AddArticlePresenterImpl @Inject constructor() : BasePresenterImpl(), AddArticlePresenter {


  @Inject
  lateinit var articlesDataStore: ArticlesDataStore



  fun inputIsValid(url: String, category: String): Boolean = !url.isEmpty()


  override fun onSubmitClicked() {



//    articlesDataStore.addArticle(" ", " ")
//      .bind(compositeDisposable)
//      .schedule()
//      .subscribe(
//      { Log.d("TAGs","Added in PResenter")}, {error -> error.printStackTrace()}
//    )
  }

}