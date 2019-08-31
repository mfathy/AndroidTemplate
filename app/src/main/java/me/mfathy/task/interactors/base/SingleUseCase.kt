package me.mfathy.task.interactors.base

import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * SingleUseCase is an abstract class which provide a Single observable to emit required data or error.
 */
abstract class SingleUseCase<T, in Params> {

    abstract fun buildUseCaseSingle(params: Params): Single<T>

    fun execute(params: Params): Flowable<T> {
        return this.buildUseCaseSingle(params)
            .toFlowable()
    }
}