package com.example.model.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.model.entities.ArticleEntity
import com.example.model.models.Article

/**
 * @author appcom interactive GmbH on 04.04.19
 */
@Database(entities = arrayOf(ArticleEntity::class), version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

  abstract fun ArticleDao(): ArticleDao

}