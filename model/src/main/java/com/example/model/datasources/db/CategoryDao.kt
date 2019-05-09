package com.example.model.datasources.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.model.entities.db.CategoryDbEntity
import io.reactivex.Completable

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Dao
interface CategoryDao {

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addCategory(categoryDb: CategoryDbEntity): Completable

  @Delete
  fun remove(categoryDb: CategoryDbEntity): Completable

}