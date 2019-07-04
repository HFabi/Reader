package com.example.model.entities.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Entity(
  tableName = "articles_categories",
  primaryKeys = ["articleId", "categoryId"],
  foreignKeys = [
    ForeignKey(entity = ArticleDbEntity::class, parentColumns = ["id"], childColumns = ["articleId"]),
    ForeignKey(entity = CategoryDbEntity::class, parentColumns = ["id"], childColumns = ["categoryId"])
  ],
  indices = [Index("categoryId")]
)
data class ArticleCategoryDbEntity(
  var articleId: Long,
  var categoryId: Long
)