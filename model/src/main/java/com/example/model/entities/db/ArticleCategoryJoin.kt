package com.example.model.entities.db

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Entity(
  tableName = "article_category",
  primaryKeys = ["articleId", "categoryId"],
  foreignKeys = [
    ForeignKey(entity = ArticleDbEntity::class, parentColumns = ["id"], childColumns = ["articleId"]),
    ForeignKey(entity = CategoryDbEntity::class, parentColumns = ["id"], childColumns = ["categoryId"])
  ]
)
data class ArticleCategoryJoin(
  var articleId: Int,
  var categoryId: Int
)