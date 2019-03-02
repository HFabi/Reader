package com.example.lenovo.reader

import com.example.lenovo.reader.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class ReaderApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

    private fun setUpTimber() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element) + ", " +
                        element.className + ", " +
                        element.methodName + ", " +
                        element.lineNumber
            }
        })
    }
}
