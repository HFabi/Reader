package com.example.lenovo.reader.fragments.articlelist.interactors

import android.util.Log
import com.example.model.datastores.ArticlesDataStore
import com.example.model.models.Category
import io.reactivex.Single
import javax.inject.Inject

class GetCategoriesInteractorImpl @Inject constructor() : GetCategoriesInteractor {

  @Inject
  lateinit var articlesDataStore: ArticlesDataStore

  override fun execute(): Single<List<Category>> {
//    return Single.create { emitter ->
//      emitter.onSuccess(
//          mutableListOf(
//              Category(1, "Design"),
//              Category(2, "Programming"),
//              Category(3, "Art"),
//              Category(4, "Learning"),
//              Category(5, "Test")
//          )
//      )
//    }
    return articlesDataStore.getCategories().map { a -> Log.d("CATEG"," " + a.size +" ")
      a
    }
  }
}