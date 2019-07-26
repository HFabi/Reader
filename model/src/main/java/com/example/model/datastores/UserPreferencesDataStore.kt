package com.example.model.datastores

import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
interface UserPreferencesDataStore {

  fun setFontSizeIndex(value: Int): Completable

  fun getFontSizeIndex(): Single<Int>

  fun setDayNightMode(value: Int): Completable

  fun getDayNightMode(): Single<Int>

}