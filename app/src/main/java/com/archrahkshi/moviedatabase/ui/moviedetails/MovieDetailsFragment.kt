package com.archrahkshi.moviedatabase.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.archrahkshi.moviedatabase.BuildConfig.BACKDROP_WIDTH
import com.archrahkshi.moviedatabase.MovieDatabaseApp.Companion.appContext
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.DataObject
import com.archrahkshi.moviedatabase.data.db.AppDatabase
import com.archrahkshi.moviedatabase.data.network.apiClient
import com.archrahkshi.moviedatabase.data.vo.MovieCredits
import com.archrahkshi.moviedatabase.data.vo.MovieDetails
import com.archrahkshi.moviedatabase.databinding.MovieDetailsFragmentBinding
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.feed.KEY_MOVIE_ID
import com.archrahkshi.moviedatabase.ui.loadFromPath
import kotlinx.coroutines.launch

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {
    private var movieId: Int = 0

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MovieDetailsFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId = requireArguments().getInt(KEY_MOVIE_ID)
        AppDatabase.getInstance(appContext).movieDao().isMovieSaved(
            movieId
        ).applySchedulers().subscribeAndDispose {
            if (this) {
                AppDatabase.getInstance(appContext).movieDao().getMovie(movieId)
            } else {
                apiClient.getMovieDetails(movieId)
            }.applySchedulers().withProgressBar(
                binding.movieBackdrop,
                binding.movieDetails
            ).subscribeAndDispose { renderMovieDetails() }
        }
    }

    private fun DataObject.renderMovieDetails() {
        with(toViewObject() as MovieDetails) {
            with(binding) {
                movieBackdrop.loadFromPath(backdropPath ?: posterPath, BACKDROP_WIDTH)
                movieTitleDetailed.text = title
                movieLike.isChecked = isFavorite
                movieRatingDetailed.text = getString(R.string.imdb_rating, voteAverage)
                movieDescription.text = overview
                renderCredits(movieId)
                movieStudio.text = studio
                movieGenre.text = genre
                movieYear.text = year
            }
            binding.movieLike.setOnCheckedChangeListener { _, isChecked ->
                lifecycleScope.launch {
                    if (isChecked) {
                        isFavorite = true
                        updateLike()
                    } else if (isFeedCache) {
                        isFavorite = false
                        updateLike()
                    } else {
                        removeFromDatabase()
                        isFavorite = false
                    }
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
