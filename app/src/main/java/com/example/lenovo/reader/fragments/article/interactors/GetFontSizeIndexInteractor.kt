package com.example.lenovo.reader.fragments.article.interactors

import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
interface GetFontSizeIndexInteractor {

  fun execute(): Single<Int>

}