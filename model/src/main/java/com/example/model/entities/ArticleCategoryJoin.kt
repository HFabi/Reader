package com.example.model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.model.models.Article
import com.example.model.models.Category

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Entity(
  tableName = "article_category",
  primaryKeys = ["articleId", "categoryId"],
  foreignKeys = [
    ForeignKey(entity = ArticleEntity::class, parentColumns = ["id"], childColumns = ["articleId"]),
    ForeignKey(entity = CategoryEntity::class, parentColumns = ["id"], childColumns = ["categoryId"])
  ]
)
data class ArticleCategoryJoin(
  var articleId: Int,
  var categoryId: Int
)