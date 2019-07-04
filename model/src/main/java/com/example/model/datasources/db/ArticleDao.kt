package com.example.model.datasources.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.entities.db.ArticleDbEntity
import com.example.model.entities.db.LastAddedArticleDbEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 04.04.19
 */
@Dao
interface ArticleDao {

  @Query("SELECT * FROM articles WHERE id = (:id)")
  fun getArticleById(id: Long): Single<ArticleDbEntity>

  @Query("SELECT id, title, addedAt, leadImagePath FROM articles ORDER BY addedAt DESC LIMIT 8")
  fun getLastAddedArticles(): Single<List<LastAddedArticleDbEntity>>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addArticle(articleDb: ArticleDbEntity): Completable


  // @Query("SELECT * FROM articles WHERE isFavorite = 1 ORDER BY addedAt LIMIT 8")
  // fun getFavoriteArticles(): Single<List<ArticleDbEntity>>
  // LIMIT <skip>, <count>
  // LIMIT <count> OFFSET <skip>
}

