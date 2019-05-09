package com.example.model.datasources.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.entities.db.ArticleDbEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 04.04.19
 */
@Dao
interface ArticleDao {

  @Query("SELECT * FROM articles WHERE id = (:id)")
  fun getArticleById(id: Int): Single<ArticleDbEntity>

  @Query("SELECT * FROM articles ORDER BY addedAt DESC LIMIT (:count) OFFSET (:skip)")
  fun getArticles(count: Int, skip: Int): Single<List<ArticleDbEntity>>

  @Query("SELECT * FROM articles ORDER BY addedAt DESC LIMIT 4")
  fun getLastAddedArticles(): Single<List<ArticleDbEntity>>

  @Query("SELECT * FROM articles WHERE isFavorite = 1 ORDER BY addedAt LIMIT 8")
  fun getFavoriteArticles(): Single<List<ArticleDbEntity>>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addArticle(articleDb: ArticleDbEntity): Completable



  // LIMIT <skip>, <count>
  // LIMIT <count> OFFSET <skip>
}

