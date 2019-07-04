package com.example.model.transformers

import com.example.model.entities.db.ArticleCategoryDbEntity
import com.example.model.models.Category
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-06-28
 */
class ArticleCategoryTransformerImpl @Inject constructor(): ArticleCategoryTransformer {

  override fun toArticleCategoryDbEntity(categories: List<Category>, articleId: Long): List<ArticleCategoryDbEntity> {
    return categories.map { category -> ArticleCategoryDbEntity(articleId, category.id) }
  }

}