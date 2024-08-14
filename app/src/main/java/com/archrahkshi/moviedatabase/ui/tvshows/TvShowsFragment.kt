package com.archrahkshi.moviedatabase.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.TvShow
import com.archrahkshi.moviedatabase.data.TvShowsResponse
import com.archrahkshi.moviedatabase.databinding.TvShowsFragmentBinding
import com.archrahkshi.moviedatabase.network.ApiClient
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber.Forest.e

private const val KEY_ID = "id"

class TvShowsFragment : BaseFragment<TvShowsFragmentBinding>() {
    private val adapter by lazy<GroupAdapter<GroupieViewHolder>>(::GroupAdapter)

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

        ApiClient.getPopularTvShows().enqueue(
            object : Callback<TvShowsResponse> {
                override fun onResponse(
                    call: Call<TvShowsResponse>,
                    response: Response<TvShowsResponse>
                ) {
                    if (response.isSuccessful && response.body()?.results != null) {
                        binding.tvShowsRecyclerView.adapter = adapter.apply {
                            addAll(
                                response.body()!!.results!!.map {
                                    TvShowItem(it, ::openTvShowDetails)
                                }
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                    e(t)
                }
            }
        )
    }

    private fun openTvShowDetails(tvShow: TvShow) {
        findNavController().navigate(
            R.id.tv_show_details_fragment,
            Bundle().apply { putInt(KEY_ID, tvShow.id) },
            options
        )
    }
}
