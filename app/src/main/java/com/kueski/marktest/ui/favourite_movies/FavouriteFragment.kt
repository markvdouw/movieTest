package com.kueski.marktest.ui.favourite_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kueski.marktest.databinding.FragmentFavouritesBinding

class FavouriteFragment : Fragment() {

    private var binding: FragmentFavouritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val favouriteMoviesViewModel =
            ViewModelProvider(this).get(FavouriteMoviesViewModel::class.java)

        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}