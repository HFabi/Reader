package com.example.lenovo.reader.fragments.article.interactors

import com.example.model.models.Article
import io.reactivex.Single

interface GetArticleInteractor {

  fun execute(id: Int): Single<Article>
}