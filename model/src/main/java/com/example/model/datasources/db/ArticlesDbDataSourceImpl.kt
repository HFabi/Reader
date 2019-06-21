package com.example.model.datasources.db

import com.example.model.models.Article
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import com.example.model.models.LastAddedArticle
import com.example.model.transformers.ArticleTransformer
import com.example.model.transformers.CategoryTransformer
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
class ArticlesDbDataSourceImpl @Inject constructor() : ArticlesDbDataSource {

  @Inject
  lateinit var articleDao: ArticleDao
  @Inject
  lateinit var categoryDao: CategoryDao
  @Inject
  lateinit var articleCategoryDao: ArticleCategoryDao
  @Inject
  lateinit var articleTransformer: ArticleTransformer
  @Inject
  lateinit var categoryTransformer: CategoryTransformer

  private val articlesPerPage: Int = 10

  override fun getArticleById(id: Int): Single<Article> {
    return articleDao.getArticleById(id)
      .map { articleDbEntity -> articleTransformer.toModel(articleDbEntity) }
  }

  override fun getLastAddedArticles(): Single<List<LastAddedArticle>> {
    return articleDao.getLastAddedArticles()
      .flatMapObservable { list -> Observable.fromIterable(list) }
      .map { articleDbEntity -> articleTransformer.toLastAddedArticle(articleDbEntity) }
      .toList()
  }

  override fun addArticle(article: Article): Completable {
    return articleDao.addArticle(articleTransformer.toDbEntity(article))
  }

  override fun getExcerptArticles(page: Int, categories: List<Category>): Single<List<ExcerptArticle>> {
    val skip = articlesPerPage * page
    return articleDao.getExcerptArticles(articlesPerPage, skip)
      .flatMapObservable { list -> Observable.fromIterable(list) }
      .map { articleDbEntity -> articleTransformer.toModel(articleDbEntity) }
      .toList()
  }

  override fun getCategories(): Single<List<Category>> {
    return categoryDao.getCategories()
      .flatMapObservable { list -> Observable.fromIterable(list) }
      .map { categoryDbEntity -> categoryTransformer.toModel(categoryDbEntity) }
      .toList()
  }
}