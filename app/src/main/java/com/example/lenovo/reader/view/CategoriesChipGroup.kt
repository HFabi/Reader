package com.example.lenovo.reader.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.lenovo.reader.R
import com.example.model.models.Category
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
class CategoriesChipGroup : ChipGroup {

  private var categoryList: List<Category> = mutableListOf()

  private val onClickListener: View.OnClickListener = View.OnClickListener {
    onChipClicked((it as Chip).text as String)
  }

  constructor(context: Context): super(context) {

  }

  constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet) {

  }

  constructor(context: Context, attributeSet: AttributeSet, a: Int): super(context, attributeSet, a) {

  }


  // can add
  // list -> show list
  fun setCategories(categories: List<Category>) {
    categoryList = categories
    categoryList = mutableListOf(Category(234," + ")) + categoryList
    updateViews()
  }

  fun onChipClicked(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
  }

  private fun updateViews() {
    var chipGroup = this
    categoryList.forEach {
      (LayoutInflater.from(context).inflate(
        R.layout.chip_category, chipGroup, false
      ) as Chip).apply {
        text = it.name
        setOnClickListener(onClickListener)
        chipGroup.addView(this)
        if(it.name == " + ") {
          chipBackgroundColor = ColorStateList.valueOf(resources.getColor(android.R.color.black))
          setTextColor(resources.getColor(android.R.color.white))
        }
      }
    }
  }

//  fun replaceAll(categories: List<Category>) {
//    itemList = categories
//    chipGroup.removeAllViews()
//    updateViews()
//  }

}