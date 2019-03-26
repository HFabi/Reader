package com.example.lenovo.reader.fragments.dashboard.interactors

import com.example.model.models.FavoriteArticle
import io.reactivex.Single

interface GetFavoriteArticlesInteractor {
  fun execute(): Single<List<FavoriteArticle>>
}