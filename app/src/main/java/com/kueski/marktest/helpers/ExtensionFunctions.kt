package com.kueski.marktest.helpers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Get now as string minus [days]
 * @param days number of days to be substracted form now
 * @return string representation of now minus [days]
 */
fun getNowMinus(days: Long = 7): String {
    return LocalDate.now().minusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}

/**
 * Round a float to [decimals] amount of decimals
 * @param decimals to round
 * @return rounded result
 */
fun Float.round(decimals: Int = 2): Float = "%.${decimals}f".format(this).toFloat()

/**
 * For testing purposes if needed
 */
fun <T> LiveData<T>.getOrAwaitValue(): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)
    @Suppress("UNCHECKED_CAST")
    return data as T
}