package com.example.lenovo.reader.fragments.addarticle.interactors

import com.example.model.models.Category
import io.reactivex.Completable

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
interface AddArticleInteractor {

  fun execute(url: String, categories: List<Category>?): Completable
}