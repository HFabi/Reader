package com.example.model.datasources.local

import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
interface SharedPreferencesDataSource {

  fun setFontSizeIndex(value: Int): Completable

  fun getFontSizeIndex(): Single<Int>

  fun setDayNightMode(value: Int): Completable

  fun getDayNightMode(): Single<Int>

}