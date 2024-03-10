package com.kueski.marktest.ui.adapter

import android.annotation.SuppressLint
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

class MovieListAdapter(private val movieClickListener: MovieClickListener?) :
    RecyclerView.Adapter<ViewHolder>() {

    private var items = listOf<Movie>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.createViewHolder(parent, AdapterViewType.LIST)
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

}