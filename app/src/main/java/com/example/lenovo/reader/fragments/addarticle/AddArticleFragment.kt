package com.example.lenovo.reader.fragments.addarticle

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.lenovo.reader.R
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.LifecycleObserverPresenter
import com.example.lenovo.reader.navigation.Router
import kotlinx.android.synthetic.main.fragment_add_article.add_article_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_add_article)
class AddArticleFragment : BaseFragment(), AddArticleView {

  @Inject
  lateinit var addArticlePresenter: AddArticlePresenter
  @Inject
  lateinit var router: Router

  override fun providePresenter(): LifecycleObserverPresenter = addArticlePresenter

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(add_article_toolbar, R.drawable.ic_close_black_24dp, R.string.title_add_article)
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
        router.goToDashboard(this)
      }
    }
    return super.onOptionsItemSelected(item)
  }

}