package com.example.lenovo.reader.fragments.addarticle.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.Category
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-04
 */
class GetCategoriesInteractorImpl @Inject constructor(): GetCategoriesInteractor {

  @Inject
  lateinit var articlesDataStore: ArticlesDataStore

  override fun execute(): Single<List<Category>> {
    return articlesDataStore.getCategories()
  }
}