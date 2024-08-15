package com.archrahkshi.moviedatabase.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import com.archrahkshi.moviedatabase.databinding.TvShowsFragmentBinding
import com.archrahkshi.moviedatabase.ui.BaseFragment

class TvShowDetailsFragment : BaseFragment<TvShowsFragmentBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        TvShowsFragmentBinding.inflate(inflater, container, false)
}
