package com.example.lenovo.reader.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.example.lenovo.reader.R
import com.example.model.models.Category
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * @author appcom interactive GmbH on 2019-06-13
 */
class CategoryFilterChipGroup : ChipGroup {

  private var itemList: List<Category> = mutableListOf()
  private var checkedChipsCounter = 0

  var onActiveStateChangeListener: ((isActive: Boolean) -> Unit)? = null

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

  }

  private val onClickListener: View.OnClickListener = View.OnClickListener { view ->
    val chip = view as Chip
    onChipClicked(chip.text as String)
    val oldChipsCount = checkedChipsCounter
    checkedChipsCounter = if (chip.isChecked) checkedChipsCounter + 1 else checkedChipsCounter - 1
    val newChipsCount = checkedChipsCounter
    if (oldChipsCount == 0) {
      onActiveStateChangeListener?.invoke(true) // Change on -> off
    } else if (oldChipsCount == 1 && newChipsCount == 0) {
      onActiveStateChangeListener?.invoke(false) // Change off -> on
    }
  }

  fun onChipClicked(text: String) {

  }

  fun setCategories(items: List<Category>) {
    itemList = items
    updateViews()
  }

  private fun updateViews() {
    val chipGroup = this
    itemList.forEach { category ->
      (LayoutInflater.from(context).inflate(
        R.layout.chip_filter_category, chipGroup, false
      ) as Chip).apply {
        text = category.name
        setOnClickListener(onClickListener)
        chipGroup.addView(this)
        tag = category.id
      }
    }
  }

  override fun getCheckedChipId(): Int {
    return super.getCheckedChipId()
  }

  override fun clearCheck() {
    super.clearCheck()
    checkedChipsCounter = 0
    onActiveStateChangeListener?.invoke(false) // Change on -> off
  }

  fun getCheckedCategoryIds(): List<Int> {
    val checkedCategories = mutableListOf<Int>()
    for (i in 0 until childCount) {
      val child = getChildAt(i)
      if (child is Chip && child.isChecked) {
        checkedCategories.add(child.tag as Int)
      }
    }
    return checkedCategories
  }

}