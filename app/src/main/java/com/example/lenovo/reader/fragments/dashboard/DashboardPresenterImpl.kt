package com.example.lenovo.reader.fragments.dashboard

import android.util.Log
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.fragments.dashboard.interactors.GetCategoriesInteractor
import com.example.lenovo.reader.fragments.dashboard.interactors.GetFavoriteArticlesInteractor
import com.example.lenovo.reader.fragments.dashboard.interactors.GetLastAddedArticlesInteractor
import com.example.model.models.Category
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle
import com.example.model.rx.bind
import com.example.model.rx.schedule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DashboardPresenterImpl @Inject constructor() : BasePresenter(), DashboardPresenter {

    @Inject
    lateinit var view: DashboardView
    @Inject
    lateinit var getLastAddedArticlesInteractor: GetLastAddedArticlesInteractor
    @Inject
    lateinit var getFavoriteArticlesInteractor: GetFavoriteArticlesInteractor
    @Inject
    lateinit var getCategoriesInteractor: GetCategoriesInteractor

    override fun onCreate() {
        super.onCreate()
        Log.d("LOG", "DashboardPresenter onCreate")
        initializeView()
    }


    fun initializeView() {
        getLastAddedArticlesInteractor.execute()
            .schedule()
            .bind(compositeDisposable)
            .subscribe{
                articles -> view.updateLastAddedArticles(articles)
            }
        getFavoriteArticlesInteractor.execute()
            .schedule()
            .bind(compositeDisposable)
            .subscribe{
                favorites -> view.updateFavoriteArticles(favorites)
            }
        getCategoriesInteractor.execute()
            .schedule()
            .bind(compositeDisposable)
            .subscribe{
                categories -> view.updateCategories(categories)
            }
    }

}
