package com.example.lenovo.reader.fragments.addarticle.interactors

import io.reactivex.Completable

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
interface AddArticleInteractor {

  fun execute(url: String): Completable
}