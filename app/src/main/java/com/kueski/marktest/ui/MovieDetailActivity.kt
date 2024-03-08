package com.kueski.marktest.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kueski.marktest.business.model.Movie
import com.kueski.marktest.databinding.ActivityDetailBinding
import com.kueski.marktest.databinding.ActivityMainBinding
import com.kueski.marktest.ui.discovery.MovieDiscoveryViewModel
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity(), MovieClickListener {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

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
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent?.let {
            if (it.hasExtra(MOVIE)) {
                (it.getSerializableExtra(MOVIE) as Movie?)?.let {
                    binding.movie = it
                }
            }
        }
        binding.clickListener = this
    }

    override fun movieClicked(movie: Movie) {
        lifecycleScope.launch {
            binding.movie = viewModel.addFavourite(movie)
        }
    }

}