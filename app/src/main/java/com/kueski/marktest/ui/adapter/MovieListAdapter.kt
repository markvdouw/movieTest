package com.kueski.marktest.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.databinding.MovieListItemBinding

class MovieListAdapter(private val movieClickListener: MovieClickListener?) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var items = listOf<Movie>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], movieClickListener)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Movie>) {
        this.items = data
        notifyDataSetChanged()
    }

    class ViewHolder private constructor(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie?, movieClickListener: MovieClickListener?) {
            if (item != null) {
                binding.movie = item
            }
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
}