package com.example.model.entities.db

import java.util.Date

/**
 * @author appcom interactive GmbH on 2019-06-21
 */
data class LastAddedArticleDbEntity(
  var id: Long,
  var title: String,
  var addedAt: Date,
  var leadImagePath: String
)