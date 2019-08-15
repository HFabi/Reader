package com.example.lenovo.reader.fragments.settings.interactors

import com.example.model.controllers.StorageController
import com.example.model.models.StorageInfo
import io.reactivex.Single
import javax.inject.Inject

class GetStorageInfoInteractorImpl @Inject constructor() : GetStorageInfoInteractor {

  @Inject
  lateinit var storageController: StorageController

  override fun execute(): Single<StorageInfo> {
    return storageController.provideStorageInfo()
  }

}