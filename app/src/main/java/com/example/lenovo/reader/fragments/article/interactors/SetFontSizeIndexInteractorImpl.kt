package com.example.lenovo.reader.fragments.article.interactors

import com.example.model.datastores.ArticlesDataStore
import com.example.model.datastores.UserPreferencesDataStore
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-06-06
 */
class SetFontSizeIndexInteractorImpl @Inject constructor() : SetFontSizeIndexInteractor {

  @Inject
  lateinit var userPreferencesDataStore: UserPreferencesDataStore

  override fun execute(value: Int): Completable {
    return userPreferencesDataStore.setFontSizeIndex(value)
  }

}