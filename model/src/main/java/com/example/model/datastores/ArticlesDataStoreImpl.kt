package com.example.model.datastores

import com.example.model.controllers.ArticleController
import com.example.model.datasources.db.ArticlesDbDataSource
import com.example.model.datasources.local.SharedPreferencesDataSource
import com.example.model.datasources.web.ArticlesWebDataSource
import com.example.model.models.Article
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import com.example.model.models.LastAddedArticle
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ArticlesDataStoreImpl @Inject constructor() : ArticlesDataStore {

  @Inject
  lateinit var articlesWebDataSource: ArticlesWebDataSource
  @Inject
  lateinit var articlesDbDataSource: ArticlesDbDataSource
  @Inject
  lateinit var articleController: ArticleController
  @Inject
  lateinit var sharedPreferencesDataSource: SharedPreferencesDataSource

  override fun getLastAddedArticles(count: Int): Single<List<LastAddedArticle>> {
    return articlesDbDataSource.getLastAddedArticles()
  }

  override fun getExcerptArticles(page: Int, categories: List<Category>): Single<List<ExcerptArticle>> {
    return articlesDbDataSource.getExcerptArticles(page, categories)
  }

  override fun getArticle(id: Int): Single<Article> {
    return articlesDbDataSource.getArticleById(id)
  }

  override fun getCategories(): Single<List<Category>> {
    return articlesDbDataSource.getCategories()
  }

  override fun addArticle(url: String, category: List<String>): Completable {
    return articlesWebDataSource.getArticle(url)
      .flatMap(articleController::processArticle)
      .flatMapCompletable { article -> articlesDbDataSource.addArticle(article) }
  }

  override fun setArticleFontSizeIndex(value: Int): Completable {
    return sharedPreferencesDataSource.setArticleFontSizeIndex(value)
  }

  override fun getArticleFontSizeIndex(): Single<Int> {
    return sharedPreferencesDataSource.getArticleFontSizeIndex()
  }
}
