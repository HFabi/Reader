package com.example.model.datasources.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.entities.db.ArticleCategoryDbEntity
import com.example.model.entities.db.ArticleDbEntity
import com.example.model.entities.db.CategoryDbEntity
import com.example.model.entities.db.ExcerptArticleDbEntity
import com.example.model.models.Category
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 25.04.19
 */
@Dao
interface ArticleCategoryDao {

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addArticleCategory(articleCategoryDbEntity: ArticleCategoryDbEntity): Completable

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun addAllArticleCategories(articleCategoryDbEntity: List<ArticleCategoryDbEntity>): Completable

  @Delete
  fun remove(articleCategoryDbEntity: ArticleCategoryDbEntity): Completable

  @Query(
    "SELECT id, title, addedAt, leadImagePath FROM articles LIMIT (:count) OFFSET (:skip)"
  )
  fun getExcerptArticles(count: Int, skip: Int): Single<List<ExcerptArticleDbEntity>>

  @Query(
    "SELECT DISTINCT articles.id, title, addedAt, leadImagePath FROM articles JOIN articles_categories ON articles.id=articles_categories.articleId WHERE categoryId IN (:categoryIds) LIMIT (:count) OFFSET (:skip)"
  )
  fun getExcerptArticles(count: Int, skip: Int, categoryIds: List<Long>): Single<List<ExcerptArticleDbEntity>>

//  @Query(
//    "SELECT articles.id, title, addedAt, leadImagePath FROM articles WHERE title LIKE (:searchString) LIMIT (:count) OFFSET (:skip)"
//  )
//  fun getExcerptArticles(count: Int, skip: Int, searchString: String): Single<List<ExcerptArticleDbEntity>>

  @Query(
    "SELECT categories.id, categories.name FROM articles_categories JOIN categories ON categories.id = articles_categories.categoryId WHERE articles_categories.articleId=(:articleId)"
  )
  fun getCategoriesForArticle(articleId: Long): Single<List<CategoryDbEntity>>


}



//  @Query(
//    "SELECT articles.id, title, addedAt, leadImagePath FROM articles LEFT OUTER JOIN articles_categories ON articles.id=articles_categories.articleId LEFT OUTER JOIN categories ON articles_categories.categoryId = categories.id WHERE categoryId IN (:categoryIds) LIMIT (:count) OFFSET (:skip)"
//  )
//  fun getExcerptArticles(count: Int, skip: Int, categoryIds: List<Long>): Single<List<ExcerptArticleDbEntity>>


//  @Query(
//    "SELECT articles.id, title, addedAt, leadImagePath FROM articles LEFT OUTER JOIN articles_categories ON articles.id=articles_categories.articleId LEFT OUTER JOIN categories ON articles_categories.categoryId = categories.id LIMIT (:count) OFFSET (:skip)"
//  )
//  fun getExcerptArticles(count: Int, skip: Int): Single<List<ExcerptArticleDbEntity>>

// DESC klappt nicht
// Query klappt nicht
//  @Query(
//    "SELECT id, title, addedAt, leadImagePath FROM articles INNER JOIN articles_categories ON articles.id=articles_categories.articleId INNER JOIN categories ON articles_categories.categoryId = articles_categories.categoryId WHERE articles_categories.categoryId=:repoId DESC LIMIT (:count) OFFSET (:skip)"
//  )
//  fun getExcerptArticle(count: Int, skip: Int): Single<List<ArticleDbEntity>>