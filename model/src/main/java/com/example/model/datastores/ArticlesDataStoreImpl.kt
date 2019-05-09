package com.example.model.datastores

import android.util.Log
import com.example.model.controllers.ArticleController
import com.example.model.controllers.DownloadController
import com.example.model.datasources.db.ArticlesDbDataSource
import com.example.model.datasources.web.ArticlesWebDataSource
import com.example.model.models.Article
import com.example.model.models.DownloadResult
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ArticlesDataStoreImpl @Inject constructor() : ArticlesDataStore {

  @Inject
  lateinit var downloadController: DownloadController
  @Inject
  lateinit var articlesWebDataSource: ArticlesWebDataSource
  @Inject
  lateinit var articlesDbDataSource: ArticlesDbDataSource
  @Inject
  lateinit var articleController: ArticleController


  override fun getFavoriteArticles(): Single<List<FavoriteArticle>> {
    return articlesDbDataSource.getFavoriteArticles()
  }

  override fun getCategories() {

  }

  override fun getArticles(count: Int, skip: Int): Single<List<Article>> {
    return articlesDbDataSource.getArticles(count, skip)
  }

  override fun getArticle(id: Int): Single<Article> {
    return articlesDbDataSource.getArticleById(id)
  }

  override fun getLastAddedArticles(count: Int): Single<List<LastAddedArticle>> {
    return articlesDbDataSource.getLastAddedArticles()
  }

  override fun addArticle(url: String, categroy: String): Completable {
    return articlesWebDataSource.parseHtmlFromUrl(url)
      .flatMap{ article ->  articleController.parse(article)}
      .flatMapObservable{ (article, downloadTaskList) ->
         articlesDbDataSource.addArticle(article)
           .andThen(downloadController.load(downloadTaskList))
      }
      .map { downloadResult -> Log.d("DOWNLOAD", downloadResult.toString())  }
      .flatMapCompletable { e -> Completable.complete() }
  }
}


//  override fun addArticle(url: String, categroy: String): Completable {
//    return articlesWebDataSource.parseHtmlFromUrl(url)
//      .flatMap{ article ->  articleController.parse(article)}
//      .flatMapCompletable { article -> articlesDbDataSource.addArticle(article) }
//  }
