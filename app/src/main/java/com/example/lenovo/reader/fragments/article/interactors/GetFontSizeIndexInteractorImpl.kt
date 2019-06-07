package com.example.lenovo.reader.fragments.article.interactors

import com.example.model.datastores.ArticlesDataStore
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
class GetFontSizeIndexInteractorImpl @Inject constructor(): GetFontSizeIndexInteractor {

  @Inject
  lateinit var articlesDataStore: ArticlesDataStore

  override fun execute(): Single<Int> {
    return articlesDataStore.getArticleFontSizeIndex()
  }

}