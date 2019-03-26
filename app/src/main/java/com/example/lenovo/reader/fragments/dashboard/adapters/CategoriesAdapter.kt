package com.example.lenovo.reader.fragments.dashboard.adapters

import android.content.Context
import android.view.View
import com.example.lenovo.reader.R
import com.example.model.models.Category
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class CategoriesAdapter(
  var chipGroup: ChipGroup,
  var context: Context?,
  var onChipClicked: (String) -> Unit
) {

  private var itemList: List<Category> = mutableListOf()
  private val onClickListener: View.OnClickListener = View.OnClickListener {
    onChipClicked((it as Chip).text as String)
  }

  fun updateViews() {
    itemList.forEach {
      val chip: Chip = Chip(context).apply {
        text = it.name
        setRippleColorResource(R.color.colorAccent)
        chipStrokeWidth = 1.0f
        setChipBackgroundColorResource(R.color.colorWhite)
        setChipStrokeColorResource(R.color.colorPrimaryDark)
//        chipStartPadding = 2.0f
//        chipEndPadding = 2.0f
      }
      chip.setOnClickListener(onClickListener)
      chipGroup.addView(chip)
    }
  }

  fun replaceAll(categories: List<Category>) {
    itemList = categories
    chipGroup.removeAllViews()
    updateViews()
  }
}