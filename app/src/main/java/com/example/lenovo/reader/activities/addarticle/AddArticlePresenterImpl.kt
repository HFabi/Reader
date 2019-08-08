package com.example.lenovo.reader.activities.addarticle

import android.util.Log
import com.example.lenovo.reader.activities.addarticle.interactors.AddArticleInteractor
import com.example.lenovo.reader.activities.addarticle.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.activities.base.BasePresenter
import com.example.lenovo.reader.activities.base.BasePresenterImpl
import com.example.lenovo.reader.navigation.Router
import com.example.model.bind
import com.example.model.schedule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
class AddArticlePresenterImpl @Inject constructor() : BasePresenterImpl(), AddArticlePresenter {

  @Inject
  lateinit var view: AddArticleView
  @Inject
  lateinit var addArticleInteractor: AddArticleInteractor
  @Inject
  lateinit var getCategoriesInteractor: GetCategoriesInteractor
  @Inject
  lateinit var router: Router

  override fun onResume() {
    getCategoriesInteractor.execute()
      .bind(compositeDisposable)
      .schedule()
      .subscribe(
        { categories -> view.setCategories(categories) },
        {})
  }

  override fun onSubmitClicked(url: String) {
    val categories = view.getSelectedCategoryIdentifier()
    if (urlIsValid(url)) {
      addArticleInteractor.execute(url, categories)
        .bind(compositeDisposable)
        .schedule()
        .subscribe(
          { Log.d("TAGs", "Added in Presenter successfully") }, { error -> error.printStackTrace() }
        )
    }
    navigateToDashboard()
  }

  fun urlIsValid(url: String): Boolean {
    return url.isNotEmpty()
  }

  override fun navigateToDashboard() {
//    router.goToDashboard(view as AddArticleActivity)
    router.goBack()
  }

  override fun navigateBack() {
    router.goBack()
  }

}