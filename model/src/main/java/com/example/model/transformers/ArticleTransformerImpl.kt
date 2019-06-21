package com.example.model.transformers

import com.example.model.entities.db.ArticleDbEntity
import com.example.model.entities.db.ExcerptArticleDbEntity
import com.example.model.entities.web.ArticleWebEntity
import com.example.model.models.Article
import com.example.model.models.ExcerptArticle
import java.util.Date
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
class ArticleTransformerImpl @Inject constructor() : ArticleTransformer {

  override fun toModel(articleDbEntity: ArticleDbEntity): Article {
    return Article(
      articleDbEntity.id,
      articleDbEntity.domain,
      articleDbEntity.url,
      articleDbEntity.title,
      articleDbEntity.author,
      articleDbEntity.datePublished,
      articleDbEntity.leadImagePath,
      articleDbEntity.excerpt,
      articleDbEntity.content,
      articleDbEntity.localPath,
      articleDbEntity.nextPageUrl,
      articleDbEntity.addedAt
    )
  }

  override fun toModel(articleWebEntity: ArticleWebEntity): Article {
    return Article(
      0,
      articleWebEntity.domain,
      articleWebEntity.url,
      articleWebEntity.title,
      articleWebEntity.author,
      articleWebEntity.datePublished,
      articleWebEntity.leadImageUrl,
      articleWebEntity.excerpt,
      articleWebEntity.content,
      "",
      articleWebEntity.nextPageUrl,
      Date()
    )
  }

  override fun toModel(excerptArticleDbEntity: ExcerptArticleDbEntity): ExcerptArticle {
    return ExcerptArticle(
      excerptArticleDbEntity.id,
      excerptArticleDbEntity.title,
      excerptArticleDbEntity.addedAt,
      excerptArticleDbEntity.imagePath
    )
  }

  override fun toDbEntity(article: Article): ArticleDbEntity {
    return ArticleDbEntity(
      article.id,
      article.domain,
      article.url,
      article.title,
      article.author,
      article.datePublished,
      article.leadImagePath,
      article.excerpt,
      article.content,
      article.localPath,
      article.nextPageUrl,
      article.addedAt
    )
  }

  override fun toDbEntity(excerptArticle: ExcerptArticle): ExcerptArticleDbEntity {
    return ExcerptArticleDbEntity(
      excerptArticle.id,
      excerptArticle.title,
      excerptArticle.addedAt,
      excerptArticle.imagePath
    )
  }
}
