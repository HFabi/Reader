package com.example.lenovo.reader.fragments.article

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.lenovo.reader.R
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.LifecycleObserverPresenter
import com.example.lenovo.reader.navigation.Router
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_article.article_appbarlayout
import kotlinx.android.synthetic.main.fragment_article.article_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_article)
class ArticleFragment : BaseFragment(), ArticleView {

  @Inject
  lateinit var articlePresenter: ArticlePresenter
  @Inject
  lateinit var router: Router

  override fun providePresenter(): LifecycleObserverPresenter = articlePresenter

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(article_toolbar, true)
    setUp()
  }

  private var showActionFavorite = false
  private var isFavorite = false

  fun setUp() {
    //Collapsing Tollbar
    // article_appbarlayout.totalScrollRange

    article_appbarlayout.addOnOffsetChangedListener(
        AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
          if (verticalOffset == -1*article_appbarlayout.totalScrollRange && !showActionFavorite) {
            showActionFavorite = true
            activity?.invalidateOptionsMenu()
            Log.d("off", "invalidate MENU")
          }
          if (verticalOffset != -1*article_appbarlayout.totalScrollRange && showActionFavorite) {
            showActionFavorite = false
            activity?.invalidateOptionsMenu()
            Log.d("off", "invalidate MENU")
          }

        })

    //Floating Action Button

  }

  /**
   * Called only once for creation
   */
  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    inflater.inflate(R.menu.menu_article, menu)
    var item: MenuItem = menu.findItem(R.id.action_article_favorite)
    item.isVisible = showActionFavorite
    item.icon = favoriteIcon()
    super.onCreateOptionsMenu(menu, inflater)
  }

  /**
   * Called when invalidate
   */
  override fun onPrepareOptionsMenu(menu: Menu) {
    var item: MenuItem = menu.findItem(R.id.action_article_favorite)
    item.isVisible = showActionFavorite
    item.icon = favoriteIcon()
    super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> router.goBack()
      R.id.action_article_favorite -> onFavoriteButtonClicked()
      R.id.action_article_share -> Log.d("TODO","NOT IMPLEMENTED") //TODO
      R.id.action_article_delete -> Log.d("TODO","NOT IMPLEMENTED") //TODO
    }
    return super.onOptionsItemSelected(item)
  }

  private fun onFavoriteButtonClicked() {

  }

  private fun favoriteIcon(): Drawable? {
    return context?.getDrawable(
        if (isFavorite) R.drawable.ic_favorite_black_24dp else R.drawable.ic_favorite_border_black_24dp
    )
  }

}