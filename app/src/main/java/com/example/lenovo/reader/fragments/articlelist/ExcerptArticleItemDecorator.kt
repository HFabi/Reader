package com.example.lenovo.reader.fragments.articlelist

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.State

/**
 * @author appcom interactive GmbH on 2019-06-13
 */
class ExcerptArticleItemDecorator(val space: Int):
  RecyclerView.ItemDecoration() {

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
    super.getItemOffsets(outRect, view, parent, state)

    outRect.apply {
      bottom = space
    }

  }

}