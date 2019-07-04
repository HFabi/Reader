package com.example.model.transformers

import com.example.model.entities.db.ArticleDbEntity
import com.example.model.entities.db.ExcerptArticleDbEntity
import com.example.model.entities.db.LastAddedArticleDbEntity
import com.example.model.entities.web.ArticleWebEntity
import com.example.model.models.Article
import com.example.model.models.ExcerptArticle
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
interface ArticleTransformer {

  fun toModel(articleDbEntity: ArticleDbEntity): Article

  fun toModel(articleWebEntity: ArticleWebEntity): Article

  fun toModel(excerptArticleDbEntity: ExcerptArticleDbEntity): ExcerptArticle

  fun toModel(lastAddedArticleDbEntity: LastAddedArticleDbEntity): LastAddedArticle

  fun toModel(lastAddedArticleDbEntities: List<LastAddedArticleDbEntity>): List<LastAddedArticle>

  fun toDbEntity(article: Article): ArticleDbEntity

  fun toDbEntity(excerptArticle: ExcerptArticle): ExcerptArticleDbEntity

}