package com.archrahkshi.moviedatabase.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.network.apiClient
import com.archrahkshi.moviedatabase.data.vo.TvShows
import com.archrahkshi.moviedatabase.databinding.TvShowsFragmentBinding
import com.archrahkshi.moviedatabase.ui.BaseFragment

const val KEY_TV_SHOW_ID = "tvShowId"

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        TvShowsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiClient.getPopularTvShows()
            .applySchedulers()
            .withProgressBar(binding.tvShows)
            .render(binding.tvShows) { tvShows ->
                addAll(
                    (tvShows as TvShows).results.map { tvShow ->
                        TvShowItem(tvShow) { openTvShowDetails(tvShow.id) }
                    }
                )
            }
    }

    private fun openTvShowDetails(tvShowId: Int) {
        findNavController().navigate(
            R.id.tv_show_details_fragment,
            bundleOf(KEY_TV_SHOW_ID to tvShowId)
        )
    }
}
