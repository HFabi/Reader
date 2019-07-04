package com.example.lenovo.reader.fragments.addarticle

import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.model.models.Category

interface AddArticlePresenter : BasePresenter {
  fun onSubmitClicked(url: String)

}

interface AddArticleView {
  fun getSelectedCategoryIdentifier(): List<Category>?
  fun setCategories(categories: List<Category>)
}