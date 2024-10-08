package com.archrahkshi.moviedatabase.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.network.apiClient
import com.archrahkshi.moviedatabase.data.vo.Movies
import com.archrahkshi.moviedatabase.databinding.FeedFragmentBinding
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.feed.MovieList.NOW_PLAYING
import com.archrahkshi.moviedatabase.ui.feed.MovieList.POPULAR
import com.archrahkshi.moviedatabase.ui.feed.MovieList.UPCOMING
import com.archrahkshi.moviedatabase.ui.search.SearchItem
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.launch

const val KEY_MOVIE_ID = "movieId"

private enum class MovieList(@StringRes val title: Int) {
    NOW_PLAYING(R.string.recommended),
    POPULAR(R.string.popular),
    UPCOMING(R.string.upcoming)
}

class FeedFragment : BaseFragment<FeedFragmentBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FeedFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchObserver()
        renderMovies()
    }

    private fun setupSearchObserver() {
        binding.searchToolbar.observeSearchContent().onReceive {
            if (isEmpty()) {
                renderMovies()
            } else {
                apiClient.searchForMovies(this)
                    .applySchedulers()
                    .withProgressBar(binding.feed)
                    .render(binding.feed) { movies ->
                        clear()
                        addAll(
                            (movies as Movies).results.map { movie ->
                                SearchItem(movie) { openMovieDetails(movie.id) }
                            }
                        )
                    }
            }
        }
    }

    private fun renderMovies() {
        val responses = MovieList.entries.associateWith { apiClient.getMovies(it.name.lowercase()) }
        Single.zip(
            responses[NOW_PLAYING]!!,
            responses[POPULAR]!!,
            responses[UPCOMING]!!
        ) { nowPlaying, popular, upcoming -> listOf(nowPlaying, popular, upcoming) }
            .applySchedulers()
            .withProgressBar(binding.feed)
            .renderAll(binding.feed) { adapter ->
                adapter.addAll(composeFeed(map { it as Movies }))
            }
    }

    private fun composeFeed(movieLists: List<Movies>) =
        List(MovieList.entries.size) { index ->
            MovieCardContainer(
                getString(MovieList.entries[index].title),
                movieLists[index].results.onEach {
                    apiClient.getMovieDetails(it.id).applySchedulers().subscribeAndDispose {
                        lifecycleScope.launch {
                            toViewObject().saveToDatabase()
                        }
                    }
                }.map { movie -> MovieItem(movie) { openMovieDetails(movie.id) } }
            )
        }

    private fun openMovieDetails(movieId: Int) {
        findNavController().navigate(
            R.id.movie_details_fragment,
            bundleOf(KEY_MOVIE_ID to movieId)
        )
    }

    override fun onStop() {
        super.onStop()
        binding.searchToolbar.clear()
    }

    companion object {
        val recycledViewPool = RecycledViewPool()
    }
}
