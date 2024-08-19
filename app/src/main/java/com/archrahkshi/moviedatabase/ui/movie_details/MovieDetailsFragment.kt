package com.archrahkshi.moviedatabase.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.archrahkshi.moviedatabase.BuildConfig.BACKDROP_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.databinding.MovieDetailsFragmentBinding
import com.archrahkshi.moviedatabase.loadFromPath
import com.archrahkshi.moviedatabase.network.apiClient
import com.archrahkshi.moviedatabase.then
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.feed.KEY_MOVIE_ID
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {
    private var movieId = 0

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MovieDetailsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId = requireArguments().getInt(KEY_MOVIE_ID)
        apiClient.getMovieDetails(movieId).then {
            with(binding) {
                movieBackdrop.loadFromPath(
                    backdropPath ?: posterPath ?: "",
                    BACKDROP_WIDTH
                )
                movieTitleDetailed.text = title
                movieRatingDetailed.text = getString(R.string.imdb_rating, voteAverage)
                movieDescription.text = overview
                renderCredits()
                movieStudio.text = productionCompanies?.joinToString {
                    it.name.orEmpty()
                }.orEmpty()
                movieGenre.text = genres?.joinToString { it.name.orEmpty() }.orEmpty()
                movieYear.text = releaseDate?.substringBefore('-').orEmpty()
            }
        }
    }

    private fun renderCredits() {
        apiClient.getMovieCredits(movieId).then {
            binding.movieCredits.adapter = GroupAdapter<GroupieViewHolder>().apply {
                addAll(cast.filter { it.name != null }.map(::ActorItem))
            }
        }
    }
}
