package com.archrahkshi.moviedatabase.ui.feed

import android.view.View
import com.archrahkshi.moviedatabase.BuildConfig.POSTER_WIDTH
import com.archrahkshi.moviedatabase.R
import com.archrahkshi.moviedatabase.data.Movie
import com.archrahkshi.moviedatabase.databinding.ItemCardBinding
import com.archrahkshi.moviedatabase.loadFromPath
import com.archrahkshi.moviedatabase.toStars
import com.xwray.groupie.viewbinding.BindableItem

class MovieItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemCardBinding>() {
    override fun bind(viewBinding: ItemCardBinding, position: Int) {
        with(viewBinding) {
            content.posterPath?.let { moviePoster.loadFromPath(it, POSTER_WIDTH) }
            movieTitle.text = content.title
            movieRating.rating = content.voteAverage.toStars()
            movieCard.setOnClickListener { onClick(content) }
        }
    }

    override fun getLayout() = R.layout.item_card

    override fun initializeViewBinding(view: View) = ItemCardBinding.bind(view)
}
