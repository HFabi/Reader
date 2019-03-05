package com.example.lenovo.reader.fragments.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lenovo.reader.R
import com.example.lenovo.reader.fragments.base.BaseAdapter
import com.example.lenovo.reader.fragments.base.BaseViewHolder
import com.example.lenovo.reader.fragments.dashboard.FavoriteArticleAdapter.FavoriteArticleViewHolder
import com.example.model.models.FavoriteArticle

class FavoriteArticleAdapter() : BaseAdapter<FavoriteArticle, FavoriteArticleViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): FavoriteArticleViewHolder = FavoriteArticleViewHolder(
      LayoutInflater.from(parent.context).inflate(
          R.layout.item_favorite_article, parent, false
      )
  )

  override fun onBindViewHolder(
    holder: FavoriteArticleViewHolder,
    position: Int
  ) = holder.bindTo(itemList[position])

  class FavoriteArticleViewHolder(item: View) : BaseViewHolder<FavoriteArticle>(item) {
    override fun bindTo(item: FavoriteArticle) {

    }
  }
}