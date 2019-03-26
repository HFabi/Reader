package com.example.lenovo.reader.fragments.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(var itemView: View) : RecyclerView.ViewHolder(itemView) {

  abstract fun bindTo(item: T)
}