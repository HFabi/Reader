package com.example.lenovo.reader.fragments.articlelist.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import io.reactivex.Single
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-06-13
 */
class GetExcerptArticlesInteractorImpl @Inject constructor() : GetExcerptArticlesInteractor {

  @Inject
  lateinit var articlesDataStore: ArticlesDataStore

  override fun execute(page: Int, categoryIds: List<Long>?): Single<List<ExcerptArticle>> {
    if(categoryIds != null) {
      return articlesDataStore.getExcerptArticles(page, categoryIds)
    } else {
      return articlesDataStore.getExcerptArticles(page)
    }
  }
}