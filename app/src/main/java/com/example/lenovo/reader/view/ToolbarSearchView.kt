package com.example.lenovo.reader.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.example.lenovo.reader.R

/**
 * @author appcom interactive GmbH on 2019-07-04
 */
class ToolbarSearchView : AppCompatEditText {

  private var drawable: Drawable? = null
  var onSearchChange: ((text: String) -> Unit)? = null

  constructor(context: Context?) : this(context, null)

  constructor(context: Context?, attrs: AttributeSet?) : this(context, null, 0)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context) {
    init()
  }

  private fun init() {
    drawable = context.getDrawable(R.drawable.ic_close_black_24dp)
    drawable?.setBounds(0, 0, drawable?.getIntrinsicWidth() ?: 0, drawable?.getIntrinsicHeight() ?: 0);
    doAfterTextChanged { text -> onTextChange(text.toString()) }
//    setOnTouchListener(this::onTouch)
    setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
    hint = context.getString(R.string.hint_search_articles)

//    isFocusableInTouchMode = true

    if (false)
      setFocusable(false)
    else
      setFocusableInTouchMode(true)
    Log.d("CALL","Call Cosntructor")

//    focusable=View.FOCUSABLE
//    focusableInTouchMode="true"
//    textSize

//    requestFocus() //-> dadurch ist keyboard nicht sichtbar UND man nkann nicht mehr auf Edittext klicken
  }

  fun onTextChange(text: String) {
    setClearIconVisible(!text.isEmpty())
    onSearchChange?.invoke(text)
  }

  private fun setClearIconVisible(isVisible: Boolean) {
    if (isVisible) {
      setCompoundDrawables(null, null, drawable, null)
    } else {
      setCompoundDrawables(null, null, null, null)
    }
  }

//  private fun onTouch(view: View, event: MotionEvent): Boolean {
//
//    if (event.getAction() == MotionEvent.ACTION_UP) {
//      // getRight right position of the view, relative to its parent
//      // getRawX original location of event on screen
//      val drawableWidth: Int = compoundDrawables[2]?.bounds?.width() ?: 0
//      if (event.getRawX() >= right - drawableWidth) {
//        Log.d("ONTOUCH","click icon triggerred")
//        clearEditText()
//        return true
//      }
//    }
//    Log.d("ONTOUCH","false")
//    return false
//  }

  fun clearEditText() {
    setText("")
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    if (event?.getAction() == MotionEvent.ACTION_UP) {
      // getRight right position of the view, relative to its parent
      // getRawX original location of event on screen
      val drawableWidth: Int = compoundDrawables[2]?.bounds?.width() ?: 0
      if (event.getRawX() >= right - drawableWidth) {
        Log.d("ONTOUCH","click icon triggerred")
        clearEditText()
        return true
      }
    }
    Log.d("ONTOUCH","false")
    return super.onTouchEvent(event)
  }

}