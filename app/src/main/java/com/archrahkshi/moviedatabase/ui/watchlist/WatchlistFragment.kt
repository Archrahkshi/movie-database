package com.archrahkshi.moviedatabase.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.archrahkshi.moviedatabase.MovieDatabaseApp.Companion.appContext
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.db.AppDatabase
import com.archrahkshi.moviedatabase.data.vo.MovieDetails
import com.archrahkshi.moviedatabase.databinding.WatchlistFragmentBinding
import com.archrahkshi.moviedatabase.ui.BaseFragment
import com.archrahkshi.moviedatabase.ui.feed.KEY_MOVIE_ID
import com.archrahkshi.moviedatabase.ui.profile.ProfileFragment.Companion.recycledViewPool

class WatchlistFragment : BaseFragment<WatchlistFragmentBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        WatchlistFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.watchlist.setRecycledViewPool(recycledViewPool)
        binding.watchlist.layoutManager = GridLayoutManager(context, 4)

        AppDatabase.getInstance(appContext).movieDao().getFavoriteMovies()
            .applySchedulers()
            .withProgressBar(binding.watchlist)
            .renderAll(binding.watchlist) { adapter ->
                adapter.addAll(
                    map { movie ->
                        WatchlistItem((movie as MovieDetails).posterPath) {
                            openMovieDetails(movie.id)
                        }
                    }
                )
            }
    }

    private fun openMovieDetails(movieId: Int) {
        findNavController().navigate(
            R.id.movie_details_fragment,
            bundleOf(KEY_MOVIE_ID to movieId)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = WatchlistFragment()
    }
}
