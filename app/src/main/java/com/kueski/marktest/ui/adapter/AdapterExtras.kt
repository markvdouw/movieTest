package com.kueski.marktest.ui.adapter

enum class AdapterViewType(val id: Int) {
    GRID(2), LIST(2)
}

enum class AdapterSortingType(val sortBy: String) {
    DATE("release_date.asc"), NAME("title.asc")
}