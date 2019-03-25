package com.example.model.datastores

import com.example.model.models.LastAddedArticle
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class ArticlesDataStoreImpl @Inject constructor(): ArticlesDataStore{
    override fun getFavoriteArticles() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCategories() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getArticles(count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getArticle(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLastAddedArticles(count: Int): Single<List<LastAddedArticle>> {
        return Single.create { emitter ->
            emitter.onSuccess(
                listOf(
                    LastAddedArticle(1, "Building Awesome CMS — Postlight — Digital product studio", Date(), ""),
                    LastAddedArticle(2, "Lorem ipsum dolor sit amet", Date(), ""),
                    LastAddedArticle(3, "Stet clita kasd", Date(), "")
                )
            )
        }
    }



}