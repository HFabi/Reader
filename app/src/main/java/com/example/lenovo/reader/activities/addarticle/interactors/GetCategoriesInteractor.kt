package com.example.lenovo.reader.activities.addarticle.interactors

import com.example.model.models.Category
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
interface GetCategoriesInteractor {

  fun execute(): Single<List<Category>>

}