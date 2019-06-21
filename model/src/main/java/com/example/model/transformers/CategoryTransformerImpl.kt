package com.example.model.transformers

import com.example.model.entities.db.CategoryDbEntity
import com.example.model.models.Category
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
class CategoryTransformerImpl @Inject constructor() : CategoryTransformer {

  override fun toModel(categoryDbEntity: CategoryDbEntity): Category {
    return Category(
      categoryDbEntity.id,
      categoryDbEntity.name
    )
  }

  override fun toDbEntity(category: Category): CategoryDbEntity {
    return CategoryDbEntity(
      category.id,
      category.name
    )
  }
}