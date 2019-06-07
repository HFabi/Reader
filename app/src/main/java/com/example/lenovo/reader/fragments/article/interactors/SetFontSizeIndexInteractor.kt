package com.example.lenovo.reader.fragments.article.interactors

import io.reactivex.Completable

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
interface SetFontSizeIndexInteractor {
  fun execute(value: Int): Completable
}