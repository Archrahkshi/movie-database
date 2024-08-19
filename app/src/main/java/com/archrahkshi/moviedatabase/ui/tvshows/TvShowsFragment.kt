package com.archrahkshi.moviedatabase.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.TvShow
import com.archrahkshi.moviedatabase.databinding.TvShowsFragmentBinding
import com.archrahkshi.moviedatabase.network.apiClient
import com.archrahkshi.moviedatabase.ui.BaseFragment

const val KEY_TV_SHOW_ID = "tvShowId"

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>() {
    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        TvShowsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiClient.getPopularTvShows().render(binding.tvShowsRecyclerView) { adapter ->
            adapter.addAll(
                results!!.filter {
                    it.name != null && it.posterPath != null
                }.map { TvShowItem(it, ::openTvShowDetails) }
            )
        }
    }

    private fun openTvShowDetails(tvShow: TvShow) {
        findNavController().navigate(
            R.id.tv_show_details_fragment,
            bundleOf(KEY_TV_SHOW_ID to tvShow.id),
            options
        )
    }
}
