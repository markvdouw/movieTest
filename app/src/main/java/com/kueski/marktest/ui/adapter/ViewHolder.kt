package com.kueski.marktest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.kueski.marktest.BuildConfig
import com.kueski.marktest.R
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.databinding.MovieGridItemBinding
import com.kueski.marktest.databinding.MovieListItemBinding

class ViewHolder private constructor(
    private val binding: ViewBinding,
    private val type: AdapterViewType
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Movie?, movieClickListener: MovieClickListener?) {
        item?.let {
            when (type) {
                AdapterViewType.LIST -> {
                    bindList(binding as MovieListItemBinding, it, movieClickListener)
                }

                AdapterViewType.GRID -> {
                    bindGrid(binding as MovieGridItemBinding, it, movieClickListener)
                }
            }
        }
    }

    private fun bindList(
        binding: MovieListItemBinding,
        movie: Movie,
        clickListener: MovieClickListener?
    ) {
        binding.movie = movie
        Glide.with(binding.root.context)
            .load("${BuildConfig.BASE_IMAGE_URL}${movie!!.poster}")
            .placeholder(R.drawable.pic_placeholder).into(binding.movieImageview)
        clickListener?.let { binding.clickListener = it }
    }

    private fun bindGrid(
        binding: MovieGridItemBinding,
        movie: Movie,
        clickListener: MovieClickListener?
    ) {
        binding.movie = movie
        Glide.with(binding.root.context)
            .load("${BuildConfig.BASE_IMAGE_URL}${movie!!.poster}")
            .placeholder(R.drawable.pic_placeholder).into(binding.movieImageview)
        clickListener?.let { binding.clickListener = it }
    }

    companion object {
        fun createViewHolder(parent: ViewGroup, type: AdapterViewType): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = if (type == AdapterViewType.LIST) {
                MovieListItemBinding.inflate(layoutInflater, parent, false)
            } else {
                MovieGridItemBinding.inflate(layoutInflater, parent, false)
            }
            return ViewHolder(binding, type)
        }
    }
}