package com.kueski.marktest.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.ui.discovery.MovieListAdapter

abstract class BaseMovieResultFragment<VM : ViewModel, VDB : ViewDataBinding> : Fragment() {

    protected var binding: VDB? = null
    protected lateinit var viewModel: VM
    protected lateinit var listAdapter: MovieListAdapter

    protected open fun provideMovieClickListener(): MovieClickListener? = null
    protected open fun provideRecyclerView(): RecyclerView? = null
    protected open fun provideLoadingProgressBar(): ProgressBar? = null

    protected fun onViewCreated() {
        listAdapter = MovieListAdapter(provideMovieClickListener())
        provideRecyclerView()?.let { recycler ->
            recycler.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = listAdapter
            }

            listAdapter.addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.NotLoading -> {
                        if (loadState.source.refresh is LoadState.NotLoading) {
                            recycler.visibility = View.VISIBLE
                            if (!loadState.append.endOfPaginationReached && listAdapter.itemCount >= 0) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}