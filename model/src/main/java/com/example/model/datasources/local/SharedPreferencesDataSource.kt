package com.example.model.datasources.local

import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
interface SharedPreferencesDataSource {

  fun setArticleFontSizeIndex(value: Int): Completable

  fun getArticleFontSizeIndex(): Single<Int>

}