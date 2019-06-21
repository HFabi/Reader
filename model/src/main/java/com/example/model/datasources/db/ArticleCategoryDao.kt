package com.example.model.datasources.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.entities.db.ArticleCategoryDbEntity
import io.reactivex.Completable

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Dao
interface ArticleCategoryDao {

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addArticleCategory(articleCategoryDbEntity: ArticleCategoryDbEntity): Completable

  @Delete
  fun remove(articleCategoryDbEntity: ArticleCategoryDbEntity): Completable

  @Query(
    "SELECT id, title, addedAt, leadImagePath " +
        "FROM articles INNER JOIN articles_categories " +
        "ON articles.category=user_repo_join.userId " +
        "WHERE user_repo_join.repoId=:repoId"
  )
  fun getExcerptArticle()

}