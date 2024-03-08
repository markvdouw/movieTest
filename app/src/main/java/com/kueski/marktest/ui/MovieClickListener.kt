package com.kueski.marktest.ui

import com.kueski.marktest.business.model.Movie

interface MovieClickListener {
    fun movieClicked(movie: Movie)
}