package com.example.lenovo.reader.fragments.article.interactors

import com.example.model.models.Category
import io.reactivex.Single

interface GetCategoriesInteractor {
  fun execute(articleId: Long): Single<List<Category>>
}