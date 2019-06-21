package com.example.lenovo.reader.fragments.dashboard.interactors

import com.example.model.models.LastAddedArticle
import io.reactivex.Single

interface GetLastAddedArticlesInteractor {
  fun execute(count: Int): Single<List<LastAddedArticle>>
}