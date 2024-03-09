package com.kueski.marktest.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kueski.marktest.ui.base.BaseMovieResultFragment
import com.kueski.marktest.ui.viewmodel.MovieDiscoveryViewModel
import kotlinx.coroutines.launch

class MovieDiscoveryFragment : BaseMovieResultFragment<MovieDiscoveryViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(ViewModelProvider(this).get(MovieDiscoveryViewModel::class.java))
        viewModel.movieList?.let {
            it.observe(viewLifecycleOwner) {
                movieListPagedAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    override fun sort() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding?.progress?.visibility = View.VISIBLE
            binding?.moviesListRecyclerView?.visibility = View.GONE
            changeSortingType()
            viewModel.movieList?.let {
                it.observe(viewLifecycleOwner) {
                    movieListPagedAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                    binding?.progress?.visibility = View.GONE
                    binding?.moviesListRecyclerView?.visibility = View.VISIBLE
                }
            }
            viewModel.getMoviesDiscovery(sortBy = sortBy.sortBy)
        }
    }
}