package com.kueski.marktest.ui.adapter

enum class AdapterViewType {
    GRID, LIST
}

enum class AdapterSortingType(val sortBy : String){
    DATE("release_date.lte"), NAME("title")
}