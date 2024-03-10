package com.kueski.marktest.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.kueski.marktest.ui.base.BaseMovieResultFragment
import com.kueski.marktest.ui.viewmodel.NowPlayingViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class NowPlayingFragment : BaseMovieResultFragment<NowPlayingViewModel>() {

    private val viewModel: NowPlayingViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
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
            viewModel.getNowPlayingMovies(sortBy = sortBy.sortBy)
        }
    }
}