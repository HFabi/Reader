package com.example.model.datasources.db

import com.example.model.models.Article
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle
import com.example.model.transformers.ArticleTransformer
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

  override fun getArticleById(id: Int): Single<Article> {
    return articleDao.getArticleById(id)
      .map { articleDbEntity -> articleTransformer.toModel(articleDbEntity) }
  }

  override fun getArticles(count: Int, skip: Int): Single<List<Article>> {
    return articleDao.getArticles(count, skip)
      .flatMapObservable { list -> Observable.fromIterable(list) }
      .map { articleDbEntity -> articleTransformer.toModel(articleDbEntity) }
      .toList()
  }

  override fun getLastAddedArticles(): Single<List<LastAddedArticle>> {
    return articleDao.getLastAddedArticles()
      .flatMapObservable { list -> Observable.fromIterable(list) }
      .map { articleDbEntity -> articleTransformer.toLastAddedArticle(articleDbEntity) }
      .toList()
  }

  override fun getFavoriteArticles(): Single<List<FavoriteArticle>> {
    return articleDao.getFavoriteArticles()
      .flatMapObservable { list -> Observable.fromIterable(list) }
      .map { articleDbEntity -> articleTransformer.toFavoriteArticle(articleDbEntity) }
      .toList()
  }

  override fun addArticle(article: Article): Completable {
    return articleDao.addArticle(articleTransformer.toDbEntity(article))
  }
}