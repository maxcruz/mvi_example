package com.globant.mviexample.domain

import io.reactivex.Observable
import io.reactivex.Scheduler


/**
 * Represents an execution unit for different use cases.
 *
 * @param <T> type returned by the use case
</T> */
abstract class UseCase<T>(private val subscribeOn: Scheduler, private val observeOn: Scheduler) {

    /**
     * Abstract method to implement the use case.
     *
     * @return observable that emits the result.
     */
    protected abstract fun buildUseCaseObservable(): Observable<T>

    /**
     * Method to execute the use case in a different thread
     *
     * @return observable that runs in different thread and emits the result
     */
    fun execute(): Observable<T> {
        return this.buildUseCaseObservable().subscribeOn(subscribeOn).observeOn(observeOn)
    }
}