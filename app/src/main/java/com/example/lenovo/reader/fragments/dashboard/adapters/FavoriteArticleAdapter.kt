package com.example.lenovo.reader.fragments.dashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lenovo.reader.R
import com.example.lenovo.reader.fragments.base.BaseAdapter
import com.example.lenovo.reader.fragments.base.BaseViewHolder
import com.example.lenovo.reader.fragments.dashboard.adapters.FavoriteArticleAdapter.FavoriteArticleViewHolder
import com.example.model.models.FavoriteArticle
import kotlinx.android.synthetic.main.item_favorite_article.view.item_favorite_subtile_textview
import kotlinx.android.synthetic.main.item_favorite_article.view.item_favorite_title_textview

class FavoriteArticleAdapter() : BaseAdapter<FavoriteArticle, FavoriteArticleViewHolder>() {

  private var onClickListener: ((FavoriteArticle) -> Unit)? = null

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): FavoriteArticleViewHolder = FavoriteArticleViewHolder(
      LayoutInflater.from(parent.context).inflate(
          R.layout.item_favorite_article, parent, false
      )
  )

  fun addListener(listener: (FavoriteArticle) -> Unit) {
    onClickListener = listener
  }

  override fun onBindViewHolder(
    holder: FavoriteArticleViewHolder,
    position: Int
  ) = holder.bindTo(itemList[position])

  inner class FavoriteArticleViewHolder(var view: View) : BaseViewHolder<FavoriteArticle>(view) {
    override fun bindTo(item: FavoriteArticle) {
      //item_favorite_imageview
      view.item_favorite_title_textview.text = item.title
      view.item_favorite_subtile_textview.text = item.excerpt
      view.setOnClickListener {
        onClickListener?.invoke(item)
      }
    }
  }
}