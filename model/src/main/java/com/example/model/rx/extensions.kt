package com.example.model.rx

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.schedule(scheduler: Scheduler = Schedulers.io()): Single<T> {
  return compose(SingleScheduler(scheduler))
}

fun <T> Observable<T>.schedule(scheduler: Scheduler = Schedulers.io()): Observable<T> {
  return compose(ObservableScheduler(scheduler))
}

fun <T> Flowable<T>.schedule(scheduler: Scheduler = Schedulers.io()): Flowable<T> {
  return compose(FlowableScheduler(scheduler))
}

fun Completable.schedule(scheduler: Scheduler = Schedulers.io()): Completable {
  return compose(CompletableScheduler(scheduler))
}

fun <T> Single<T>.bind(compositeDisposable: CompositeDisposable): Single<T> {
  return doOnSubscribe { disposable -> compositeDisposable.add(disposable) }
}

fun <T> Observable<T>.bind(compositeDisposable: CompositeDisposable): Observable<T> {
  return doOnSubscribe { disposable -> compositeDisposable.add(disposable) }
}

//fun <T> Flowable<T>.bind(compositeDisposable: CompositeDisposable): Flowable<T> {
//  return doOnSubscribe{disposable -> compositeDisposable.add(disposable)}
//}

fun Completable.bind(compositeDisposable: CompositeDisposable): Completable {
  return doOnSubscribe { disposable -> compositeDisposable.add(disposable) }
}