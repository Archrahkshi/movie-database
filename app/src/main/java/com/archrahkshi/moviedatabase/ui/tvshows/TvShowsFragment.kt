package com.archrahkshi.moviedatabase.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.TvShow
import com.archrahkshi.moviedatabase.data.TvShows
import com.archrahkshi.moviedatabase.databinding.TvShowsFragmentBinding
import com.archrahkshi.moviedatabase.network.apiClient
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.navOptions

const val KEY_TV_SHOW_ID = "tvShowId"

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        TvShowsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiClient.getPopularTvShows()
            .applySchedulers()
            .doOnSubscribe {
                binding.tvShows.isVisible = false
                binding.tvShowsProgressBar.isVisible = true
            }
            .doFinally {
                binding.tvShowsProgressBar.isVisible = false
                binding.tvShows.isVisible = true
            }
            .render(binding.tvShows) { shows ->
            addAll((shows as TvShows).results.map { TvShowItem(it, ::openTvShowDetails) })
        }
    }

    private fun openTvShowDetails(tvShow: TvShow) {
        findNavController().navigate(
            R.id.tv_show_details_fragment,
            bundleOf(KEY_TV_SHOW_ID to tvShow.id),
            navOptions
        )
    }
}
