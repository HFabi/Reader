package com.example.model.models

import java.util.Date

data class Article(
  var id: Long,
  var domain: String,
  var url: String,
  var title: String,
  var author: String = "",
  var datePublished: Date,
  var leadImagePath: String = "",
  var excerpt: String,
  var content: String,
  var localPath: String = "",
  var nextPageUrl: String,
  var addedAt: Date
)