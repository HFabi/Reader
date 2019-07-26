package com.example.model.datastores

import com.example.model.datasources.local.SharedPreferencesDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
class UserPreferencesDataStoreImpl @Inject constructor() : UserPreferencesDataStore {

  @Inject
  lateinit var sharedPreferencesDataSource: SharedPreferencesDataSource

  override fun setFontSizeIndex(value: Int): Completable {
    return sharedPreferencesDataSource.setFontSizeIndex(value)
  }

  override fun getFontSizeIndex(): Single<Int> {
    return sharedPreferencesDataSource.getFontSizeIndex()
  }

  override fun setDayNightMode(value: Int): Completable {
    return sharedPreferencesDataSource.setDayNightMode(value)
  }

  override fun getDayNightMode(): Single<Int> {
    return sharedPreferencesDataSource.getDayNightMode()
  }

}