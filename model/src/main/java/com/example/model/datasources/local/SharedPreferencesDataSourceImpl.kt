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

  override fun setArticleFontSizeIndex(value: Int): Completable {
    return Completable.fromCallable {
      with(sharedPreferences.edit()) {
        putInt(ARTICLE_FONT_SIZE, value)
        apply()
      }
    }
  }

  override fun getArticleFontSizeIndex(): Single<Int> {
    return Single.fromCallable {
      sharedPreferences.getInt(ARTICLE_FONT_SIZE, 0)
    }
  }

}