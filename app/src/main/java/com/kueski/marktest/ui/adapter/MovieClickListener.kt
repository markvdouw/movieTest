package com.kueski.marktest.ui.adapter

import com.kueski.marktest.business.model.Movie

interface MovieClickListener {
    fun movieClicked(movie: Movie)
    fun toggleView()
    fun update(withSorting : Boolean)
}