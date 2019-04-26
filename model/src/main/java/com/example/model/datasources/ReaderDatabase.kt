package com.example.model.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.model.entities.ArticleCategoryJoin
import com.example.model.entities.ArticleEntity
import com.example.model.entities.CategoryEntity

/**
 * @author appcom interactive GmbH on 04.04.19
 */
@Database(entities = arrayOf(ArticleEntity::class, CategoryEntity::class, ArticleCategoryJoin::class), version = 1)
@TypeConverters(Converters::class)
abstract class ReaderDatabase : RoomDatabase() {

  abstract fun articleDao(): ArticleDao

  abstract fun articleCategoryDao(): ArticleCategoryDao

  abstract fun categoryDao(): CategoryDao

}