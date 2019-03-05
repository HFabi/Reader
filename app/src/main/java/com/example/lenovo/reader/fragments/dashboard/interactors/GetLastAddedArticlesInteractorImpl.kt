package com.example.lenovo.reader.fragments.dashboard.interactors

import com.example.model.models.LastAddedArticle
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class GetLastAddedArticlesInteractorImpl @Inject constructor() : GetLastAddedArticlesInteractor {

    override fun execute(): Single<List<LastAddedArticle>> {
        return Single.create{
            emitter ->  emitter.onSuccess(mutableListOf(
            LastAddedArticle(1,"Titel", Date(), ""),
            LastAddedArticle(2,"Text", Date(), ""),
            LastAddedArticle(3,"Artikel 3", Date(), "")
        ))
        }
    }
}