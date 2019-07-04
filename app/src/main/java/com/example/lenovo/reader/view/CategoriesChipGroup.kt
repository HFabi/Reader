package com.example.lenovo.reader.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.example.lenovo.reader.R
import com.example.model.models.Category
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
class CategoriesChipGroup : ChipGroup {

  private var categoryList: MutableList<Category> = mutableListOf()

  private var checkedCategories: MutableSet<Category> = mutableSetOf()

  // click on chip
  private val onChipClickListener: View.OnClickListener = View.OnClickListener {
    var chip = it as Chip
    if (chip.isChecked) {
      checkedCategories.add(chip.tag as Category)
    } else {
      checkedCategories.remove(chip.tag as Category)
    }
    onChipClicked?.invoke()
  }

  // click on chip with +
  private val onAddChipClickListener: View.OnClickListener = View.OnClickListener {
    onAddClicked?.invoke()
  }

  val selectedCategoryIdentifier: List<Category>
    get() = checkedCategories.toList()

  var onAddClicked: (() -> Unit)? = null

  var onChipClicked: (() -> Unit)? = null

  constructor(context: Context) : this(context, null)
  constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
    init()
  }
  constructor(context: Context, attributeSet: AttributeSet?, a: Int) : super(context, attributeSet, a) {
    init()
  }

  fun init() {
    categoryList.add(0, Category(-1L, " + "))
    updateViews()
  }

  // can add
  // list -> show list
  fun setCategories(categories: List<Category>) {
    categoryList = categories.toMutableList()
    categoryList.add(0, Category(-1L, " + "))
    updateViews()
  }

  fun addCategory(category: Category) {
    addView(getChipFromCategory(category), 1)
  }

  // replace
  private fun updateViews() {
    removeAllViews()
    categoryList.forEach {
      addView(getChipFromCategory(it))
    }
  }

  private fun getChipFromCategory(category: Category): Chip {
    if (category.id == -1L) {
      return (LayoutInflater.from(context).inflate(
        R.layout.chip_category_action, this, false
      ) as Chip).apply {
        text = category.name
        chipBackgroundColor = ColorStateList.valueOf(resources.getColor(android.R.color.black))
        setTextColor(resources.getColor(android.R.color.white))
        setOnClickListener(onAddChipClickListener)
//        tag = category
      }
    } else {
      return (LayoutInflater.from(context).inflate(
        R.layout.chip_category_filter, this, false
      ) as Chip).apply {
        text = category.name
        setOnClickListener(onChipClickListener)
        tag = category
      }
    }
  }
}