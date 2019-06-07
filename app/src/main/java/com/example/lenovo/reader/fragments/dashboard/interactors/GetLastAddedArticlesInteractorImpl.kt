package com.example.lenovo.reader.fragments.dashboard.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.LastAddedArticle
import io.reactivex.Single
import java.util.Date
import javax.inject.Inject

class GetLastAddedArticlesInteractorImpl @Inject constructor() : GetLastAddedArticlesInteractor {

  @Inject
  lateinit var datastore: ArticlesDataStore

  override fun execute(): Single<List<LastAddedArticle>> {
//    return datastore.getLastAddedArticles(3)
    return Single.just(
      listOf(
        LastAddedArticle(1, "Building Awesome CMS _ Postlight _ Digital product studio", Date(), ""),
        LastAddedArticle(2, "Coroutines on Android (part I): Getting the background", Date(), ""),
        LastAddedArticle(3, "Patterns for accessing code from Dynamic Feature Modules", Date(), ""),
        LastAddedArticle(4, "Right way of setting margin on Recycler View’s cell", Date(), "")
      ))
  }
}