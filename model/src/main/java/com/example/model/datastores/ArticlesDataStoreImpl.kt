package com.example.model.datastores

import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle
import io.reactivex.Single
import java.util.Date
import javax.inject.Inject

class ArticlesDataStoreImpl @Inject constructor() : ArticlesDataStore {
  override fun getFavoriteArticles(): Single<List<FavoriteArticle>> {
    return Single.create { emitter ->
      emitter.onSuccess(
          mutableListOf(
              FavoriteArticle(1, "Lorem Ipsum", "Dies ist der Subtitle", ""),
              FavoriteArticle(2, "Artiekl 2", "Subtitle", ""),
              FavoriteArticle(3, "My favorite", "Dies Subtitle", ""),
              FavoriteArticle(4, "Test", "Dies ist der Subtitle", ""),
              FavoriteArticle(5, "News", "Dies ist der Subtitle", "")
          )
      )
    }
  }

  override fun getCategories() {
    TODO(
        "not implemented"
    ) //To change body of created functions use File | Settings | File Templates.
  }

  override fun getArticles(count: Int) {
    TODO(
        "not implemented"
    ) //To change body of created functions use File | Settings | File Templates.
  }

  override fun getArticle(id: Int) {
    TODO(
        "not implemented"
    ) //To change body of created functions use File | Settings | File Templates.
  }

  override fun getLastAddedArticles(count: Int): Single<List<LastAddedArticle>> {
    return Single.create { emitter ->
      emitter.onSuccess(
          listOf(
              LastAddedArticle(
                  1, "Building Awesome CMS — Postlight — Digital product studio", Date(), ""
              ),
              LastAddedArticle(2, "Lorem ipsum dolor sit amet", Date(), ""),
              LastAddedArticle(3, "Stet clita kasd", Date(), "")
          )
      )
    }
  }

}