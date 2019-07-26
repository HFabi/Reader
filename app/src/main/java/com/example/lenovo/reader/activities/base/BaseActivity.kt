package com.example.lenovo.reader.activities.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.core.content.getSystemService
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes val contentLayoutId: Int) : DaggerAppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (contentLayoutId != 0) {
      setContentView(contentLayoutId)
    }
  }

  protected fun hideKeyboard(view: View?) {
    if (view != null) {
      getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }

  protected fun showKeyboard(view: View?) {
    if (view != null) {
      getSystemService<InputMethodManager>()?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    super.onCreate(savedInstanceState, persistentState)
    providePresenter()?.let { lifecycle.addObserver(it) }
  }

  override fun onDestroy() {
    super.onDestroy()
    providePresenter()?.let { lifecycle.removeObserver(it) }
  }

  abstract fun providePresenter(): BasePresenter?

}
