package com.example.lenovo.reader.fragments.dashboard.interactors

import com.example.model.models.FavoriteArticle
import io.reactivex.Single
import javax.inject.Inject

class GetFavoriteArticlesInteractorImpl @Inject constructor() : GetFavoriteArticlesInteractor {
    override fun execute(): Single<List<FavoriteArticle>> {
        return Single.create{emitter -> emitter.onSuccess(
            mutableListOf(
                FavoriteArticle(1,"Lorem Ipsum", "Dies ist der Subtitle", ""),
                FavoriteArticle(2,"Artiekl 2", "Subtitle", ""),
                FavoriteArticle(3,"My favorite", "Dies Subtitle", ""),
                FavoriteArticle(4,"Test", "Dies ist der Subtitle", ""),
                FavoriteArticle(5,"News", "Dies ist der Subtitle", "")
            )
        ) }
    }
}