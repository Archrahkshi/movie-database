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
import com.archrahkshi.moviedatabase.databinding.FeedFragmentBinding
import com.archrahkshi.moviedatabase.databinding.FeedHeaderBinding
import com.archrahkshi.moviedatabase.network.apiClient
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.navOptions

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
        MovieList.entries.forEach(::renderMovies)
    }

    private fun setupSearchObserver() {
        searchBinding.searchToolbar.observeContent().onReceive(action = ::openSearch)
    }

    private fun openSearch(searchText: String) {
        findNavController().navigate(
            R.id.search_dest,
            bundleOf(KEY_SEARCH to searchText),
            navOptions
        )
    }

    private fun renderMovies(movieList: MovieList) {
        apiClient.getMovies(
            movieList.name.lowercase()
        ).render(binding.moviesRecyclerView) { movies ->
            add(
                MovieCardContainer(
                    getString(movieList.title),
                    (movies as Movies).results.map { MovieItem(it, ::openMovieDetails) }
                )
            )
        }
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
