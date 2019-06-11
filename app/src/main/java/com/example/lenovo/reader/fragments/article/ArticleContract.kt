package com.example.lenovo.reader.fragments.article

import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.model.models.Article

interface ArticleView {
  fun showArticle(article: Article)
  fun articleId(): Int
  fun setContentFontSize(index: Int)
}

interface ArticlePresenter : BasePresenter {
  fun onFontSizeChanged()
}