package com.archrahkshi.moviedatabase.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.archrahkshi.moviedatabase.BuildConfig.BACKDROP_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.MovieCredits
import com.archrahkshi.moviedatabase.data.MovieDetails
import com.archrahkshi.moviedatabase.databinding.MovieDetailsFragmentBinding
import com.archrahkshi.moviedatabase.network.apiClient
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.feed.KEY_MOVIE_ID
import com.archrahkshi.moviedatabase.ui.loadFromPath

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MovieDetailsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = requireArguments().getInt(KEY_MOVIE_ID)
        apiClient.getMovieDetails(movieId)
            .applySchedulers()
            .withProgressBar(
                binding.movieDetailsProgressBar,
                binding.movieBackdrop,
                binding.movieDetails
            )
            .subscribeAndDispose {
                with(toViewObject() as MovieDetails) {
                    with(binding) {
                        movieBackdrop.loadFromPath(background, BACKDROP_WIDTH)
                        movieTitleDetailed.text = title
                        movieRatingDetailed.text = getString(R.string.imdb_rating, voteAverage)
                        movieDescription.text = overview
                        renderCredits(movieId)
                        movieStudio.text = studio
                        movieGenre.text = genre
                        movieYear.text = year
                    }
                }
            }
    }

    private fun renderCredits(movieId: Int) {
        apiClient.getMovieCredits(movieId)
            .applySchedulers()
            .render(binding.movieCredits) { credits ->
                addAll((credits as MovieCredits).cast.map(::ActorItem))
            }
    }
}
