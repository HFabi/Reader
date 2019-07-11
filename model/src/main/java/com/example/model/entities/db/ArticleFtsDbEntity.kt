package com.example.model.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

/**
 * @author appcom interactive GmbH on 2019-07-11
 */
@Fts4(contentEntity = ArticleDbEntity::class)
@Entity(tableName = "articles_fts")
class ArticleFtsDbEntity(
//  @ColumnInfo(name = "primary_kanji")
  var title: String,
//  @ColumnInfo(name = "primary_kanji")
  var author: String
)


