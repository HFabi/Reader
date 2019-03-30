package com.example.lenovo.reader.fragments.article

import android.annotation.SuppressLint
import android.util.Log
import com.example.lenovo.reader.fragments.article.interactors.GetArticleInteractor
import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.model.bind
import com.example.model.schedule
import javax.inject.Inject

class ArticlePresenterImpl @Inject constructor() : BasePresenterImpl(), ArticlePresenter {

  @Inject
  lateinit var view: ArticleView
  @Inject
  lateinit var getArticleInteractor: GetArticleInteractor

  @SuppressLint("CheckResult")
  override fun onResume() {
    super<BasePresenterImpl>.onResume()
    getArticleInteractor.execute(view.articleId())
      .schedule()
      .bind(compositeDisposable)
      .subscribe(
        { article -> view.showArticle(article) },
        { error ->  Log.d("E",""+error.printStackTrace())}
      )
  }

}