package com.kueski.marktest.helpers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getNowMinus(days: Long = 7): String {
    return LocalDate.now().minusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}

fun Float.round(decimals: Int = 2): Float = "%.${decimals}f".format(this).toFloat()

fun <T> LiveData<T>.toMutableLiveData(): MutableLiveData<T> {
    val mediatorLiveData = MediatorLiveData<T>()
    mediatorLiveData.addSource(this) {
        mediatorLiveData.value = it
    }
    return mediatorLiveData
}