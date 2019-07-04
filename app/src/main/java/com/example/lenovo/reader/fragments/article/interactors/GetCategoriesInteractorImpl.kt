package com.example.lenovo.reader.fragments.article.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.Category
import io.reactivex.Single
import javax.inject.Inject

class GetCategoriesInteractorImpl @Inject constructor() : GetCategoriesInteractor {

  @Inject
  lateinit var articlesDataStore: ArticlesDataStore

  override fun execute(articleId: Long): Single<List<Category>> {
    return articlesDataStore.getCategoriesForArticle(articleId)
  }
}