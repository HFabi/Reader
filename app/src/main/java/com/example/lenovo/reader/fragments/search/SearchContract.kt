package com.example.lenovo.reader.fragments.search

import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.model.models.ExcerptArticle

interface SearchView {
  fun updateExcerptArticles(excerptArticles: List<ExcerptArticle>)
}

interface SearchPresenter : BasePresenter {
  fun searchExcerptArticles(searchString: String)
}