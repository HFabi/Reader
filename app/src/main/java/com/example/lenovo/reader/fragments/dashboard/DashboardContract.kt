package com.example.lenovo.reader.fragments.dashboard

import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.model.models.Category
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle

interface DashboardPresenter: BasePresenter {


}

interface DashboardView {

    fun updateCategories(categories: List<Category>)

    fun updateFavoriteArticles(favoriteArticles: List<FavoriteArticle>)

    fun updateLastAddedArticles(lastAddedArticles: List<LastAddedArticle>)

}