package com.example.lenovo.reader.fragments.dashboard.adapters

import android.content.Context
import android.view.LayoutInflater
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

  private fun updateViews() {
    itemList.forEach {
      (LayoutInflater.from(context).inflate(
          R.layout.chip_category, chipGroup, false
      ) as Chip).apply {
        text = it.name
        setOnClickListener(onClickListener)
        chipGroup.addView(this)
      }
    }
  }

  fun replaceAll(categories: List<Category>) {
    itemList = categories
    chipGroup.removeAllViews()
    updateViews()
  }
}