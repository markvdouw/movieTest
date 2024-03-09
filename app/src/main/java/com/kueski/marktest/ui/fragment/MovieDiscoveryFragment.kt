package com.kueski.marktest.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.kueski.marktest.ui.base.BaseMovieResultFragment
import com.kueski.marktest.ui.viewmodel.MovieDiscoveryViewModel

class MovieDiscoveryFragment : BaseMovieResultFragment<MovieDiscoveryViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(ViewModelProvider(this).get(MovieDiscoveryViewModel::class.java))
        viewModel.movieList.let {
            it.observe(viewLifecycleOwner) {
                movieListPagedAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }
}