package com.example.lenovo.reader.fragments.dashboard.interactors

import com.example.model.models.Category
import io.reactivex.Single
import javax.inject.Inject

class GetCategoriesInteractorImpl @Inject constructor() : GetCategoriesInteractor {
    override fun execute(): Single<List<Category>> {
        return Single.create{emitter ->
            emitter.onSuccess(mutableListOf(
                Category(1,"Design"),
                Category(2,"Programming"),
                Category(3,"Art"),
                Category(4,"Learning"),
                Category(5,"Test")
            ))}
    }
}