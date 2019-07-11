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

  override fun getExcerptArticles(page: Int, categoryIds: List<Long>): Single<List<ExcerptArticle>> {
    return articlesDbDataSource.getExcerptArticles(page, categoryIds)
  }

  override fun getExcerptArticles(page: Int): Single<List<ExcerptArticle>> {
    return articlesDbDataSource.getExcerptArticles(page)
  }

  override fun getExcerptArticles(page: Int, serachString: String): Single<List<ExcerptArticle>> {
    return articlesDbDataSource.getExcerptArticles(page, serachString)
  }

  override fun getArticle(id: Long): Single<Article> {
    return articlesDbDataSource.getArticleById(id)
  }

  override fun getCategories(): Single<List<Category>> {
    return articlesDbDataSource.getCategories()
  }

  override fun getCategoriesForArticle(articleId: Long): Single<List<Category>> {
    return articlesDbDataSource.getCategoriesForArticle(articleId)
  }

  override fun addArticle(url: String, category: List<Category>?): Completable {
    return articlesWebDataSource.getArticle(url)
      .flatMap(articleController::processArticle)
      .flatMapCompletable { article -> articlesDbDataSource.addArticle(article, category) }
  }

  override fun setArticleFontSizeIndex(value: Int): Completable {
    return sharedPreferencesDataSource.setArticleFontSizeIndex(value)
  }

  override fun getArticleFontSizeIndex(): Single<Int> {
    return sharedPreferencesDataSource.getArticleFontSizeIndex()
  }
}
