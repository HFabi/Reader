package com.example.model.transformers

import com.example.model.entities.ArticleEntity
import com.example.model.entities.CategoryEntity
import com.example.model.models.Article
import com.example.model.models.Category
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle

/**
 * @author appcom interactive GmbH on 25.04.19
 */
interface Transformer {

  fun toArticleEntity(article: Article): ArticleEntity

  fun toArticle(articleEntity: ArticleEntity): Article

  fun toCategoryEntity(category: Category): CategoryEntity

  fun toCategory(categoryEntity: CategoryEntity): Category

  fun toLastAddedArticle(articleEntity: ArticleEntity): LastAddedArticle

  fun toFavoriteArticle(articleEntity: ArticleEntity): FavoriteArticle
}