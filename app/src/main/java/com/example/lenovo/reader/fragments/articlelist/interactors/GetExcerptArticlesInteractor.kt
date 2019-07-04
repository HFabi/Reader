package com.example.lenovo.reader.fragments.articlelist.interactors

import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-06-13
 */
interface GetExcerptArticlesInteractor {

  fun execute(page: Int = 0, categoryIds: List<Long>): Single<List<ExcerptArticle>>

}