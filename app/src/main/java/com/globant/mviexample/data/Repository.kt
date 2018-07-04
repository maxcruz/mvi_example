package com.globant.mviexample.data

import android.content.Context
import com.globant.mviexample.R
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Repository to manipulate data
 */
class Repository(private val context: Context) {

    companion object {
        private const val DELAY = 3L
    }

    /**
     *
     */
    fun retrieveMessage(): Observable<String> = Observable
            .just(getRandomMessage())
            .delay(DELAY, TimeUnit.SECONDS)

    /**
     * Return a random message from string array resource
     *
     * @return Quote message
     */
    private fun getRandomMessage(): String {
        val messages = context.resources.getStringArray(R.array.quotes)
        return messages[Random().nextInt(messages.size)]
    }

}