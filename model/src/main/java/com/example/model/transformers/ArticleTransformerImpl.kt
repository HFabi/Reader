package com.example.model.transformers

import com.example.model.entities.db.ArticleDbEntity
import com.example.model.entities.web.ArticleWebEntity
import com.example.model.models.Article
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
class ArticleTransformerImpl @Inject constructor() : ArticleTransformer {

  override fun toModel(articleWebEntity: ArticleWebEntity): Article {
    return Article(
      articleWebEntity.id,
      articleWebEntity.author,
      articleWebEntity.title,
      articleWebEntity.content,
      articleWebEntity.domain,
      articleWebEntity.url,
      articleWebEntity.localPath,
      articleWebEntity.excerpt,
      articleWebEntity.leadImageUrl,
      articleWebEntity.leadImagePath,
      articleWebEntity.nextPageUrl,
      articleWebEntity.addedAt,
      false,
      false
    )
  }

  override fun toModel(articleDbEntity: ArticleDbEntity): Article {
    return Article(
      articleDbEntity.id,
      articleDbEntity.author,
      articleDbEntity.title,
      articleDbEntity.content,
      articleDbEntity.domain,
      articleDbEntity.url,
      articleDbEntity.localPath,
      articleDbEntity.excerpt,
      articleDbEntity.leadImageUrl,
      articleDbEntity.leadImagePath,
      articleDbEntity.nextPageUrl,
      articleDbEntity.addedAt,
      articleDbEntity.isRead,
      articleDbEntity.isFavorite
    )
  }

  override fun toDbEntity(article: Article): ArticleDbEntity {
    return ArticleDbEntity(
      article.id,
      article.author,
      article.title,
      article.content,
      article.domain,
      article.url,
      article.localPath,
      article.excerpt,
      article.leadImageUrl,
      article.leadImagePath,
      article.nextPageUrl,
      article.addedAt,
      article.isRead,
      article.isFavorite
    )
  }

  override fun toLastAddedArticle(articleDbEntity: ArticleDbEntity): LastAddedArticle {
    return LastAddedArticle(
      articleDbEntity.id,
      articleDbEntity.title,
      articleDbEntity.addedAt,
      articleDbEntity.leadImagePath
    )
  }

  override fun toFavoriteArticle(articleDbEntity: ArticleDbEntity): FavoriteArticle {
    return FavoriteArticle(
      articleDbEntity.id,
      articleDbEntity.title,
      articleDbEntity.excerpt,
      articleDbEntity.leadImagePath
    )
  }
}
