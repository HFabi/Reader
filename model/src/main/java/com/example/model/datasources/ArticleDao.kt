package com.example.model.datasources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.entities.ArticleEntity
import com.example.model.models.Article
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 04.04.19
 */
@Dao
interface ArticleDao {

  @Query("SELECT * FROM articles WHERE id = (:id)")
  fun getArticle(id: Int): Single<ArticleEntity>

  @Query("SELECT * FROM articles ORDER BY addedAt DESC LIMIT 4")
  fun getLastAddedArticles(): Single<List<ArticleEntity>>

  @Query("SELECT * FROM articles WHERE isFavorite = 1 ORDER BY addedAt LIMIT 8")
  fun getFavoriteArticles(): Single<List<ArticleEntity>>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addArticle(article:ArticleEntity): Completable

}