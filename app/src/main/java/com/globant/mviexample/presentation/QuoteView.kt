package com.globant.mviexample.presentation

import com.globant.mviexample.domain.model.QuoteViewState
import com.globant.mviexample.presentation.commons.BaseView
import io.reactivex.Observable

interface QuoteView: BaseView {

    /**
     * Quote request command
     */
    fun retrieveQuoteIntent(): Observable<Unit>


    /**
     * Render the staet in the UI
     */
    fun render(state: QuoteViewState)

}