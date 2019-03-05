package com.example.model.rx

import io.reactivex.Completable
import io.reactivex.CompletableSource
import io.reactivex.CompletableTransformer
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import org.reactivestreams.Publisher

class SingleScheduler<T>(var scheduler: Scheduler) : SingleTransformer<T, T> {
  override fun apply(upstream: Single<T>): SingleSource<T> {
    return upstream.subscribeOn(scheduler)
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(scheduler)
  }
}

class ObservableScheduler<T>(var scheduler: Scheduler) : ObservableTransformer<T, T> {
  override fun apply(upstream: Observable<T>): ObservableSource<T> {
    return upstream.subscribeOn(scheduler)
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(
            scheduler
        )//To change body of created functions use File | Settings | File Templates.
  }
}

class FlowableScheduler<T>(var scheduler: Scheduler) : FlowableTransformer<T, T> {
  override fun apply(upstream: Flowable<T>): Publisher<T> {
    return upstream.subscribeOn(scheduler)
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(scheduler)
  }
}

class CompletableScheduler(var scheduler: Scheduler) : CompletableTransformer {
  override fun apply(upstream: Completable): CompletableSource {
    return upstream.subscribeOn(scheduler)
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(scheduler)
  }
}