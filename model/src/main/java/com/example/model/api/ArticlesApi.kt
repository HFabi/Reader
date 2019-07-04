package com.example.model.api

import com.example.model.entities.web.ArticleWebEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {

  @GET("/parse")
  fun getContentFromUrl(@Query("url") url: String): Single<ArticleWebEntity>

}