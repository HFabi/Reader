package com.example.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * @author appcom interactive GmbH on 04.04.19
 */
@Entity(
  tableName = "articles"
)
data class ArticleEntity(
  @PrimaryKey(autoGenerate = false) var id: Int,
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
)