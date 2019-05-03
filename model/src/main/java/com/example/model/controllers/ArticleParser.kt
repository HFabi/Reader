package com.example.model.controllers

import com.example.model.models.Article

/**
 * @author appcom interactive GmbH on 2019-05-03
 */
interface ArticleParser {

  fun parse(): Article
}