package com.example.lenovo.reader.activities.addarticle

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.activities.base.BasePresenter
import com.example.model.models.Category
import kotlinx.android.synthetic.main.activity_add_article.add_article_toolbar
import kotlinx.android.synthetic.main.activity_add_article.add_article_url_textinputedittext
import kotlinx.android.synthetic.main.activity_add_article.add_category_categorieschipgroup

import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-25
 */
class AddArticleActivity : BaseActivity(R.layout.activity_add_article), AddArticleView {

  @Inject
  lateinit var presenter: AddArticlePresenter

  override fun providePresenter(): BasePresenter = presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setUpToolbar(add_article_toolbar, R.drawable.ic_close_black_24dp, R.string.title_add_article)
  }

  override fun onResume() {
    super.onResume()
    add_article_url_textinputedittext.requestFocus()
    showKeyboard(add_article_url_textinputedittext)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.menu_add_article, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> presenter.navigateBack()
      R.id.action_add_article_submit -> {
        presenter.onSubmitClicked(add_article_url_textinputedittext.text.toString())
      }
    }
    return super.onOptionsItemSelected(item)
  }

  fun setUpToolbar(
    toolbar: Toolbar?,
    upIndicatorRes: Int,
    titleRes: Int,
    hasOptionMenu: Boolean = true
  ) {
    setSupportActionBar(toolbar)
    supportActionBar?.apply {
      title = getString(titleRes)
      setDisplayHomeAsUpEnabled(true)
      setDisplayShowHomeEnabled(true)
      setHomeAsUpIndicator(upIndicatorRes)
    }
//    setHasOptionsMenu(hasOptionMenu)
  }

  override fun getSelectedCategoryIdentifier(): List<Category>? {
    return add_category_categorieschipgroup.selectedCategoryIdentifier
  }

  override fun setCategories(categories: List<Category>) {
    add_category_categorieschipgroup.setCategories(categories)
  }
}