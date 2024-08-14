package com.archrahkshi.moviedatabase.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.archrahkshi.moviedatabase.BuildConfig.BACKDROP_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.MovieCredits
import com.archrahkshi.moviedatabase.data.MovieDetails
import com.archrahkshi.moviedatabase.databinding.MovieDetailsFragmentBinding
import com.archrahkshi.moviedatabase.network.ApiClient
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.feed.KEY_MOVIE_ID
import com.archrahkshi.moviedatabase.ui.ifSuccessful
import com.archrahkshi.moviedatabase.ui.loadFromPath
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber.Forest.e

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {
    private var movieId = 0
    private val creditsAdapter by lazy<GroupAdapter<GroupieViewHolder>>(::GroupAdapter)

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MovieDetailsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId = requireArguments().getInt(KEY_MOVIE_ID)
        ApiClient.getMovieDetails(movieId).enqueue(
            object : Callback<MovieDetails> {
                override fun onResponse(
                    call: Call<MovieDetails>,
                    response: Response<MovieDetails>
                ) {
                    response.ifSuccessful {
                        with(binding) {
                            movieBackdrop.loadFromPath(
                                backdropPath ?: posterPath!!,
                                BACKDROP_WIDTH
                            )
                            movieTitleDetailed.text = title!!
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

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                    e(t)
                }
            }
        )
    }

    private fun renderCredits() {
        ApiClient.getMovieCredits(movieId).enqueue(
            object : Callback<MovieCredits> {
                override fun onResponse(
                    call: Call<MovieCredits>,
                    response: Response<MovieCredits>
                ) {
                    response.ifSuccessful {
                        binding.movieCredits.adapter = creditsAdapter.apply {
                            addAll(cast.filter { it.name != null }.map(::ActorItem))
                        }
                    }
                }

                override fun onFailure(call: Call<MovieCredits>, t: Throwable) {
                    e(t)
                }
            }
        )
    }
}
