package com.example.lenovo.reader.fragments.settings.interactors

import com.example.model.models.StorageInfo
import io.reactivex.Single

interface GetStorageInfoInteractor {
  fun execute(): Single<StorageInfo>
}