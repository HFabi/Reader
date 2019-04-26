package com.example.lenovo.reader.utils

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

/**
 * @author appcom interactive GmbH on 25.04.19
 */
class StartSnapHelper : LinearSnapHelper() {

  private var mVerticalHelper: OrientationHelper? = null
  private var mHorizontalHelper: OrientationHelper? = null

  override fun calculateDistanceToFinalSnap(layoutManager: LayoutManager, targetView: View): IntArray? {
    val out = IntArray(2)
    if (layoutManager.canScrollHorizontally()) {
      out[0] = distanceToStart(
        layoutManager, targetView,
        getHorizontalHelper(layoutManager)
      )
    } else {
      out[0] = 0
    }

    if (layoutManager.canScrollVertically()) {
      out[1] = distanceToStart(
        layoutManager, targetView,
        getVerticalHelper(layoutManager)
      )
    } else {
      out[1] = 0
    }
    return out
  }

  fun distanceToStart(layoutManager: RecyclerView.LayoutManager, targetView: View, helper: OrientationHelper): Int {
    val childStart = helper.getDecoratedStart(targetView)
    val containerStart: Int
    containerStart = helper.startAfterPadding
    return childStart - containerStart
  }

  fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
    if (mHorizontalHelper == null) {
      mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
    }
    return mHorizontalHelper ?: OrientationHelper.createHorizontalHelper(layoutManager)
  }

  fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
    if (mVerticalHelper == null) {
      mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
    }
    return mVerticalHelper ?: OrientationHelper.createVerticalHelper(layoutManager)
  }

  override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
    if (layoutManager.canScrollVertically()) {
      return findStartView(layoutManager, getVerticalHelper(layoutManager))
    } else if (layoutManager.canScrollHorizontally()) {
      return findStartView(layoutManager, getHorizontalHelper(layoutManager))
    }
    return null
  }

  private fun findStartView(
    layoutManager: RecyclerView.LayoutManager,
    helper: OrientationHelper
  ): View? {
    val childCount = layoutManager.childCount
    if (childCount == 0) {
      return null
    }

    var closestChild: View? = null
    val start: Int = helper.startAfterPadding
    var absClosest = Integer.MAX_VALUE

    for (i in 0 until childCount) {
      val child = layoutManager.getChildAt(i)
      val childCenter = helper.getDecoratedStart(child)
      val absDistance = Math.abs(childCenter - start)

      /** if child start is closer than previous closest, set it as closest   */
      if (absDistance < absClosest) {
        absClosest = absDistance
        closestChild = child
      }
    }
    return closestChild
  }

}