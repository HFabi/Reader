package com.example.model.models

class StorageInfo(
  val totalSpace: Long,
  val usableSpace: Long, //more realistic than getFreeSpace
  val hasExternalStorage: Boolean
)
