package com.kueski.marktest.ui.discovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.databinding.FragmentDiscoveryBinding
import com.kueski.marktest.ui.BaseMovieResultFragment
import com.kueski.marktest.ui.MovieClickListener
import com.kueski.marktest.ui.MovieDetailActivity

class MovieDiscoveryFragment :
    BaseMovieResultFragment<MovieDiscoveryViewModel, FragmentDiscoveryBinding>(),
    MovieClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDiscoveryViewModel::class.java)
        binding = FragmentDiscoveryBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
        viewModel.movieList.let {
            it.observe(viewLifecycleOwner) {
                listAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    override fun provideLoadingProgressBar(): ProgressBar? = binding?.progressBarExploreFragment
    override fun provideMovieClickListener(): MovieClickListener = this
    override fun provideRecyclerView(): RecyclerView? = binding?.moviesListRecyclerView
    override fun movieClicked(movie: Movie) {
        requireActivity().startActivity(MovieDetailActivity.getIntent(requireContext(), movie))
    }
}