package com.example.lenovo.reader.annotations

import java.lang.annotation.*

@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
annotation class Layout(val value: Int)
