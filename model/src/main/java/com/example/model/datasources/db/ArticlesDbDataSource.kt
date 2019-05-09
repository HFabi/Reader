package com.example.model.datasources.db

import com.example.model.models.Article
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
interface ArticlesDbDataSource {

  fun getArticleById(id: Int): Single<Article>

  fun getArticles(count: Int, skip: Int): Single<List<Article>>

  fun getLastAddedArticles(): Single<List<LastAddedArticle>>

  fun getFavoriteArticles(): Single<List<FavoriteArticle>>

  fun addArticle(article: Article): Completable

}