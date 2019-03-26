package com.example.lenovo.reader.fragments.dashboard.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.FavoriteArticle
import io.reactivex.Single
import javax.inject.Inject

class GetFavoriteArticlesInteractorImpl @Inject constructor() : GetFavoriteArticlesInteractor {

  @Inject
  lateinit var dataStore: ArticlesDataStore

  override fun execute(): Single<List<FavoriteArticle>> {
    return dataStore.getFavoriteArticles()
  }
}