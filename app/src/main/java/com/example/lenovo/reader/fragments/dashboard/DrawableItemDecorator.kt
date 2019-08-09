package com.example.lenovo.reader.fragments.dashboard

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State

/**
 * @author appcom interactive GmbH on 2019-06-07
 */
class DrawableItemDecorator(
  val dividerDrawable: Drawable?
) :
  RecyclerView.ItemDecoration() {

  /**
   * Retrieve any offsets for the given item.
   * Specifies number of pixels that she view should be inserted by
   * Default implementation sets the out rect to 0
   *
   * If this ItemDecoration does not affect the positioning of item views,
   * it should set all four fields of outRect (left, top, right, bottom) to zero before returning.
   *
   * If you need to access Adapter for additional data, you can call getChildAdapterPosition(View)
   * to get the adapter position of the View.
   */
  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
    super.getItemOffsets(outRect, view, parent, state)
    if (parent.childCount != parent.getChildAdapterPosition(view)) {
      outRect.apply {
        bottom = dividerDrawable?.intrinsicHeight ?: 0
      }
    }
  }

  /**
   * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
   * Any content drawn by this method
   * - will be drawn before the item views are drawn
   * - will appear underneath the views
   *
   */
  override fun onDraw(canvas: Canvas, parent: RecyclerView, state: State) {
    super.onDraw(canvas, parent, state)
    val dividerLeft = parent.paddingLeft
    val dividerRight = parent.width - parent.paddingRight
    val childCount = parent.childCount

    for (itemPosition in 0 until childCount) {
      val childView = parent.getChildAt(itemPosition)
      val params = childView.layoutParams as RecyclerView.LayoutParams
      val dividerTop = childView.bottom + params.bottomMargin
      val dividerBottom: Int = dividerTop + (dividerDrawable?.getIntrinsicHeight() ?: 0)

      dividerDrawable?.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
      dividerDrawable?.draw(canvas)
    }
  }

  /**
   * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
   * Any content drawn by this method
   * - will be drawn after the item views are drawn
   * - will appear over the views
   */
  override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: State) {
    super.onDrawOver(canvas, parent, state)
  }
}

//    if (parent.getChildAdapterPosition(view) != 0) {
//      outRect.apply {
//        top = dividerDrawable?.intrinsicHeight ?: 0
//      }
//    }

