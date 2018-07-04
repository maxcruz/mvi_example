package com.globant.mviexample.domain

import com.globant.mviexample.data.Repository
import com.globant.mviexample.domain.model.QuoteViewState
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetQuoteUseCase(private val repository: Repository,
                      subscribeOn: Scheduler,
                      observeOn: Scheduler) : UseCase<QuoteViewState>(subscribeOn, observeOn) {

    override fun buildUseCaseObservable(): Observable<QuoteViewState> {
        return repository.retrieveMessage()
                .map<QuoteViewState> { QuoteViewState.DataState(it) }
                .startWith(QuoteViewState.LoadingState())
                .onErrorReturn { QuoteViewState.ErrorState(it) }
    }

}