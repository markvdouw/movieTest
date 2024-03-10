package com.kueski.marktest.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kueski.marktest.ui.adapter.MovieListAdapter
import com.kueski.marktest.ui.base.BaseMovieResultFragment
import com.kueski.marktest.ui.viewmodel.FavouriteMoviesViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FavouriteFragment : BaseMovieResultFragment<FavouriteMoviesViewModel>() {

    private val movieListAdapter = MovieListAdapter(this)
    private val viewModel: FavouriteMoviesViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(setAdapter = false)
        binding?.sort?.visibility = View.GONE
        binding?.view?.visibility = View.GONE
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
        update(false)
    }

    override fun update(withSorting: Boolean) {
        lifecycleScope.launch {
            viewModel.getFavouriteMovies()
            binding?.progress?.visibility = View.VISIBLE
            binding?.moviesListRecyclerView?.visibility = View.GONE
        }
    }


}