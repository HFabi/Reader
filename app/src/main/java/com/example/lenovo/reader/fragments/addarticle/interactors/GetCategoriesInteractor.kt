package com.example.lenovo.reader.fragments.addarticle.interactors

import com.example.model.models.Category
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-07-04
 */
interface GetCategoriesInteractor {

  fun execute(): Single<List<Category>>

}