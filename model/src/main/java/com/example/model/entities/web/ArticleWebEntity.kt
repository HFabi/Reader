package com.example.model.entities.web

import java.util.Date

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
data class ArticleWebEntity(
  var domain: String?,
  var url: String?,
  var title: String?,
  var author: String? = "",
  var date_published: Long?,
  var lead_image_url: String?,
  var excerpt: String?,
  var content: String?,
  var next_page_url: String?
)