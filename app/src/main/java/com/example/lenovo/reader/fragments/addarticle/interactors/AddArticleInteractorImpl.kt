package com.example.lenovo.reader.fragments.addarticle.interactors

import com.example.model.datastores.ArticlesDataStore
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
class AddArticleInteractorImpl @Inject constructor() : AddArticleInteractor {

  @Inject
  lateinit var dataStore: ArticlesDataStore

  override fun execute(url: String): Completable {
    return dataStore.addArticle(url)
  }

}