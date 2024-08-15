package com.archrahkshi.moviedatabase.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Movie
import com.archrahkshi.moviedatabase.databinding.FeedFragmentBinding
import com.archrahkshi.moviedatabase.network.apiClient
import com.archrahkshi.moviedatabase.ui.BaseFragmentWithSearch
import com.archrahkshi.moviedatabase.ui.feed.MovieList.NOW_PLAYING
import com.archrahkshi.moviedatabase.ui.feed.MovieList.POPULAR
import com.archrahkshi.moviedatabase.ui.feed.MovieList.UPCOMING
import com.archrahkshi.moviedatabase.ui.navOptions
import com.archrahkshi.moviedatabase.ui.then
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

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
        val title: String
        when (which) {
            NOW_PLAYING -> {
                title = getString(R.string.recommended)
                apiClient.getNowPlayingMovies()
            }
            POPULAR     -> {
                title = getString(R.string.popular)
                apiClient.getPopularMovies()
            }
            UPCOMING    -> {
                title = getString(R.string.upcoming)
                apiClient.getUpcomingMovies()
            }
        }.then {
            binding.moviesRecyclerView.adapter = adapter.apply {
                add(
                    MovieCardContainer(
                        title,
                        results.filter {
                            it.title != null && it.posterPath != null
                        }.map {
                            MovieItem(it, ::openMovieDetails)
                        }
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.clear()
    }

    private fun openMovieDetails(movie: Movie) {
        findNavController().navigate(
            R.id.movie_details_fragment,
            bundleOf(KEY_MOVIE_ID to movie.id),
            navOptions
        )
    }
}
