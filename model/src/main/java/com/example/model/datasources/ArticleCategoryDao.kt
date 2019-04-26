package com.example.model.datasources

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.model.entities.ArticleCategoryJoin
import io.reactivex.Completable

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Dao
interface ArticleCategoryDao {

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addArticleCategory(articleCategoryJoin: ArticleCategoryJoin): Completable

  @Delete
  fun remove(articleCategoryJoin: ArticleCategoryJoin): Completable

}