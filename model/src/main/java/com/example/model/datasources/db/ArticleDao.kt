package com.example.model.datasources.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.entities.db.ArticleDbEntity
import com.example.model.entities.db.ExcerptArticleDbEntity
import com.example.model.entities.db.LastAddedArticleDbEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 04.04.19
 */
@Dao
interface ArticleDao {

  @Query("SELECT * FROM articles WHERE id = (:id)")
  fun getArticleById(id: Long): Single<ArticleDbEntity>

  @Query("SELECT id, title, addedAt, leadImagePath, color FROM articles ORDER BY addedAt DESC LIMIT 8")
  fun getLastAddedArticles(): Single<List<LastAddedArticleDbEntity>>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun addArticle(articleDb: ArticleDbEntity): Completable

//  @Query(
//    "SELECT articles.id, title, addedAt, leadImagePath FROM articles WHERE title LIKE (:searchString) LIMIT (:count) OFFSET (:skip)"
//  )
//  fun getExcerptArticles(count: Int, skip: Int, searchString: String): Single<List<ExcerptArticleDbEntity>>

  @Query("SELECT articles.id, articles.title, articles.addedAt, articles.leadImagePath, articles.color  FROM articles JOIN articles_fts ON (articles.id = articles_fts.docid) WHERE articles_fts MATCH :searchString LIMIT (:count) OFFSET (:skip)")
  fun getExcerptArticles(
    count: Int,
    skip: Int,
    searchString: String
  ): Single<List<ExcerptArticleDbEntity>>

  // FTS 5
  // SELECT * FROM movies MATCH 'pul*' ORDER BY rank;
  //
  // SELECT * FROM movies WHERE movies MATCH 'pul*'
  // AND rank MATCH 'bm25(10.0, 1.0)' ORDER BY rank

  // FTS 4
  //

  // @Query("SELECT * FROM articles WHERE isFavorite = 1 ORDER BY addedAt LIMIT 8")
  // fun getFavoriteArticles(): Single<List<ArticleDbEntity>>
  // LIMIT <skip>, <count>
  // LIMIT <count> OFFSET <skip>

  @Query("DELETE FROM articles WHERE id = :articleId")
  fun deleteArticle(articleId: Long): Single<Int>

}

