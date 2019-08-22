package com.example.lenovo.reader.fragments.dashboard.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.lenovo.reader.R
import com.example.lenovo.reader.fragments.base.BaseAdapter
import com.example.lenovo.reader.fragments.base.BaseViewHolder
import com.example.lenovo.reader.fragments.dashboard.adapters.LastAddedArticleAdapter.LastAddedArticleViewHolder
import com.example.lenovo.reader.pxFromDp
import com.example.model.models.LastAddedArticle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_last_added_article.view.item_last_added_cardview
import kotlinx.android.synthetic.main.item_last_added_article.view.item_last_added_date_textView
import kotlinx.android.synthetic.main.item_last_added_article.view.item_last_added_imageview
import kotlinx.android.synthetic.main.item_last_added_article.view.item_last_added_subtitle_textView
import timber.log.Timber
import java.text.SimpleDateFormat

class LastAddedArticleAdapter : BaseAdapter<LastAddedArticle, LastAddedArticleViewHolder>() {

  private var onClickListener: ((LastAddedArticle, View) -> Unit)? = null

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): LastAddedArticleViewHolder = LastAddedArticleViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_last_added_article, parent, false)
  )

  override fun onBindViewHolder(
    holder: LastAddedArticleViewHolder,
    position: Int
  ) {
    holder.bindTo(itemList[position])
  }

  fun addListener(listener: (LastAddedArticle, View) -> Unit) {
    onClickListener = listener
  }

  inner class LastAddedArticleViewHolder(var view: View) : BaseViewHolder<LastAddedArticle>(view) {

    override fun bindTo(item: LastAddedArticle) {
      view.item_last_added_date_textView.text = SimpleDateFormat("MMMM yy").format(item.addedAt)
      view.item_last_added_subtitle_textView.text = item.title

      Timber.d("ADAPTER item" + item.id)

      view.item_last_added_cardview.setCardBackgroundColor(getRandomColor(view.context))

      view.setOnClickListener {
        onClickListener?.invoke(item, view.item_last_added_imageview)
      }
      if (!item.imagePath.isEmpty()) {
        Log.d("ViewHolder", "Path in bind to   " + item.imagePath)
        Picasso.get()
          .load("file://" + item.imagePath)
          .resize(pxFromDp(200.0f, view.context).toInt(), pxFromDp(160.0f, view.context).toInt())
//          .resize(view.item_last_added_imageview.measuredWidth, view.item_last_added_imageview.measuredHeight)
          .centerCrop()
          .into(view.item_last_added_imageview)
      }
      view.item_last_added_imageview.transitionName = "transition" + item.id
    }

    fun getRandomColor(context: Context): Int {
      return when (Math.random()) {
        in 0.0f..0.3f -> ContextCompat.getColor(context, R.color.colorYellowOrange)
        in 0.3f..0.6f -> ContextCompat.getColor(context, R.color.colorRegentStBlue)
        else -> ContextCompat.getColor(context, R.color.colorSeaPink)
      }
    }
  }
}
