package com.example.lenovo.reader.fragments.articlelist

import com.example.lenovo.reader.fragments.articlelist.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.fragments.articlelist.interactors.GetExcerptArticlesInteractor
import com.example.lenovo.reader.fragments.base.BasePresenterImpl
import com.example.model.bind
import com.example.model.models.Category
import com.example.model.schedule
import javax.inject.Inject

class ArticleListPresenterImpl @Inject constructor(): BasePresenterImpl(), ArticleListPresenter {

  @Inject
  lateinit var view: ArticleListView

  @Inject
  lateinit var getExcerptArticlesInteractor: GetExcerptArticlesInteractor
  @Inject
  lateinit var getCategoriesInteractor: GetCategoriesInteractor

  var currentPage = 0


  override fun onResume() {
    super<BasePresenterImpl>.onResume()

    initializeView()
  }

  fun initializeView() {
//    getExcerptArticlesInteractor.execute(currentPage, mutableListOf<Int>())
//      .bind(compositeDisposable)
//      .schedule()
//      .subscribe({articles -> view.replaceExcerptArticles(articles)})
//
    getCategoriesInteractor.execute()
      .bind(compositeDisposable)
      .schedule()
      .subscribe({categories -> view.updateCategories(categories)})
  }

//  fun loadNextExcerptArticlesPage() {
//    getExcerptArticlesInteractor.execute(currentPage)
//      .bind(compositeDisposable)
//      .schedule()
//      .subscribe({articles -> view.appendExcerptArticles(articles)})
//  }

  override fun loadExcerptArticles(categoryIds: List<Long>) {
    getExcerptArticlesInteractor.execute(currentPage, categoryIds)
      .bind(compositeDisposable)
      .schedule()
      .subscribe({articles -> view.replaceExcerptArticles(articles)})
  }

}