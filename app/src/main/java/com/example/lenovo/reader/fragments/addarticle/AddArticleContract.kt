package com.example.lenovo.reader.fragments.addarticle

import com.example.lenovo.reader.fragments.base.BasePresenter

interface AddArticlePresenter : BasePresenter {
  fun onSubmitClicked(url: String)
}

interface AddArticleView {

}