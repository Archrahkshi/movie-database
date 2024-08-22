package com.archrahkshi.moviedatabase.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Movie
import com.archrahkshi.moviedatabase.data.Movies
import com.archrahkshi.moviedatabase.data.ViewObject
import com.archrahkshi.moviedatabase.databinding.FeedFragmentBinding
import com.archrahkshi.moviedatabase.databinding.FeedHeaderBinding
import com.archrahkshi.moviedatabase.network.apiClient
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.navOptions
import io.reactivex.rxjava3.core.Single

const val KEY_SEARCH = "search"
const val KEY_MOVIE_ID = "movieId"

private enum class MovieList(@StringRes val title: Int) {
    NOW_PLAYING(R.string.recommended),
    POPULAR(R.string.popular),
    UPCOMING(R.string.upcoming)
}

class FeedFragment : BaseFragment<FeedFragmentBinding>() {
    private var _searchBinding: FeedHeaderBinding? = null
    private val searchBinding get() = _searchBinding!!

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FeedFragmentBinding.inflate(inflater, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        bindHeader()
        return binding.root
    }

    private fun bindHeader() {
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        requireActivity().addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
                    inflater.inflate(R.menu.main_menu, menu)
                }

                override fun onMenuItemSelected(item: MenuItem): Boolean {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchObserver()
        renderMovies()
    }

    private fun setupSearchObserver() {
        searchBinding.searchToolbar.observeSearchContent().onReceive(action = ::openSearch)
    }

    private fun openSearch(searchText: String) {
        findNavController().navigate(
            R.id.search_dest,
            bundleOf(KEY_SEARCH to searchText),
            navOptions
        )
    }

    private fun renderMovies() {
        val responses = Array(MovieList.entries.size) {
            apiClient.getMovies(MovieList.entries[it].name.lowercase())
        }
        Single.zip(responses[0], responses[1], responses[2]) { nowPlaying, popular, upcoming ->
            listOf(nowPlaying, popular, upcoming)
        }
            .applySchedulers()
            .withProgressBar(binding.feedProgressBar, binding.feed)
            .renderAll(binding.feed) { addAll(composeMovieLists(it)) }
    }

    private fun composeMovieLists(movieLists: List<ViewObject>) =
        List(MovieList.entries.size) { index ->
            MovieCardContainer(
                getString(MovieList.entries[index].title),
                (movieLists[index] as Movies).results.map { MovieItem(it, ::openMovieDetails) }
            )
        }

    private fun openMovieDetails(movie: Movie) {
        findNavController().navigate(
            R.id.movie_details_fragment,
            bundleOf(KEY_MOVIE_ID to movie.id),
            navOptions
        )
    }

    override fun onStop() {
        super.onStop()
        searchBinding.searchToolbar.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _searchBinding = null
    }
}
