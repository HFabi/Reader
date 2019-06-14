package com.example.lenovo.reader.view

import android.content.Context
import android.graphics.Outline
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.LinearLayout
import com.example.lenovo.reader.R
import kotlinx.android.synthetic.main.view_searchtextview.view.searchtextview_imageview

/**
 * @author appcom interactive GmbH on 2019-06-13
 */
class SearchTextView : LinearLayout {

  constructor(context: Context) : super(context) {
    init()
  }

  constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
    init()
  }

  constructor(context: Context, attributeSet: AttributeSet, a: Int) : super(context, attributeSet, a) {
    init()
  }

  private fun init() {
    val inflater = LayoutInflater.from(context)
    val layout = inflater.inflate(R.layout.view_searchtextview, this)
    val radius = context.resources.getDimension(R.dimen.serachtextview_radius)

    searchtextview_imageview.background = context.getDrawable(R.drawable.ic_search_black_24dp)

    val viewOutlineProvider = object : ViewOutlineProvider() {
      override fun getOutline(view: View, outline: Outline) {
        val rect = Rect()
        view.background.copyBounds(rect)
        outline.setRoundRect(rect, radius)
      }
    }
    background = context.getDrawable(R.drawable.view_serachtextview_bg)
    outlineProvider = viewOutlineProvider
    elevation = 4.0f

  }
}

//    searchtextview_linearlayout.elevation = 8.0f
//    searchtextview_linearlayout.background = context.getDrawable(R.drawable.view_serachtextview_bg)
//    searchtextview_linearlayout.outlineProvider = viewOutlineProvider

