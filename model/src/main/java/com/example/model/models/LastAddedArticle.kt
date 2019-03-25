package com.example.model.models

import java.util.*

data class LastAddedArticle(
    var id: Int,
    var title: String,
    var addedAt: Date,
    var imagePath: String
)