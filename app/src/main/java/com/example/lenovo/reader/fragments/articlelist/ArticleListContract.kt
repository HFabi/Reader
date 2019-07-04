package com.example.lenovo.reader.fragments.articlelist

import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle

interface ArticleListView {
  fun replaceExcerptArticles(excerptArticles: List<ExcerptArticle>)
  fun appendExcerptArticles(excerptArticles: List<ExcerptArticle>)
  fun updateCategories(categories: List<Category>)
}

interface ArticleListPresenter : BasePresenter {
  fun loadExcerptArticles(categoryIds: List<Long>)
}
