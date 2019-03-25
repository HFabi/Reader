package com.example.model.models

import java.util.Date

class Article(
  var id: Int,
  var author: String = "",
  var title: String,
  var content: String,
  var domain: String,
  var url: String,
  var excerpt: String,
  var leadImageUrl: String,
  var nextPageUrl: String,
  var addedAt: Date,
  var isRead: Boolean,
  var isFavorite: Boolean
) {

}