package com.example.lenovo.reader.activities.addarticle

import android.content.Intent
import android.util.Log
import com.example.lenovo.reader.activities.addarticle.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.activities.base.BasePresenterImpl
import com.example.lenovo.reader.navigation.Router
import com.example.lenovo.reader.services.DownloadArticleService
import com.example.model.bind
import com.example.model.models.Category
import com.example.model.schedule
import timber.log.Timber
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
class AddArticlePresenterImpl @Inject constructor() : BasePresenterImpl(), AddArticlePresenter {

  @Inject
  lateinit var view: AddArticleView
  @Inject
  lateinit var getCategoriesInteractor: GetCategoriesInteractor
  @Inject
  lateinit var router: Router
  @Inject
  lateinit var addArticleActivity: AddArticleActivity

  override fun onResume() {
    getCategoriesInteractor.execute()
      .bind(compositeDisposable)
      .schedule()
      .subscribe(
        { categories -> view.setCategories(categories) },
        {error -> Timber.d(error)})
  }

  override fun onSubmitClicked(url: String) {
    startService(url, view.getSelectedCategoryIdentifier())
    navigateToDashboard()
  }

  private fun startService(url: String, categories: List<Category>?) {
    addArticleActivity.startService(Intent(addArticleActivity, DownloadArticleService::class.java).apply {
      putExtra("url", url)
      categories?.let {
        putParcelableArrayListExtra("categories", ArrayList<Category>())
      }
    })
  }

  override fun navigateToDashboard() {
//    router.goToDashboard(view as AddArticleActivity)
    router.goBack()
  }

  override fun navigateBack() {
    router.goBack()
  }

}

//      addArticleInteractor.execute(url, categories)
//        .bind(compositeDisposable)
//        .schedule()
//        .subscribe(
//          { Log.d("TAGs", "Added in Presenter successfully") }, { error -> error.printStackTrace() }
//        )