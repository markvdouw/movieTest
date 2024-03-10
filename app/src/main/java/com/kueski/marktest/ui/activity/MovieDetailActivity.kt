package com.kueski.marktest.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.kueski.marktest.BuildConfig
import com.kueski.marktest.R
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.databinding.ActivityDetailBinding
import com.kueski.marktest.ui.adapter.MovieClickListener
import com.kueski.marktest.ui.viewmodel.DetailViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MovieDetailActivity : AppCompatActivity(), MovieClickListener {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by inject()

    companion object {
        const val MOVIE = "movie"
        fun getIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE, movie)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent?.let {
            if (it.hasExtra(MOVIE)) {
                (it.getSerializableExtra(MOVIE) as Movie?)?.let {
                    binding.movie = it
                    Glide.with(binding.root.context).load("${BuildConfig.BASE_IMAGE_URL}${it.poster}")
                        .placeholder(R.drawable.pic_placeholder).into(binding.image)
                    lifecycleScope.launch {
                        viewModel.setFavouriteMovieState(it.id!!)
                    }
                }
            }
        }
        binding.clickListener = this
        viewModel.isFavouriteMovie.let {
            it.observe(this) {
                setFavouriteButtonText(isFavourite = it)
            }
        }
    }

    private fun setFavouriteButtonText(isFavourite: Boolean?) {
        with(binding.markFavourite) {
            isFavourite?.let {
                text = if (it) "DELETE" else "ADD"
                visibility = View.VISIBLE
            } ?: run {
                visibility = View.GONE
            }
        }
    }

    override fun movieClicked(movie: Movie) {
        lifecycleScope.launch {
            viewModel.onFavouriteButtonClicked(movie)
        }
    }

    override fun toggleView() {
    }

    override fun update(withSorting: Boolean) {
    }

}