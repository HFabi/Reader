package com.example.lenovo.reader.fragments.dashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lenovo.reader.R
import com.example.lenovo.reader.fragments.base.BaseAdapter
import com.example.lenovo.reader.fragments.base.BaseViewHolder
import com.example.lenovo.reader.fragments.dashboard.adapters.LastAddedArticleAdapter.LastAddedArticleViewHolder
import com.example.model.models.LastAddedArticle
import kotlinx.android.synthetic.main.item_last_added_article.view.item_last_added_date_textView
import kotlinx.android.synthetic.main.item_last_added_article.view.item_last_added_subtitle_textView

class LastAddedArticleAdapter : BaseAdapter<LastAddedArticle, LastAddedArticleViewHolder>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): LastAddedArticleViewHolder = LastAddedArticleViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_last_added_article, parent, false)
  )

  override fun onBindViewHolder(
    holder: LastAddedArticleViewHolder,
    position: Int
  ) = holder.bindTo(itemList[position])

  private var onClickListener: ((LastAddedArticle) -> Unit)? = null

  fun addListener(listener:(LastAddedArticle) -> Unit) {
    onClickListener = listener
  }

  inner class LastAddedArticleViewHolder(var view: View) : BaseViewHolder<LastAddedArticle>(view) {

    override fun bindTo(item: LastAddedArticle) {
//      Picasso.get().load(item.imagePath).into(view.item_last_added_imageview)
      view.item_last_added_date_textView.text = item.date.toString()
      view.item_last_added_subtitle_textView.text = item.title

      view.setOnClickListener {
        if(onClickListener != null) {
          onClickListener?.invoke(item)
        }
      }
    }
  }
}


