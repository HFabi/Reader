package com.example.model.datastores

import com.example.model.models.Article
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import com.example.model.models.LastAddedArticle
import io.reactivex.Completable
import io.reactivex.Single

interface ArticlesDataStore {

  fun getArticle(id: Long): Single<Article>

  fun getLastAddedArticles(count: Int): Single<List<LastAddedArticle>>

  fun getExcerptArticles(page: Int = 0, categoryIds: List<Long>): Single<List<ExcerptArticle>>

  fun getExcerptArticles(page: Int = 0): Single<List<ExcerptArticle>>

  fun getExcerptArticles(page: Int = 0, serachString: String): Single<List<ExcerptArticle>>

  fun getCategories(): Single<List<Category>>

  fun getCategoriesForArticle(articleId: Long): Single<List<Category>>

  fun addArticle(url: String, category: List<Category>?): Completable

  fun setArticleFontSizeIndex(value: Int): Completable

  fun getArticleFontSizeIndex(): Single<Int>

  fun deleteArticle(articleId: Long): Single<Boolean>

}