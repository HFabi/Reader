package com.example.model.datastores

import com.example.model.models.Article
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import com.example.model.models.LastAddedArticle
import io.reactivex.Completable
import io.reactivex.Single

interface ArticlesDataStore {

  fun getArticle(id: Int): Single<Article>

  fun getLastAddedArticles(count: Int): Single<List<LastAddedArticle>>

  fun getExcerptArticles(page: Int = 0, categories: List<Category> = mutableListOf()): Single<List<ExcerptArticle>>

  fun getCategories(): Single<List<Category>>

  fun addArticle(url: String, category: List<String> = mutableListOf("")): Completable

  fun setArticleFontSizeIndex(value: Int): Completable

  fun getArticleFontSizeIndex(): Single<Int>

}