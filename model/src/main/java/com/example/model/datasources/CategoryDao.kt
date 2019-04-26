package com.example.model.datasources

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.model.entities.CategoryEntity
import io.reactivex.Completable

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Dao
interface CategoryDao {

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addCategory(category: CategoryEntity): Completable

  @Delete
  fun remove(category: CategoryEntity): Completable

}