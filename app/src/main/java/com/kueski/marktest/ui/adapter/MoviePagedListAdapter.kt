package com.kueski.marktest.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.databinding.MovieListItemBinding

class MoviePagedListAdapter(private val movieClickListener: MovieClickListener?) :
    PagingDataAdapter<Movie, MoviePagedListAdapter.ViewHolder>(MovieDiffCallback()) {

    private var viewType: AdapterViewType = AdapterViewType.LIST

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, movieClickListener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.createViewHolder(parent)
    }

    fun setViewType(type: AdapterViewType) {
        this.viewType = type
        refresh()
    }

    class ViewHolder private constructor(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageBaseUrl: String = "https://image.tmdb.org/t/p/w500"
        fun bind(item: Movie?, movieClickListener: MovieClickListener?) {
            if (item != null) {
                binding.movie = item
            }
            Glide.with(binding.root.context).load("$imageBaseUrl${item!!.poster}")
                .into(binding.imageView);
            movieClickListener?.let { binding.clickListener = it }
        }

        companion object {
            fun createViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}