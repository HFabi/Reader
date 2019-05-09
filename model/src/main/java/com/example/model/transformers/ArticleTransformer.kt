package com.example.model.transformers

import com.example.model.entities.db.ArticleDbEntity
import com.example.model.entities.web.ArticleWebEntity
import com.example.model.models.Article
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
interface ArticleTransformer {

  fun toModel(articleDbEntity: ArticleDbEntity): Article

  fun toModel(articleWebEntity: ArticleWebEntity): Article

  fun toDbEntity(article: Article): ArticleDbEntity

  fun toLastAddedArticle(articleDbEntity: ArticleDbEntity): LastAddedArticle

  fun toFavoriteArticle(articleDbEntity: ArticleDbEntity): FavoriteArticle
}