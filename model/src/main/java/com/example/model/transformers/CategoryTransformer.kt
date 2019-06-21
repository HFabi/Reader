package com.example.model.transformers

import com.example.model.entities.db.CategoryDbEntity
import com.example.model.models.Category

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
interface CategoryTransformer {

  fun toModel(categoryDbEntity: CategoryDbEntity): Category

  fun toDbEntity(category: Category): CategoryDbEntity

}