package com.kueski.marktest.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kueski.marktest.ui.base.BaseMovieResultFragment
import com.kueski.marktest.ui.adapter.MovieListAdapter
import com.kueski.marktest.ui.viewmodel.FavouriteMoviesViewModel
import kotlinx.coroutines.launch

class FavouriteFragment : BaseMovieResultFragment<FavouriteMoviesViewModel>() {

    val movieListAdapter = MovieListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(
            ViewModelProvider(this).get(FavouriteMoviesViewModel::class.java),
            setAdapter = false
        )
        provideRecyclerView()?.let { recycler ->
            recycler.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieListAdapter
            }
            viewModel.movieList.let {
                it.observe(viewLifecycleOwner) {
                    movieListAdapter.setData(it)
                    binding?.progress?.visibility = View.GONE
                    binding?.moviesListRecyclerView?.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.getFavouriteMovies()
            binding?.progress?.visibility = View.VISIBLE
            binding?.moviesListRecyclerView?.visibility = View.GONE
        }
    }

    override fun sort() {

    }
}