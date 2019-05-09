package com.example.model.entities.web

import java.util.Date

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
data class ArticleWebEntity(
  var id: Int,
  var author: String = "",
  var title: String,
  var content: String,
  var domain: String,
  var url: String,
  var localPath: String = "",
  var excerpt: String,
  var leadImageUrl: String,
  var leadImagePath: String = "",
  var nextPageUrl: String,
  var addedAt: Date
)