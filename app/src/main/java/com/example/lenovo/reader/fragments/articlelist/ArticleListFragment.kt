package com.example.lenovo.reader.fragments.articlelist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.navigation.Router
import kotlinx.android.synthetic.main.fragment_article_list.article_list_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_article_list)
class ArticleListFragment : BaseFragment(), ArticleListView {

  @Inject
  lateinit var presenter: ArticleListPresenter

  @Inject
  lateinit var router: Router

  override fun providePresenter(): BasePresenter? = presenter

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(article_list_toolbar, false, titleRes = R.string.articles)
  }

  override fun onResume() {
    super.onResume()
    (activity as MainActivity).setBottomNavigationEnabled(true)
    (activity as MainActivity).currentFragment = this
  }

  /**
   * Called only once for creation
   */
  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    inflater.inflate(R.menu.menu_article_list, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_article_list_filter -> {
        Toast.makeText(context, "Filter", Toast.LENGTH_LONG).show()
      }
      R.id.action_article_list_search -> {
//        Toast.makeText(context, "Search", Toast.LENGTH_LONG).show()
        router.goToSearch(this)
      }
    }
    return super.onOptionsItemSelected(item)
  }
}