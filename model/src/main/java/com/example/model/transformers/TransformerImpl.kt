package com.example.model.transformers

import com.example.model.entities.ArticleEntity
import com.example.model.entities.CategoryEntity
import com.example.model.models.Article
import com.example.model.models.Category
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 25.04.19
 */
class TransformerImpl @Inject constructor() : Transformer {

  override fun toArticleEntity(article: Article): ArticleEntity {
    return ArticleEntity(
      article.id,
      article.author,
      article.title,
      article.content,
      article.domain,
      article.url,
      article.excerpt,
      article.leadImageUrl,
      article.nextPageUrl,
      article.addedAt,
      article.isRead,
      article.isFavorite
    )
  }

  override fun toArticle(articleEntity: ArticleEntity): Article {
    return Article(
      articleEntity.id,
      articleEntity.author,
      articleEntity.title,
      articleEntity.content,
      articleEntity.domain,
      articleEntity.url,
      articleEntity.excerpt,
      articleEntity.leadImageUrl,
      articleEntity.nextPageUrl,
      articleEntity.addedAt,
      articleEntity.isRead,
      articleEntity.isFavorite
    )
  }

  override fun toCategoryEntity(category: Category): CategoryEntity {
    return CategoryEntity(category.id, category.name)
  }

  override fun toCategory(categoryEntity: CategoryEntity): Category {
    return Category(categoryEntity.id, categoryEntity.name)
  }

  override fun toLastAddedArticle(articleEntity: ArticleEntity): LastAddedArticle {
    return LastAddedArticle(articleEntity.id, articleEntity.title, articleEntity.addedAt, articleEntity.leadImageUrl)
  }

  override fun toFavoriteArticle(articleEntity: ArticleEntity): FavoriteArticle {
    return FavoriteArticle(articleEntity.id, articleEntity.title, articleEntity.excerpt, articleEntity.leadImageUrl)
  }
}