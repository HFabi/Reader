package com.example.lenovo.reader.fragments.search.interatctors

import com.example.model.models.ExcerptArticle
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-07-04
 */
interface SearchExcerptArticleInteractor {
  fun execute(page: Int, searchString: String): Single<List<ExcerptArticle>>
}