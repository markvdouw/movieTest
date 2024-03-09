package com.kueski.marktest.helpers

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getNowMinus(days: Long = 7): String {
    return LocalDate.now().minusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}

fun Float.round(decimals: Int = 2): Float = "%.${decimals}f".format(this).toFloat()
