package com.example.lenovo.reader.activities.addarticle.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.Category
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
class AddArticleInteractorImpl @Inject constructor() : AddArticleInteractor {

  @Inject
  lateinit var dataStore: ArticlesDataStore

  override fun execute(url: String, categories: List<Category>?): Completable {
    return dataStore.addArticle(url, categories)
  }

}