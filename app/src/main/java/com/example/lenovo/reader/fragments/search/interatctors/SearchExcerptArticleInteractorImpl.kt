package com.example.lenovo.reader.fragments.search.interatctors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.ExcerptArticle
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-04
 */
class SearchExcerptArticleInteractorImpl @Inject constructor() : SearchExcerptArticleInteractor {

  @Inject
  lateinit var articlesDataStore: ArticlesDataStore

  override fun execute(page: Int, searchString: String): Single<List<ExcerptArticle>> {
    return articlesDataStore.getExcerptArticles(page, searchString)
  }

}