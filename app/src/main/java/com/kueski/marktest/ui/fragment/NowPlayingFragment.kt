package com.kueski.marktest.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kueski.marktest.ui.base.BaseMovieResultFragment
import com.kueski.marktest.ui.viewmodel.NowPlayingViewModel

class NowPlayingFragment : BaseMovieResultFragment<NowPlayingViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(ViewModelProvider(this).get(NowPlayingViewModel::class.java))
        viewModel.movieList?.let {
            it.observe(viewLifecycleOwner) {
                movieListPagedAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    override fun sort() {
       viewModel.getNowPlayingMovies(sortBy.sortBy)
    }
}