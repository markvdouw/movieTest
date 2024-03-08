package com.kueski.marktest.helpers

import java.text.SimpleDateFormat
import java.util.Date

fun Long.formatDate() = SimpleDateFormat("yyyy-MM-dd").format(Date(this))

fun Boolean.favouriteToString() = if(this) "FAVOURITE: TRUE" else "FAVOURITE: FALSE"