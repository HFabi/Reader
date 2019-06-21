package com.example.lenovo.reader.fragments.articlelist.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import io.reactivex.Single
import java.util.Date
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-06-13
 */
class GetExcerptArticlesInteractorImpl @Inject constructor() : GetExcerptArticlesInteractor {

  @Inject
  lateinit var articlesDataStore: ArticlesDataStore

  override fun execute(page: Int, categories: List<Category>): Single<List<ExcerptArticle>> {
//    return Single.just(listOf(
//      ExcerptArticle(1, "Building Awesome CMS _ Postlight _ Digital product studio", Date(), ""),
//      ExcerptArticle(2, "Coroutines on Android", Date(), ""),
//      ExcerptArticle(3, "Patterns for accessing code from Dynamic Feature Modules from Dynamic Feature Modules", Date(), ""),
//      ExcerptArticle(4, "Right way of setting margin on Recycler View’s", Date(), ""),
//      ExcerptArticle(5, "On Android (part I): Getting", Date(), ""),
//      ExcerptArticle(6, "For accessing code from Dynamic Feature Modules from Dynamic Feature Modules", Date(), ""),
//      ExcerptArticle(7, "Way of setting margin on Recycler View’s cell", Date(), "")
//    ))
    //
    return articlesDataStore.getExcerptArticles(page, categories)
  }
}