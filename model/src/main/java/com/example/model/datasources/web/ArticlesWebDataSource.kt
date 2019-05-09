package com.example.model.datasources.web

import com.example.model.entities.web.ArticleWebEntity
import com.example.model.models.Article
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
interface ArticlesWebDataSource {

  fun parseHtmlFromUrl(utl: String): Single<Article>
}