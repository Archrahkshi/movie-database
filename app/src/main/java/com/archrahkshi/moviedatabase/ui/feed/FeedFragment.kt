package com.archrahkshi.moviedatabase.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Movie
import com.archrahkshi.moviedatabase.data.MoviesResponse
import com.archrahkshi.moviedatabase.databinding.FeedFragmentBinding
import com.archrahkshi.moviedatabase.network.ApiClient
import com.archrahkshi.moviedatabase.ui.BaseFragmentWithSearch
import com.archrahkshi.moviedatabase.ui.feed.MovieList.NOW_PLAYING
import com.archrahkshi.moviedatabase.ui.feed.MovieList.POPULAR
import com.archrahkshi.moviedatabase.ui.feed.MovieList.UPCOMING
import com.archrahkshi.moviedatabase.ui.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber.Forest.e

const val KEY_MOVIE_ID = "movieId"

private enum class MovieList { NOW_PLAYING, POPULAR, UPCOMING }

class FeedFragment : BaseFragmentWithSearch<FeedFragmentBinding>() {
    private val adapter by lazy<GroupAdapter<GroupieViewHolder>>(::GroupAdapter)

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FeedFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderMovies(NOW_PLAYING)
        renderMovies(POPULAR)
        renderMovies(UPCOMING)
    }

    private fun renderMovies(which: MovieList) {
        @StringRes val title: Int
        when (which) {
            NOW_PLAYING -> {
                title = R.string.recommended
                ApiClient.getNowPlayingMovies()
            }
            POPULAR     -> {
                title = R.string.popular
                ApiClient.getPopularMovies()
            }
            UPCOMING    -> {
                title = R.string.upcoming
                ApiClient.getUpcomingMovies()
            }
        }.enqueue(
            object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        binding.moviesRecyclerView.adapter = adapter.apply {
                            add(
                                MovieCardContainer(
                                    title,
                                    response.body()!!.results.filter {
                                        it.title != null && it.backdropPath != null
                                    }.map {
                                        MovieItem(it, ::openMovieDetails)
                                    }
                                )
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    e(t)
                }
            }
        )
    }

    private fun openMovieDetails(movie: Movie) {
        findNavController().navigate(
            R.id.movie_details_fragment,
            bundleOf(KEY_MOVIE_ID to movie.id),
            navOptions
        )
    }
}
