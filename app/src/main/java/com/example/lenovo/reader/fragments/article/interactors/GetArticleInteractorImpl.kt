package com.example.lenovo.reader.fragments.article.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.Article
import io.reactivex.Single
import javax.inject.Inject

class GetArticleInteractorImpl @Inject constructor() : GetArticleInteractor {

  @Inject
  lateinit var articlesDataStore: ArticlesDataStore

  override fun execute(id: Int): Single<Article> {
    return articlesDataStore.getArticle(id)
  }

}