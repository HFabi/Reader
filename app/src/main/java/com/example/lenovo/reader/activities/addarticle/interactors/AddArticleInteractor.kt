package com.example.lenovo.reader.activities.addarticle.interactors

import com.example.model.models.Category
import io.reactivex.Completable

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
interface AddArticleInteractor {

  fun execute(url: String, categories: List<Category>?): Completable
}