package com.example.model.entities.web

import java.util.Date

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
data class ArticleWebEntity(
  var domain: String,
  var url: String,
  var title: String,
  var author: String = "",
  var datePublished: Date,
  var leadImageUrl: String,
  var excerpt: String,
  var content: String,
  var nextPageUrl: String
)