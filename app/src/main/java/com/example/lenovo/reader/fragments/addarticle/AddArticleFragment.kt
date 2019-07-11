package com.example.lenovo.reader.fragments.addarticle

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.navigation.Router
import com.example.model.models.Category
import kotlinx.android.synthetic.main.fragment_add_article.add_article_toolbar
import kotlinx.android.synthetic.main.fragment_add_article.add_article_url_textinputedittext
import kotlinx.android.synthetic.main.fragment_add_article.add_category_categorieschipgroup
import timber.log.Timber
import javax.inject.Inject

@Layout(R.layout.fragment_add_article)
class AddArticleFragment : BaseFragment(), AddArticleView {

  @Inject
  lateinit var presenter: AddArticlePresenter
  @Inject
  lateinit var router: Router

  override fun providePresenter(): BasePresenter = presenter

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(add_article_toolbar, R.drawable.ic_close_black_24dp, R.string.title_add_article)
  }

  override fun onResume() {
    super.onResume()
    (activity as MainActivity).setBottomNavigationEnabled(false)
    setUpChips()
    add_article_url_textinputedittext.requestFocus()
    showKeyboard(add_article_url_textinputedittext)
  }

  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    inflater.inflate(R.menu.menu_add_article, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> router.goBack()
      R.id.action_add_article_submit -> {
        //Todo: validate Input
        presenter.onSubmitClicked(add_article_url_textinputedittext.text.toString())
        router.goToDashboard(this)
      }
    }
    return super.onOptionsItemSelected(item)
  }

  fun setUpChips() {

  }

  override fun getSelectedCategoryIdentifier(): List<Category>? {
    Timber.d("SELECTED Category IDentifier legnth" + add_category_categorieschipgroup.selectedCategoryIdentifier.size)
    return add_category_categorieschipgroup.selectedCategoryIdentifier
  }

  override fun setCategories(categories: List<Category>) {
    add_category_categorieschipgroup.setCategories(categories)
  }

}