package com.example.lenovo.reader.fragments.articlelist.interactors

import com.example.model.models.Category
import io.reactivex.Single

interface GetCategoriesInteractor {
  fun execute(): Single<List<Category>>
}