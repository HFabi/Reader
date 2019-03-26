package com.example.lenovo.reader.fragments.dashboard.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.LastAddedArticle
import io.reactivex.Single
import javax.inject.Inject

class GetLastAddedArticlesInteractorImpl @Inject constructor() : GetLastAddedArticlesInteractor {

  @Inject
  lateinit var datastore: ArticlesDataStore

  override fun execute(): Single<List<LastAddedArticle>> {
    return datastore.getLastAddedArticles(3)
//        return Single.just(listOf( LastAddedArticle(3, "Stet clita kasd", Date(), "")))
  }
}