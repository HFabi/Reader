package com.example.lenovo.reader.fragments.addarticle

import android.util.Log
import com.example.lenovo.reader.fragments.addarticle.interactors.AddArticleInteractor
import com.example.lenovo.reader.fragments.addarticle.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.model.bind
import com.example.model.schedule
import javax.inject.Inject

class AddArticlePresenterImpl @Inject constructor() : BasePresenterImpl(), AddArticlePresenter {

  @Inject
  lateinit var addArticleInteractor: AddArticleInteractor

  @Inject
  lateinit var view: AddArticleView
  @Inject
  lateinit var getCategoriesInteractor: GetCategoriesInteractor

  override fun onResume() {
    super<BasePresenterImpl>.onResume()
    getCategoriesInteractor.execute()
      .bind(compositeDisposable)
      .schedule()
      .subscribe(
        {categories -> view.setCategories(categories)},
        {})
  }

  override fun onSubmitClicked(url: String) {
    val categories = view.getSelectedCategoryIdentifier()
    if(urlIsValid(url)) {
      addArticleInteractor.execute(url, categories)
        .bind(compositeDisposable)
        .schedule()
        .subscribe(
          { Log.d("TAGs", "Added in Presenter successfully") }, { error -> error.printStackTrace() }
        )
    }
  }

  fun urlIsValid(url: String):Boolean {
    return url.isNotEmpty()
  }
}