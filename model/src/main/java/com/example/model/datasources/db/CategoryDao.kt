package com.example.model.datasources.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.entities.db.CategoryDbEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Dao
interface CategoryDao {

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addCategory(categoryDb: CategoryDbEntity): Completable

  @Delete
  fun remove(categoryDb: CategoryDbEntity): Completable

  @Query("SELECT * FROM categories")
  fun getCategories(): Single<List<CategoryDbEntity>>

}