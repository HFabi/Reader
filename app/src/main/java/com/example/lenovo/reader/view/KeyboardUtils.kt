package com.example.lenovo.reader.view

import android.content.Context
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService



/**
 * @author appcom interactive GmbH on 2019-07-04
 */
class KeyboardUtils {
  fun hideKeyboard(focusedView: View?) {
    if (focusedView != null) {
      val inputMethodManager = focusedView.getContext()
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      if (inputMethodManager != null) {
        inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0)
      }
    }
  }

  fun showKeyboard(focusedView: View?) {
    if (focusedView != null) {
      val inputMethodManager = focusedView.getContext()
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      if (inputMethodManager != null) {
        inputMethodManager.showSoftInput(focusedView, InputMethodManager.SHOW_IMPLICIT)
      }
    }
  }

}