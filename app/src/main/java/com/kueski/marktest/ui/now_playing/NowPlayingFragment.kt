package com.kueski.marktest.ui.now_playing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kueski.marktest.databinding.FragmentNowPlayingBinding

class NowPlayingFragment : Fragment() {

    private var binding: FragmentNowPlayingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val nowPlayingViewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)

        binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textDashboard
//        nowPlayingViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}