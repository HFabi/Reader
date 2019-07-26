package com.example.model.datasources.local

import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
class SharedPreferencesDataSourceImpl @Inject constructor(val sharedPreferences: SharedPreferences) :
  SharedPreferencesDataSource {

  val ARTICLE_FONT_SIZE = "ARTICLE_FONT_SIZE"
  val DAY_NIGHT_INDEX = "DAY_NIGHT_INDEX"

  override fun setFontSizeIndex(value: Int): Completable {
    return Completable.fromCallable {
      with(sharedPreferences.edit()) {
        putInt(ARTICLE_FONT_SIZE, value)
        apply()
      }
    }
  }

  override fun getFontSizeIndex(): Single<Int> {
    return Single.fromCallable {
      sharedPreferences.getInt(ARTICLE_FONT_SIZE, 0)
    }
  }

  override fun setDayNightMode(value: Int): Completable {
    return Completable.fromCallable {
      with(sharedPreferences.edit()) {
        putInt(DAY_NIGHT_INDEX, value)
        apply()
      }
    }
  }

  override fun getDayNightMode(): Single<Int> {
    return Single.fromCallable {
      sharedPreferences.getInt(DAY_NIGHT_INDEX, 2)
    }
  }

}