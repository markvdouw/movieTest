package com.kueski.marktest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kueski.marktest.R
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.databinding.FragmentMovieResultsBinding
import com.kueski.marktest.ui.activity.MovieDetailActivity
import com.kueski.marktest.ui.adapter.AdapterSortingType
import com.kueski.marktest.ui.adapter.AdapterViewType
import com.kueski.marktest.ui.adapter.MovieClickListener
import com.kueski.marktest.ui.adapter.MoviePagedListAdapter


abstract class BaseMovieResultFragment<VM : ViewModel> : Fragment(), MovieClickListener {

    protected var binding: FragmentMovieResultsBinding? = null
    protected lateinit var movieListPagedAdapter: MoviePagedListAdapter
    private var itemViewType = AdapterViewType.LIST
    var sortBy = AdapterSortingType.NAME
    protected open fun provideRecyclerView(): RecyclerView? = binding?.moviesListRecyclerView
    protected open fun provideLoadingProgressBar(): ProgressBar? =
        binding?.progress

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMovieResultsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    protected fun onViewCreated(setAdapter: Boolean = true) {
        binding?.clickListener = this
        if (setAdapter) {
            movieListPagedAdapter = MoviePagedListAdapter(this)
            provideRecyclerView()?.let { recycler ->
                recycler.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieListPagedAdapter
                }

                movieListPagedAdapter.addLoadStateListener { loadState ->
                    when (loadState.source.refresh) {
                        is LoadState.NotLoading -> {
                            if (loadState.source.refresh is LoadState.NotLoading) {
                                recycler.visibility = View.VISIBLE
                                if (!loadState.append.endOfPaginationReached && movieListPagedAdapter.itemCount >= 0) {
                                    provideLoadingProgressBar()?.let { it.visibility = View.GONE }
                                }
                            }
                        }

                        is LoadState.Loading -> {
                            provideLoadingProgressBar()?.let { it.visibility = View.VISIBLE }
                            recycler.visibility = View.GONE
                        }

                        is LoadState.Error -> {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun movieClicked(movie: Movie) {
        requireActivity().startActivity(MovieDetailActivity.getIntent(requireContext(), movie))
    }

    private fun changeViewType() {
        itemViewType = if (itemViewType == AdapterViewType.LIST) {
            AdapterViewType.GRID
        } else {
            AdapterViewType.LIST
        }
    }

    protected fun changeSortingType() {
        sortBy = if (sortBy == AdapterSortingType.NAME) {
            AdapterSortingType.DATE
        } else {
            AdapterSortingType.NAME
        }
    }

    override fun toggleView() {
        changeViewType()
        provideRecyclerView()?.let {
            it.layoutManager = if (itemViewType == AdapterViewType.GRID) {
                GridLayoutManager(context, 2)
            } else {
                LinearLayoutManager(context)
            }
            (it.adapter as MoviePagedListAdapter).setViewType(itemViewType)
            val imageResource =
                if (itemViewType == AdapterViewType.GRID) R.drawable.list else R.drawable.grid
            binding?.view?.setImageResource(imageResource)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}