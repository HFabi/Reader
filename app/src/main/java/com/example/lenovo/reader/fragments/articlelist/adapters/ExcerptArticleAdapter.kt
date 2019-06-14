package com.example.lenovo.reader.fragments.articlelist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lenovo.reader.R
import com.example.lenovo.reader.fragments.articlelist.adapters.ExcerptArticleAdapter.ExcerptArticleViewHolder
import com.example.lenovo.reader.fragments.base.BaseAdapter
import com.example.lenovo.reader.fragments.base.BaseViewHolder
import com.example.lenovo.reader.pxFromDp
import com.example.model.models.ExcerptArticle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_excerpt_article.view.item_excerpt_description_textview
import kotlinx.android.synthetic.main.item_excerpt_article.view.item_excerpt_imageview
import kotlinx.android.synthetic.main.item_excerpt_article.view.item_excerpt_title_textview
import java.text.SimpleDateFormat

/**
 * @author appcom interactive GmbH on 2019-06-13
 */
class ExcerptArticleAdapter : BaseAdapter<ExcerptArticle, ExcerptArticleViewHolder>() {

  var onItemClickListener: ((ExcerptArticle, View) -> Unit)? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExcerptArticleViewHolder =
    ExcerptArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_excerpt_article, parent, false))

  override fun onBindViewHolder(holder: ExcerptArticleViewHolder, position: Int) {
    holder.bindTo(itemList[position])
  }

  inner class ExcerptArticleViewHolder(var view: View) : BaseViewHolder<ExcerptArticle>(view) {

    override fun bindTo(item: ExcerptArticle) {
      view.setOnClickListener {
        onItemClickListener?.invoke(item, view)
      }
      view.item_excerpt_title_textview.text = item.title
      view.item_excerpt_description_textview.text = SimpleDateFormat("MMMM yy").format(item.addedAt)
      if (!item.imagePath.isEmpty()) {
        Picasso.get()
          .load("file://" + item.imagePath)
          .resize(pxFromDp(200.0f, view.context).toInt(), pxFromDp(160.0f, view.context).toInt())
//          .resize(view.item_last_added_imageview.measuredWidth, view.item_last_added_imageview.measuredHeight)
          .centerCrop()
          .into(view.item_excerpt_imageview)
      }
    }
  }
}