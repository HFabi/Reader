package com.example.lenovo.reader.fragments.article.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.datastores.UserPreferencesDataStore
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
class GetFontSizeIndexInteractorImpl @Inject constructor(): GetFontSizeIndexInteractor {

  @Inject
  lateinit var userPreferencesDataStore: UserPreferencesDataStore

  override fun execute(): Single<Int> {
    return userPreferencesDataStore.getFontSizeIndex()
  }

}