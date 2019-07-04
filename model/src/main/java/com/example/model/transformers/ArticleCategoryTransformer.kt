package com.example.model.transformers

import com.example.model.entities.db.ArticleCategoryDbEntity
import com.example.model.models.Category

/**
 * @author appcom interactive GmbH on 2019-06-28
 */
interface ArticleCategoryTransformer {
  fun toArticleCategoryDbEntity(categories: List<Category>, articleId: Long): List<ArticleCategoryDbEntity>
}