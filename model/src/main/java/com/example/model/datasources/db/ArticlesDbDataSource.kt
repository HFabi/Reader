package com.example.model.datasources.db

import com.example.model.models.Article
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import com.example.model.models.LastAddedArticle
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
interface ArticlesDbDataSource {

  fun getArticleById(id: Long): Single<Article>

  fun getLastAddedArticles(): Single<List<LastAddedArticle>>

  fun getExcerptArticles(page: Int): Single<List<ExcerptArticle>>

  fun getExcerptArticles(page: Int, categoryIds: List<Long>): Single<List<ExcerptArticle>>

  fun getExcerptArticles(page: Int, searchString: String): Single<List<ExcerptArticle>>

  fun getCategories(): Single<List<Category>>

  fun getCategoriesForArticle(articleId: Long): Single<List<Category>>

  fun addArticle(article: Article): Completable

  fun addArticle(article: Article, categories: List<Category>?): Completable

  fun deleteArticle(articleId: Long): Single<Boolean>
}