package com.example.lenovo.reader.fragments.article.interactors

import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-07-18
 */
interface DeleteArticleInteractor {

  fun execute(articleId: Long): Single<Boolean>

}