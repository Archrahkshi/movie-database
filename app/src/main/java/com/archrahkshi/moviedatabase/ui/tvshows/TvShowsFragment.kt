package com.archrahkshi.moviedatabase.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.archrahkshi.moviedatabase.data.MockRepository
import com.archrahkshi.moviedatabase.data.TvShow
import com.archrahkshi.moviedatabase.databinding.TvShowsFragmentBinding
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>() {

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        TvShowsFragmentBinding.inflate(inflater, container, false)

    private val adapter by lazy<GroupAdapter<GroupieViewHolder>>(::GroupAdapter)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvShowsRecyclerView.adapter = adapter.apply {
            add(
                TvShowItem(
                    TvShow(
                        "A TV show with a very very very very long title",
                        10f,
                        "https://i.ytimg.com/vi/mkD5Nsr4vfc/maxresdefault.jpg"
                    )
                ) {}
            )
            addAll(MockRepository.getTvShows().map { TvShowItem(it) {} })
        }
    }
}
