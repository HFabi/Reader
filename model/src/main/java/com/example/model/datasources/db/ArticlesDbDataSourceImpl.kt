package com.example.model.datasources.db

import com.example.model.controllers.StorageController
import com.example.model.models.Article
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import com.example.model.models.LastAddedArticle
import com.example.model.transformers.ArticleCategoryTransformer
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
  @Inject
  lateinit var articleCategoryTransformer: ArticleCategoryTransformer
  @Inject
  lateinit var storageController: StorageController

  private val articlesPerPage: Int = 10

  override fun getArticleById(id: Long): Single<Article> {
    return articleDao.getArticleById(id)
      .map { articleDbEntity -> articleTransformer.toModel(articleDbEntity) }
  }

  override fun getLastAddedArticles(): Single<List<LastAddedArticle>> {
    return articleDao.getLastAddedArticles()
      .flatMapObservable { list -> Observable.fromIterable(list) }
      .map { articleDbEntity -> articleTransformer.toModel(articleDbEntity) }
      .toList()
  }

  override fun addArticle(article: Article): Completable {
    return articleDao.addArticle(articleTransformer.toDbEntity(article))
  }

  override fun addArticle(article: Article, categories: List<Category>?): Completable {
    return articleDao.addArticle(articleTransformer.toDbEntity(article))
      .andThen(categories?.let {
        categoryDao.addAllCategories(categoryTransformer.toDbEntity(categories))
          .andThen(
            articleCategoryDao.addAllArticleCategories(
              articleCategoryTransformer.toArticleCategoryDbEntity(
                categories,
                article.id
              )
            )
          )
      } ?: Completable.complete())
  }

  override fun getExcerptArticles(page: Int): Single<List<ExcerptArticle>> {
    val skip = articlesPerPage * page
    return articleCategoryDao.getExcerptArticles(articlesPerPage, skip)
      .flatMapObservable { list -> Observable.fromIterable(list) }
      .map { articleDbEntity -> articleTransformer.toModel(articleDbEntity) }
      .toList()
  }

  override fun getExcerptArticles(
    page: Int,
    categoryIds: List<Long>
  ): Single<List<ExcerptArticle>> {
    val skip = articlesPerPage * page
    return articleCategoryDao.getExcerptArticles(articlesPerPage, skip, categoryIds)
      .flatMapObservable { list -> Observable.fromIterable(list) }
      .map { articleDbEntity -> articleTransformer.toModel(articleDbEntity) }
      .toList()
  }

  override fun getExcerptArticles(page: Int, searchString: String): Single<List<ExcerptArticle>> {
    val skip = articlesPerPage * page
    return articleDao.getExcerptArticles(articlesPerPage, skip, "$searchString*")
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

  override fun getCategoriesForArticle(articleId: Long): Single<List<Category>> {
    return articleCategoryDao.getCategoriesForArticle(articleId)
      .map(categoryTransformer::toModel)
  }

  override fun deleteArticle(articleId: Long): Single<Boolean> {
    return getArticleById(articleId)
      .flatMapCompletable { article -> storageController.deleteAlbum(article.localPath) }
      .andThen(articleDao.deleteArticle(articleId))
      .map { changedRows -> changedRows > 0 }
  }
}