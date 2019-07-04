package com.example.model.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * @author appcom interactive GmbH on 04.04.19
 */
@Entity(
  tableName = "articles"
)
data class ArticleDbEntity(
  @PrimaryKey(autoGenerate = true) var id: Long,
  var domain: String,
  var url: String,
  var title: String,
  var author: String = "",
  var datePublished: Date?,
  var leadImagePath: String,
  var excerpt: String,
  var content: String,
  var localPath: String,
  var nextPageUrl: String,
  var addedAt: Date
)