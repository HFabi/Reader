package com.example.model.models

import java.util.Date

/**
 * @author appcom interactive GmbH on 2019-06-13
 */
data class ExcerptArticle(
  var id: Int,
  var title: String,
  var addedAt: Date,
  var imagePath: String
)