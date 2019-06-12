package com.example.lenovo.reader.fragments.articlelist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.navigation.Router
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.article_list_filter.filter_bottom_sheet
import kotlinx.android.synthetic.main.fragment_article_list.article_list_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_article_list)
class ArticleListFragment : BaseFragment(), ArticleListView {

  @Inject
  lateinit var presenter: ArticleListPresenter

  @Inject
  lateinit var router: Router

  lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

  override fun providePresenter(): BasePresenter? = presenter

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(article_list_toolbar, false, titleRes = R.string.articles)
    setUpFilter()
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
        onToggleFilter()
      }
      R.id.action_article_list_search -> {
        router.goToSearch(this)
      }
    }
    return super.onOptionsItemSelected(item)
  }

  fun onToggleFilter() {
    bottomSheetBehavior.state =
      if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN)
        BottomSheetBehavior.STATE_COLLAPSED else BottomSheetBehavior.STATE_HIDDEN
  }

  fun setUpFilter() {
    bottomSheetBehavior = BottomSheetBehavior.from(filter_bottom_sheet)
    // hide on start
    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
  }
}