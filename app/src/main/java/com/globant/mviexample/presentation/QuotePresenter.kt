package com.globant.mviexample.presentation

import com.globant.mviexample.domain.GetQuoteUseCase
import com.globant.mviexample.domain.model.QuoteViewState
import com.globant.mviexample.presentation.commons.BasePresenter
import io.reactivex.disposables.Disposable

class QuotePresenter(private val getQuoteUseCase: GetQuoteUseCase) :
        BasePresenter<QuoteView>() {

    lateinit var disposable: Disposable

    override fun bindIntents(view: QuoteView) {
        disposable = view
                .retrieveQuoteIntent()
                .switchMap {
                    getQuoteUseCase.execute()
                }
                .subscribe { state: QuoteViewState ->
                    view.render(state)
                }
    }

    override fun unbindIntents() {
        disposable.dispose()
    }

}
