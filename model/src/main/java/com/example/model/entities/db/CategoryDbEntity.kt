package com.example.model.entities.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Entity(
  tableName = "categories"
)
data class CategoryDbEntity(
  @PrimaryKey(autoGenerate = false) var id: Long,
  var name: String
)