package com.example.lenovo.reader

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

fun getDisplayMetrics(context: Context): DisplayMetrics {
  val displayMetrics = DisplayMetrics()
  val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
  windowManager?.defaultDisplay?.getMetrics(displayMetrics)
  return displayMetrics
}

fun getStatusBarHeight(context: Context): Int {
  var statusBarHeight = 0
  val resourceId = context.getResources()
      .getIdentifier("status_bar_height", "dimen", "android")
  if (resourceId > 0) {
    statusBarHeight = context.getResources()
        .getDimensionPixelSize(resourceId)
  }
  return statusBarHeight
}

fun pxFromDp(
  dp: Float,
  context: Context
): Float {
  return dp * context.getResources().getDisplayMetrics().density
}