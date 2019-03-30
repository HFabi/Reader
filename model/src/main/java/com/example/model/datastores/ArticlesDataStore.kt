package com.example.model.datastores

import com.example.model.models.Article
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle
import io.reactivex.Single

interface ArticlesDataStore {

  fun getLastAddedArticles(count: Int): Single<List<LastAddedArticle>>

  fun getFavoriteArticles(): Single<List<FavoriteArticle>>

  fun getCategories()

  fun getArticles(count: Int)

  fun getArticle(id: Int): Single<Article>

}