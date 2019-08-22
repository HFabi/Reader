package com.example.lenovo.reader.activities.addarticle

import com.example.lenovo.reader.activities.base.BasePresenter
import com.example.model.models.Category

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
interface AddArticlePresenter : BasePresenter {
  fun onSubmitClicked(url: String)
  fun navigateToDashboard()
  fun navigateBack()
}

interface AddArticleView {
  fun getSelectedCategoryIdentifier(): List<Category>?
  fun setCategories(categories: List<Category>)
}