package com.example.lenovo.reader.fragments.article

import android.annotation.SuppressLint
import com.example.lenovo.reader.fragments.article.interactors.GetArticleInteractor
import com.example.lenovo.reader.fragments.article.interactors.GetFontSizeIndexInteractor
import com.example.lenovo.reader.fragments.article.interactors.SetFontSizeIndexInteractor
import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.model.bind
import com.example.model.models.Article
import com.example.model.schedule
import java.util.Date
import javax.inject.Inject

class ArticlePresenterImpl @Inject constructor() : BasePresenterImpl(), ArticlePresenter {

  @Inject
  lateinit var view: ArticleView
  @Inject
  lateinit var getArticleInteractor: GetArticleInteractor

  @Inject
  lateinit var getFontSizeIndexInteractor: GetFontSizeIndexInteractor

  @Inject
  lateinit var setFontSizeIndexInteractor: SetFontSizeIndexInteractor

  val fontSizes = arrayOf(16.0f, 18.0f, 20.0f) // in sp
  var currentFontSizeIndex = 0

  @SuppressLint("CheckResult")
  override fun onResume() {
    super<BasePresenterImpl>.onResume()
    setInitialFontSize()
    loadArticle()
  }

  override fun onFontSizeChanged() {
    currentFontSizeIndex = (currentFontSizeIndex + 1) % fontSizes.size
    setFontSizeIndexInteractor.execute(currentFontSizeIndex)
      .bind(compositeDisposable)
      .schedule()
      .subscribe({ },
        { error -> error.printStackTrace() })
    view.setContentFontSize(fontSizes[currentFontSizeIndex])
  }

  private fun setInitialFontSize() {
    getFontSizeIndexInteractor.execute()
      .bind(compositeDisposable)
      .schedule()
      .subscribe({ index ->
        view.setContentFontSize(fontSizes[index])
        currentFontSizeIndex = index
      })
  }

  private fun loadArticle() {
    getArticleInteractor.execute(view.articleId())
      .schedule()
      .bind(compositeDisposable)
      .subscribe(
        { article -> view.showArticle(article) },
        { error -> error.printStackTrace() }
      )
    view.showArticle(
      Article(5,
        "Mark-Uwe Kling",
        "Right way of setting margin on Recycler View’s cell",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        Date(),
        false, false))
  }

}