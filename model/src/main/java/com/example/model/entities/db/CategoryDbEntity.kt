package com.example.model.entities.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Entity(
  tableName = "category"
)
data class CategoryDbEntity(
  @PrimaryKey(autoGenerate = false) var id: Int,
  var name: String
)