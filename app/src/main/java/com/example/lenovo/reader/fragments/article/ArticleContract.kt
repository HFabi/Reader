package com.example.lenovo.reader.fragments.article

import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.model.models.Article
import com.example.model.models.Category

interface ArticleView {
  fun showArticle(article: Article)
  fun articleId(): Long
  fun setContentFontSize(index: Int)
  fun setCategories(categories: List<Category>)
}

interface ArticlePresenter : BasePresenter {
  fun onFontSizeChanged()
}