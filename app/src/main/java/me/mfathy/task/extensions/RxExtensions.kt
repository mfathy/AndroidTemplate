package me.mfathy.task.extensions

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 */
fun <T : Any> Flowable<T>.subscribeAndObserve(): Flowable<T> =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Flowable<T>.observeMainThread(): Flowable<T> =
    observeOn(AndroidSchedulers.mainThread())

fun Completable.subscribeAndObserve(): Completable =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
