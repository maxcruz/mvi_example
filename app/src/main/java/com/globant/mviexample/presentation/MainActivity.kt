package com.globant.mviexample.presentation

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.globant.mviexample.R
import com.globant.mviexample.data.Repository
import com.globant.mviexample.domain.GetQuoteUseCase
import com.globant.mviexample.domain.model.QuoteViewState
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), QuoteView {

    private lateinit var presenter: QuotePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = QuotePresenter(GetQuoteUseCase(Repository(this), Schedulers.io(), AndroidSchedulers.mainThread()))
    }

    override fun onResume() {
        super.onResume()
        presenter.bindIntents(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unbindIntents()
    }

    override fun retrieveQuoteIntent(): Observable<Unit> = button.clicks()

    override fun render(state: QuoteViewState) {
        when (state) {
            is QuoteViewState.LoadingState -> renderLoadingState()
            is QuoteViewState.ErrorState -> renderErrorState(state.error.message)
            is QuoteViewState.DataState -> renderDataState(state.quote)
        }
    }

    private fun renderLoadingState() {
        progress.visibility = View.VISIBLE
        textView.visibility = View.GONE
    }

    private fun renderDataState(message: String) {
        progress.visibility = View.GONE
        textView.apply {
            visibility = View.VISIBLE
            text = message
        }
    }

    private fun renderErrorState(error: String?) {
        progress.visibility = View.GONE
        textView.visibility = View.GONE
        Snackbar.make(layout, "Error: $error", Snackbar.LENGTH_LONG).show()
    }

}
