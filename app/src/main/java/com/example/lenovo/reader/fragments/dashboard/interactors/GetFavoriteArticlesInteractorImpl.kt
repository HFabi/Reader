package com.example.lenovo.reader.fragments.dashboard.interactors

import com.example.model.models.FavoriteArticle
import io.reactivex.Single
import javax.inject.Inject

class GetFavoriteArticlesInteractorImpl @Inject constructor() : GetFavoriteArticlesInteractor {
    override fun execute(): Single<List<FavoriteArticle>> {
        return Single.create{emitter -> emitter.onSuccess(
            mutableListOf(FavoriteArticle(1,"Lorem Ipsum", "Dies ist der Subtitle", ""))
        ) }
    }
}