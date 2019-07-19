package com.example.lenovo.reader.fragments.article.interactors

import com.example.model.datastores.ArticlesDataStore
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-18
 */
class DeleteArticleInteractorImpl @Inject constructor() : DeleteArticleInteractor {

  @Inject
  lateinit var articleDataStore: ArticlesDataStore

  override fun execute(articleId: Long): Single<Boolean> {
    return articleDataStore.deleteArticle(articleId)
  }

}