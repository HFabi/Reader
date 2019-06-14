package com.example.lenovo.reader.fragments.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : RecyclerView.ViewHolder> : RecyclerView.Adapter<V>() {

  var itemList: MutableList<T> = mutableListOf()

  override fun getItemCount(): Int = itemList.size

  fun add(item: T) {
    itemList.add(item)
    notifyItemInserted(itemCount)
  }

  fun replace(collection: Collection<T>) {
    itemList.clear()
    itemList.addAll(0, collection)
    notifyDataSetChanged()
  }

  fun append(collection: Collection<T>) {
    val length = itemList.size
    itemList.addAll(length, collection)
    notifyItemRangeInserted(length, collection.size)
  }
}