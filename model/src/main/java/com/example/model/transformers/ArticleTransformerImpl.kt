package com.example.model.transformers

import com.example.model.entities.db.ArticleDbEntity
import com.example.model.entities.db.ExcerptArticleDbEntity
import com.example.model.entities.db.LastAddedArticleDbEntity
import com.example.model.entities.web.ArticleWebEntity
import com.example.model.models.Article
import com.example.model.models.ExcerptArticle
import com.example.model.models.LastAddedArticle
import timber.log.Timber
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
      Date().time,
      articleWebEntity.domain ?:"",
      articleWebEntity.url ?:"",
      articleWebEntity.title ?:"",
      articleWebEntity.author ?:"",
      articleWebEntity.date_published?.let { Date(it)} ?: null,
      articleWebEntity.lead_image_url ?:"",
      articleWebEntity.excerpt ?:"",
      articleWebEntity.content ?:"",
      "",
      articleWebEntity.next_page_url ?:"",
      Date()
    )
  }

  override fun toModel(excerptArticleDbEntity: ExcerptArticleDbEntity): ExcerptArticle {
    return ExcerptArticle(
      excerptArticleDbEntity.id,
      excerptArticleDbEntity.title,
      excerptArticleDbEntity.addedAt,
      excerptArticleDbEntity.leadImagePath
    )
  }

  override fun toModel(lastAddedArticleDbEntity: LastAddedArticleDbEntity): LastAddedArticle {
    return LastAddedArticle(
      lastAddedArticleDbEntity.id,
      lastAddedArticleDbEntity.title,
      lastAddedArticleDbEntity.addedAt,
      lastAddedArticleDbEntity.leadImagePath
    )
  }

  override fun toModel(lastAddedArticleDbEntities: List<LastAddedArticleDbEntity>): List<LastAddedArticle> {
    return lastAddedArticleDbEntities.map(::toModel)
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
