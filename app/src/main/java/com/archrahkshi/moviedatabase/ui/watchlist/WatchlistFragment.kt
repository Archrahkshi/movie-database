package com.archrahkshi.moviedatabase.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.archrahkshi.moviedatabase.databinding.WatchlistFragmentBinding
import com.archrahkshi.moviedatabase.ui.BaseFragment

class WatchlistFragment : BaseFragment<WatchlistFragmentBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        WatchlistFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.watchlist.layoutManager = GridLayoutManager(context, 4)
    }

    companion object {
        @JvmStatic
        fun newInstance() = WatchlistFragment()
    }
}
