package com.example.model.datastores

import com.example.model.models.LastAddedArticle
import io.reactivex.Single
import java.util.*

interface ArticlesDataStore {

    fun getLastAddedArticles(count: Int): Single<List<LastAddedArticle>>

    fun getFavoriteArticles()

    fun getCategories()

    fun getArticles(count: Int)

    fun getArticle(id: Int)

}