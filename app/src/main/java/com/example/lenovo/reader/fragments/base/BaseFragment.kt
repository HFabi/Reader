package com.example.lenovo.reader.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import androidx.core.content.getSystemService
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.pxFromDp
import com.google.android.material.appbar.AppBarLayout
import dagger.android.support.DaggerFragment
import timber.log.Timber

abstract class BaseFragment : DaggerFragment() {

  private var scrollChangeListener: OnScrollChangedListener? = null
  private var viewTreeObserver: ViewTreeObserver? = null

  private val layoutResId: Int
    get() {
      val annotation = javaClass.getAnnotation(Layout::class.java)
      return if (annotation == null) {
        throw IllegalStateException("You must provide a layout annotation for " + javaClass.name)
      } else {
        (annotation as Layout).value
      }
    }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    var transition = TransitionInflater.from(this.context)
      .inflateTransition(R.transition.change_image_transition)
    sharedElementEnterTransition = transition
    sharedElementReturnTransition = transition
    val view = inflater.inflate(layoutResId, container, false)
    return view
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    providePresenter()?.let { lifecycle.addObserver(it) }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    providePresenter()?.let { lifecycle.removeObserver(it) }
    viewTreeObserver?.apply {
      if (isAlive) {
        removeOnScrollChangedListener(scrollChangeListener)
      }
    }
  }

  fun setUpToolbar(
    toolbar: Toolbar?,
    showUpNavigation: Boolean,
    hasOptionMenu: Boolean = true,
    titleRes: Int = 0
  ) {
    (activity as BaseActivity).setSupportActionBar(toolbar)
    (activity as BaseActivity).supportActionBar?.apply {
      setDisplayHomeAsUpEnabled(showUpNavigation)
      setDisplayShowHomeEnabled(showUpNavigation)
      if (titleRes != 0) {
        title = getString(titleRes)
      }
    }
    setHasOptionsMenu(hasOptionMenu)
  }

  fun setUpToolbar(
    toolbar: Toolbar?,
    upIndicatorRes: Int,
    titleRes: Int,
    hasOptionMenu: Boolean = true
  ) {
    (activity as BaseActivity).setSupportActionBar(toolbar)
    (activity as BaseActivity).supportActionBar?.apply {
      title = getString(titleRes)
      setDisplayHomeAsUpEnabled(true)
      setDisplayShowHomeEnabled(true)
      setHomeAsUpIndicator(upIndicatorRes)
    }
    setHasOptionsMenu(hasOptionMenu)
  }

  fun setUpCustomToolbar(
    toolbar: Toolbar?,
    showUpNavigation: Boolean,
    customView: View
  ) {
    (activity as BaseActivity).setSupportActionBar(toolbar)
    (activity as BaseActivity).supportActionBar?.apply {
      setDisplayHomeAsUpEnabled(showUpNavigation)
      setDisplayShowHomeEnabled(showUpNavigation)
      val layoutParams =
        Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT).apply {
          marginStart = 0
          marginEnd = pxFromDp(16f, context!!).toInt()
        }
      setCustomView(customView, layoutParams)
      setDisplayShowCustomEnabled(true)
    }
//    toolbar?.setNavigationOnClickListener()
    setHasOptionsMenu(true)
  }

  fun setUpAdaptiveToolbarElevation(bar: View, scrollableView: View) {
//    setUpAdaptiveToolbarElevation(bar, scrollableView, context!!.resources.getDimension(R.dimen.toolbar_delay))
    setUpAdaptiveToolbarElevation(bar, scrollableView, pxFromDp(8.0f, context!!))
  }

  fun setUpAdaptiveToolbarElevation(bar: View, scrollableView: View, delayInPx: Float) {
    if (bar is Toolbar || bar is AppBarLayout) {
      val elevation = context!!.resources.getDimension(R.dimen.toolbar_elevation)
      if (scrollableView is RecyclerView) {
        scrollChangeListener = OnScrollChangedListener {
          bar.elevation = if (scrollableView.computeVerticalScrollOffset() > delayInPx) elevation else 0f
        }
      } else if (scrollableView is NestedScrollView) {
        scrollChangeListener = OnScrollChangedListener {
          bar.elevation = if (scrollableView.scrollY > delayInPx) elevation else 0f
        }
      }
      viewTreeObserver = scrollableView.viewTreeObserver
      viewTreeObserver?.addOnScrollChangedListener(scrollChangeListener)
    } else {
      Timber.e("View should be of type Toolbar or AppBarLayout")
    }
  }

  abstract fun providePresenter(): BasePresenter?

  protected fun hideKeyboard(view: View?) {
    if (view != null) {
      context?.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }

  protected fun showKeyboard(view: View?) {
    if (view != null) {
      context?.getSystemService<InputMethodManager>()?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
  }

}