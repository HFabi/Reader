package com.example.model.controllers

import com.example.model.models.Article
import com.example.model.models.DownloadTask
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-05-03
 */
interface ArticleController {

//  fun parse(article: Article): Single<Pair<Article, List<DownloadTask>>>

  fun processArticle(article: Article): Single<Article>
}