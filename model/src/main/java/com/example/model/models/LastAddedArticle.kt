package com.example.model.models

import java.util.Date

data class LastAddedArticle(
  var id: Int,
  var title: String,
  var date: Date,
  var imagePath: String
)