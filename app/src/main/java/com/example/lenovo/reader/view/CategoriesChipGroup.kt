package com.example.lenovo.reader.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.InsetDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.getSystemService
import com.example.lenovo.reader.R
import com.example.lenovo.reader.R.string
import com.example.lenovo.reader.pxFromDp
import com.example.model.models.Category
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.Date

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
class CategoriesChipGroup : ChipGroup {

  val ID_ACTION_ADD = -1L

  private var categoryList: MutableList<Category> = mutableListOf()

  private var checkedCategories: MutableSet<Category> = mutableSetOf()

  private val categoryNamesList: List<String>
    get() = categoryList.map { category -> category.name.toLowerCase() }

  // click on chip
  private val onChipClickListener: View.OnClickListener = View.OnClickListener {
    val chip = it as Chip
//    if (chip.isChecked) {
//      checkedCategories.add(chip.tag as Category)
//    } else {
//      checkedCategories.remove(chip.tag as Category)
//    }
    handleCheckedChange(chip)
    onChipClicked?.invoke()
  }

  // click on chip with +
  private val onAddChipClickListener: View.OnClickListener = View.OnClickListener {
    if (!containsEditableView()) {
      addEditableView()
    }
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

  fun containsEditableView(): Boolean {
    for (i in 0 until childCount) {
      val child = getChildAt(i)
      if (child is EditText) {
        return true
      }
    }
    return false
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

  fun init() {
    categoryList.add(0, Category(ID_ACTION_ADD, " + "))
    updateViews()
  }

  // can add
  // list -> show list
  fun setCategories(categories: List<Category>) {
    categoryList = categories.toMutableList()
    categoryList.add(0, Category(ID_ACTION_ADD, " + "))
    updateViews()
  }

  // replace
  private fun updateViews() {
    removeAllViews()
    categoryList.forEach {
      addView(getChipFromCategory(it))
    }
  }

  /**
   * Create a chip for category
   */
  private fun getChipFromCategory(category: Category): Chip {
    if (category.id == ID_ACTION_ADD) {
      return createActionChipFromCategory(category)
    } else {
      return createFilterChipFromCategory(category)
    }
  }

  private fun createActionChipFromCategory(category: Category): Chip {
    return (LayoutInflater.from(context).inflate(
      R.layout.chip_category_action, this, false
    ) as Chip).apply {
      text = category.name
      chipBackgroundColor = ColorStateList.valueOf(resources.getColor(android.R.color.black))
      setTextColor(resources.getColor(android.R.color.white))
      setOnClickListener(onAddChipClickListener)
    }
  }

  private fun createFilterChipFromCategory(category: Category): Chip {
    return (LayoutInflater.from(context).inflate(
      R.layout.chip_category_filter, this, false
    ) as Chip).apply {
      text = category.name
      setOnClickListener(onChipClickListener)
      tag = category
    }
  }

  /**
   * Add a new category at first check-chip position (after action chip)
   */
  private fun addCategory(category: Category, categoryIsChecked: Boolean) {
    val chip = getChipFromCategory(category).apply { isChecked = categoryIsChecked }
    if (categoryIsChecked) {
      handleCheckedChange(chip)
    }
    categoryList.add(1, category) //TODO check
    addView(chip, 1)
  }

  /**
   * Adapt check-changes to checkedCategories list
   */
  private fun handleCheckedChange(chip: Chip) {
    if (chip.isChecked) {
      checkedCategories.add(chip.tag as Category)
    } else {
      checkedCategories.remove(chip.tag as Category)
    }
  }

  /**
   * Add editable view after action button
   */
  private fun addEditableView() {
    val editText = EditText(context).apply {
      textSize = 15.0f
      hint = context.getString(string.category_hint)
      imeOptions = EditorInfo.IME_ACTION_DONE
      setSingleLine(true)
      maxLines = 1
      setPaddingRelative(36, 0, 36, 0)
      background = InsetDrawable(context.getDrawable(R.drawable.bg_editchip), 5, 6, 5, 6)
    }
    val params = ChipGroup.LayoutParams(ChipGroup.LayoutParams.WRAP_CONTENT, pxFromDp(36f, context!!).toInt()).apply {
      topMargin = 0
    }
    addView(editText, 1, params)
    editText.requestFocus()
    context?.getSystemService<InputMethodManager>()?.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    editText.setOnEditorActionListener { textview, actionId, event ->
      if (actionId == EditorInfo.IME_ACTION_DONE) {
        onActionDone(textview)
        true
      } else {
        false
      }
    }
  }

  /**
   * Returns first chip where name matches
   */
  fun findChipByCategoryName(categoryName: String): Chip? {
    for (i in 0 until childCount) {
      val child = getChildAt(i)
      if (child is Chip && child.tag != null) {
        val category = child.tag as Category
        if (category.name.toLowerCase() == categoryName.toLowerCase()) {
          return child
        }
      }
    }
    return null
  }

  private fun onActionDone(textview: TextView) {
    val categoryName = textview.text.toString().trim()
    if (categoryName.isEmpty()) {
      // category is empty
      context?.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(textview.windowToken, 0)
      removeViewAt(1)
    } else if (categoryNamesList.contains(categoryName.toLowerCase())) {
      // category already exists -> set existing tag checked
      findChipByCategoryName(categoryName)?.let { chip ->
        chip.isChecked = true
        handleCheckedChange(chip)
      }
      context?.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(textview.windowToken, 0)
      removeViewAt(1)
    } else {
      // category is new
      context?.getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(textview.windowToken, 0)
      removeViewAt(1)
      addCategory(Category(Date().time, categoryName), true)
    }
  }

}