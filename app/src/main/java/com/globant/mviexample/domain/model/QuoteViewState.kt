package com.globant.mviexample.domain.model

sealed class QuoteViewState {

    class LoadingState : QuoteViewState()

    data class DataState(val quote: String) : QuoteViewState()
    data class ErrorState(val error: Throwable) : QuoteViewState()

}
