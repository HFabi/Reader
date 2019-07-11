package com.example.lenovo.reader.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.InsetDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.core.view.marginBottom
import com.example.lenovo.reader.R
import com.example.lenovo.reader.pxFromDp
import com.example.model.models.Category
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * @author appcom interactive GmbH on 2019-06-13
 */
class CategoryFilterChipGroup : ChipGroup {

  private var itemList: MutableList<Category> = mutableListOf()
  private var checkedChipsCounter = 0

  var allCategoriesString: String = ""

  var onActiveStateChangeListener: ((isActive: Boolean) -> Unit)? = null
  var onFilterChangeListener: ((List<Long>) -> Unit)? = null

  constructor(context: Context) : super(context) {
    init()
  }

  constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
    init()
  }

  constructor(context: Context, attributeSet: AttributeSet, a: Int) : super(context, attributeSet, a) {
    init()
  }

  fun init() {
    allCategoriesString = context.getString(R.string.categories_all)
  }

  private val onAllCategoriesChipClickListener: View.OnClickListener = View.OnClickListener { view ->
    if (getCheckedCategoryIds().size < itemList.size - 1) {
      setChipsChecked(true)
    } else {
      setChipsChecked(false)
    }
    onFilterChangeListener?.invoke(getCheckedCategoryIds())
  }

  private val onClickListener: View.OnClickListener = View.OnClickListener { view ->
    val chip = view as Chip
    val oldChipsCount = checkedChipsCounter
    checkedChipsCounter = if (chip.isChecked) checkedChipsCounter + 1 else checkedChipsCounter - 1
    val newChipsCount = checkedChipsCounter
    if (oldChipsCount == 0) {
      onActiveStateChangeListener?.invoke(true) // Change on -> off
    } else if (oldChipsCount == 1 && newChipsCount == 0) {
      onActiveStateChangeListener?.invoke(false) // Change off -> on
    }
    onFilterChangeListener?.invoke(getCheckedCategoryIds())
  }

  fun replaceCategories(items: List<Category>) {
    removeAllViews()
    itemList = items.toMutableList()
    itemList.add(0, Category(-1L, allCategoriesString))

//    createEditableChip()

    itemList.forEach { category -> addView(getChipFromCategory(category)) }
  }

//  fun createEditableChip() {
//    val editText = EditText(context).apply {
//      textSize = 15.0f
//      setPaddingRelative(36, 0, 36, 0)
//      setText("ABCDE")
////      minimumWidth = 50
////      minWidth = 60
//      background = InsetDrawable(context.getDrawable(R.drawable.bg_editchip), 5, 6, 5, 6)
//    }
//    val params = ChipGroup.LayoutParams(ChipGroup.LayoutParams.WRAP_CONTENT, pxFromDp(36f, context!!).toInt()).apply {
//      topMargin = 0
//    }
//    addView(editText, params)
//  }

  override fun clearCheck() {
    super.clearCheck()
    checkedChipsCounter = 0
    onActiveStateChangeListener?.invoke(false) // Change on -> off
    onFilterChangeListener?.invoke(getCheckedCategoryIds())
  }

  private fun setChipsChecked(isChecked: Boolean) {
    for (i in 0 until childCount) {
      val child = getChildAt(i)
      if (child is Chip) {
        child.isChecked = isChecked
      }
    }
  }

  private fun getCheckedCategoryIds(): List<Long> {
    val checkedCategories = mutableListOf<Long>()
    for (i in 0 until childCount) {
      val child = getChildAt(i)
      if (child is Chip && child.isChecked) {
        checkedCategories.add(child.tag as Long)
      }
    }
    checkedCategories.forEach { elm -> Log.d("FILTER", "" + elm) }
    return checkedCategories
  }

  private fun getChipFromCategory(category: Category): Chip {
    if (category.id == -1L) {
      return (LayoutInflater.from(context).inflate(
        R.layout.chip_category_action, this, false
      ) as Chip).apply {
        text = category.name
        chipBackgroundColor = ColorStateList.valueOf(resources.getColor(android.R.color.black))
        setTextColor(resources.getColor(android.R.color.white))
        setOnClickListener(onAllCategoriesChipClickListener)
      }
    } else {
      return (LayoutInflater.from(context).inflate(
        R.layout.chip_category_filter, this, false
      ) as Chip).apply {
        text = category.name
        setOnClickListener(onClickListener)
        tag = category.id
      }
    }
  }
}

//    itemList.forEach { category ->
//      (LayoutInflater.from(context).inflate(
//        R.layout.chip_category_filter, chipGroup, false
//      ) as Chip).apply {
//        text = category.name
//        setOnClickListener(onClickListener)
//        chipGroup.addView(this)
//        tag = category.id
//      }
//    }
