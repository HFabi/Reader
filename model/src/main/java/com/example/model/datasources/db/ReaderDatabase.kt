package com.example.model.datasources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.model.entities.db.ArticleCategoryDbEntity
import com.example.model.entities.db.ArticleDbEntity
import com.example.model.entities.db.CategoryDbEntity

/**
 * @author appcom interactive GmbH on 04.04.19
 */
@Database(entities = arrayOf(ArticleDbEntity::class, CategoryDbEntity::class, ArticleCategoryDbEntity::class), version = 1)
@TypeConverters(Converters::class)
abstract class ReaderDatabase : RoomDatabase() {

  abstract fun articleDao(): ArticleDao

  abstract fun articleCategoryDao(): ArticleCategoryDao

  abstract fun categoryDao(): CategoryDao

}