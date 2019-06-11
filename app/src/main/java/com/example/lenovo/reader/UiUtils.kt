package com.example.lenovo.reader

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager

fun getDisplayMetrics(context: Context): DisplayMetrics {
  val displayMetrics = DisplayMetrics()
  val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
  windowManager.defaultDisplay?.getMetrics(displayMetrics)
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

fun pxFromSp(sp: Float, context: Context): Int {
  return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics)
    .toInt()
}

fun getActionBarHeight(context: Context): Int {
  var actionBarHeight = 0
  val styledAttributes = context.getTheme().obtainStyledAttributes(
    intArrayOf(android.R.attr.actionBarSize)
  )
  actionBarHeight = styledAttributes.getDimension(0, 0f).toInt()
  styledAttributes.recycle()
  return actionBarHeight
}